
package com.abdulloh.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.options.AriaRole.BUTTON;

public class CookieBanner {

    private final Locator banner;
    private final Locator acceptButton;

    public CookieBanner(Page page) {
        this.banner = page.locator(".cookies");

        this.acceptButton = banner.getByRole(
                BUTTON,
                new Locator.GetByRoleOptions()
                        .setName("Not really, no")
        );
    }

    public void acceptCookies() {
        acceptButton.click();
    }

    public Locator getBanner() {
        return banner;
    }
}
