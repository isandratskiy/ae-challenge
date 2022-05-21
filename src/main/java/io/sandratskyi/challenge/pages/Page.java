package io.sandratskyi.challenge.pages;

import com.codeborne.selenide.Selenide;

abstract class Page {

    public static void open(String path) {
        Selenide.open(path);
    }
}
