import com.codeborne.selenide.Selenide;
import extension.BaseSetup;
import io.sandratskyi.challenge.fragments.Carousel;
import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.sandratskyi.challenge.pages.HomePage.atCarousel;
import static io.sandratskyi.challenge.utils.CssUtils.getBackgroundColor;
import static io.sandratskyi.challenge.utils.CssUtils.getColorAsHex;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Carousel Spec => ")
@BaseSetup
public class CarouselTest {

    private final Carousel carousel = atCarousel();

    @Tag("smoke")
    @Tag("regression")
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

    @Tag("smoke")
    @DisplayName("it has same urls for each text line")
    @Test
    void shouldHaveSameUrlsForEachTextLine() {
        var urls = carousel.getTextLinesUniqueUrls();

        assertEquals(1, urls.size(), "Have different urls in lines: " + urls);
    }

    @Tag("smoke")
    @DisplayName("it change text lines at next slide")
    @Test
    void shouldChangeTextLinesAtNextSlide() {
        var currentTextLines = carousel.getCarouselTextLines().texts();
        carousel.controlDots.last().click();
        var changedTextLines = carousel.getCarouselTextLines().texts();

        assertFalse(
                currentTextLines.containsAll(changedTextLines),
                "Has the same text lines with: " + currentTextLines + " and " + changedTextLines
        );
    }

    @Tag("regression")
    @TestFactory
    Stream<DynamicTest> shouldHaveSameUrlsForEachSlides() {
        return carousel.controlDots.stream().map(dot -> {
            dot.click();
            var urls = carousel.getTextLinesUniqueUrls();

            return dynamicTest(
                    "it has the same URLs for all text lines " + urls,
                    () -> assertEquals(
                            1, urls.size(),
                            "Have different urls in lines: " + urls
                    ));
        });
    }

    @Tag("smoke")
    @DisplayName("it has unique color for each slide")
    @Test
    void shouldHaveUniqueColorForEachSlide() {
        var dots = carousel.controlDots;
        var colors = dots.stream().map(dot -> {
            dot.click();
            return getBackgroundColor(carousel.topSectionModule);
        }).collect(Collectors.toSet());

        assertEquals(
                dots.size(), colors.size(),
                "Have " + dots.size() + " slides but only " + colors.size() + " unique colors"
        );
    }

    @Tag("regression") //ADD COLORS TO SET
    @TestFactory
    Stream<DynamicTest> shouldHaveDifferentBackgroundColorForEachSlide() {
        return carousel.controlDots.stream().skip(1).map(dot -> {
            var current = getBackgroundColor(carousel.topSectionModule);
            Selenide.screenshot("slide1" + current);
            dot.click();
            Selenide.sleep(5000);
            var next = getBackgroundColor(carousel.topSectionModule);
            Selenide.screenshot("slide2" + next);

            return dynamicTest(
                    "it change slide background color " + current + " to " + next,
                    () -> assertNotEquals(current, next, "Background has same color")
            );
        });
    }

    @Tag("regression")
    @TestFactory
    Stream<DynamicTest> shouldChangeTextLinesForEachSlide() {
        return carousel.controlDots.stream().skip(1).map(dot -> {
            var currentTextLines = carousel.getCarouselTextLines().texts();
            dot.click();
            var changedTextLines = carousel.getCarouselTextLines().texts();

            return dynamicTest(
                    "it change text from " + currentTextLines + " to " + changedTextLines,
                    () -> assertFalse(
                            currentTextLines.containsAll(changedTextLines),
                            "Has the same text lines with: " + currentTextLines + " and " + changedTextLines
                    ));
        });
    }

    @Tag("regression")
    @TestFactory
    Stream<DynamicTest> shouldAcceptTextStylesForEachSlide() {
        var slideNumber = new AtomicInteger(1);
        return carousel.controlDots.stream().map(dot -> dynamicTest(
                "it confirm text lines style for " + slideNumber.getAndIncrement() + " slide",
                () -> {
                    dot.click();
                    var textLines = carousel.getCarouselTextLines();

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
