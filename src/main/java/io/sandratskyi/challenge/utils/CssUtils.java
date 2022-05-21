package io.sandratskyi.challenge.utils;

import com.codeborne.selenide.SelenideElement;

import static org.openqa.selenium.support.Color.fromString;

public class CssUtils {

    public static String getColorAsHex(SelenideElement element) {
        return fromString(element.getCssValue("color")).asHex();
    }

    public static String getBackgroundColor(SelenideElement element) {
        return fromString(element.getCssValue("background-color")).asHex();
    }
}
