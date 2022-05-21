package io.sandratskyi.challenge.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Set;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.stream.Collectors.toSet;

public class Carousel {
    public SelenideElement homeLogo = $("[data-testid=homeLogo] [id=TuneIn-Marquee]");
    public SelenideElement heroImageContainer = $("[data-testid=heroImageContainer]");
    public SelenideElement topSectionModule = $("[class^=landing-module__topSection]");
    public ElementsCollection heroTextContainers = $$("[data-testid^=heroCarouselTextContainer]");
    public ElementsCollection controlDots = $$("[data-testid^=carouselControlDot]");

    public ElementsCollection getCarouselTextLines() {
        return this.heroTextContainers
                .find(visible)
                .shouldBe(exist)
                .$$("[data-testid^=line]");
    }

    public Set<String> getTextLinesUniqueUrls() {
        return this.getCarouselTextLines()
                .stream().map(x -> x.attr("href"))
                .collect(toSet());
    }
}
