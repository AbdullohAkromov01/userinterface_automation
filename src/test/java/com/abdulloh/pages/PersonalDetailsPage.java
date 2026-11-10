package com.abdulloh.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PersonalDetailsPage {

    private final Locator pageContainer;

    public PersonalDetailsPage(Page page) {
        this.pageContainer = page.locator(".personal-details");
    }

    public Locator getPageContainer() {
        return pageContainer;
    }
}