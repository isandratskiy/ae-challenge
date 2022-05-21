package io.sandratskyi.challenge.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Carousel {
    public SelenideElement homeLogo = $("[data-testid=homeLogo] [id=TuneIn-Marquee]");
    public SelenideElement heroImageContainer = $("[data-testid=heroImageContainer]");
    public SelenideElement topSectionModule = $("[class^=landing-module__topSection]");
    public ElementsCollection heroTextContainers = $$("[data-testid^=heroCarouselTextContainer]");
    public ElementsCollection controlDots = $$("[data-testid^=carouselControlDot]");

    public ElementsCollection getAllTextLines() {
        return this.heroTextContainers
                .find(visible)
                .shouldBe(exist)
                .$$("[data-testid^=line]");
    }
}
