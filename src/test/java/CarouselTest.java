import extension.BaseSetup;
import extension.Regression;
import extension.Smoke;
import io.sandratskyi.challenge.fragments.Carousel;
import io.sandratskyi.challenge.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static io.sandratskyi.challenge.utils.SelenideElementUtils.*;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Carousel Spec => ")
@BaseSetup
public class CarouselTest {

    private final Carousel carousel = HomePage.atCarousel();

    @Smoke
    @Regression
    @DisplayName("it has logo with hero image at carousel")
    @Test
    void shouldHaveCarouselImages() {
        var tuneInLogo = carousel.homeLogo;
        var heroImage = carousel.heroImageContainer;

        assertAll(
                "Carousel images is not displayed: ",
                () -> assertTrue(tuneInLogo.isDisplayed()),
                () -> assertTrue(heroImage.isDisplayed())
        );
    }

    @Smoke
    @DisplayName("it has same urls for each text line")
    @Test
    void shouldHaveSameUrlsForEachTextLine() {
        var urls = getAllHrefValues(carousel.getAllTextLines());

        assertEquals(1, urls.size(), "Has different urls in lines: " + urls);
    }

    @Smoke
    @DisplayName("it change text lines at next slide")
    @Test
    void shouldChangeTextLinesAtNextSlide() {
        var currentTextLines = carousel.getAllTextLines().texts();

        carousel.controlDots.last().click();
        var changedTextLines = carousel.getAllTextLines().texts();

        assertFalse(
                currentTextLines.containsAll(changedTextLines),
                "Has the same text lines with: " + currentTextLines + " and " + changedTextLines
        );
    }

    @Regression
    @DisplayName("it has unique background color for each slide")
    @Test
    void shouldHaveUniqueBackgroundColorForEachSlide() {
        var dots = carousel.controlDots;

        var colors = dots.stream().map(dot -> {
            dot.click();
            return getBackgroundColor(carousel.topSectionModule);
        }).collect(toSet());

        assertEquals(
                dots.size(), colors.size(),
                "Has " + dots.size() + " slides but only " + colors.size() + " unique background colors"
        );
    }

    @Regression
    @TestFactory
    Stream<DynamicTest> shouldHaveSameUrlsForEachSlide() {
        return carousel.controlDots.stream().map(dot -> {
            dot.click();
            var urls = getAllHrefValues(carousel.getAllTextLines());

            return dynamicTest(
                    "it has the same URLs for all text lines " + urls,
                    () -> assertEquals(
                            1, urls.size(),
                            "Has different urls in lines: " + urls
                    ));
        });
    }

    @Regression
    @TestFactory
    Stream<DynamicTest> shouldChangeTextLinesForEachSlide() {
        return carousel.controlDots.stream().skip(1).map(dot -> {
            var currentTextLines = carousel.getAllTextLines().texts();

            dot.click();
            var changedTextLines = carousel.getAllTextLines().texts();

            return dynamicTest(
                    "it change text from " + currentTextLines + " to " + changedTextLines,
                    () -> assertFalse(
                            currentTextLines.containsAll(changedTextLines),
                            "Has the same text lines with: " + currentTextLines + " and " + changedTextLines
                    ));
        });
    }

    @Regression
    @TestFactory
    Stream<DynamicTest> shouldAcceptTextStylesForEachSlide() {
        var slideCounter = new AtomicInteger(1);
        return carousel.controlDots.stream().map(dot -> dynamicTest(
                "it confirm text lines style for " + slideCounter.getAndIncrement() + " slide",
                () -> {
                    dot.click();
                    var textLines = carousel.getAllTextLines();

                    var firstLine = getColorAsHex(textLines.get(0));
                    var secondLine = getColorAsHex(textLines.get(1));
                    var thirdLine = getColorAsHex(textLines.get(2));

                    assertAll(
                            "Text lines with wrong color CSS: ",
                            () -> assertEquals("#ffffff", firstLine),
                            () -> assertEquals("#ffffff", secondLine),
                            () -> assertEquals("#1c203c", thirdLine)
                    );
                }));
    }
}
