package io.sandratskyi.challenge.config;

import static com.codeborne.selenide.Configuration.*;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

public final class Configuration {
    private static final String LOCALIZATION = "LOCALIZATION";
    private static final String DEFAULT_L10N = "us";

    private Configuration() {
    }

    public static String getL10n() {
        return ofNullable(getenv(LOCALIZATION)).orElse(DEFAULT_L10N);
    }

    public static void build() {
        timeout = 10000;
        browserSize = "1920x1080";
        baseUrl = "https://tunein.com";
        screenshots = false;
        savePageSource = false;
        headless = true;
    }
}
