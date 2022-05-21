package io.sandratskyi.challenge.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.openqa.selenium.support.Color.fromString;

public final class SelenideElementUtils {

    public SelenideElementUtils() {
    }

    public static String getColorAsHex(SelenideElement element) {
        return fromString(element.getCssValue("color")).asHex();
    }

    public static String getBackgroundColor(SelenideElement element) {
        return fromString(element.getCssValue("background-color")).asHex();
    }

    public static Set<String> getAllHrefValues(ElementsCollection elements) {
        return elements.stream().map(element -> element.attr("href")).collect(toSet());
    }
}
