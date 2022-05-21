package extension;

import io.sandratskyi.challenge.config.Configuration;
import io.sandratskyi.challenge.pages.HomePage;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SetupExtension implements BeforeEachCallback, BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        Configuration.build();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        HomePage.open("?lang=" + Configuration.getL10n() + "&abtestid=5015&homecarouselautoscroll=false");
    }
}
