
package com.abdulloh.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TimerPopup {

    private final Locator popup;
    private final Locator closeButton;

    public TimerPopup(Page page) {

        this.popup = page.locator(".modal")
                .filter(new Locator.FilterOptions()
                        .setHasText("Hurry up, time is ticking!"));

        this.closeButton = popup.locator(
                ".modal__close-copyright > span"
        );
    }

    public boolean isDisplayed() {
        return popup.isVisible();
    }

    public void closeIfVisible() {

        if (!isDisplayed()) {
            return;
        }

        closeButton.click(
                new Locator.ClickOptions().setTimeout(3000)
        );

        popup.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(5000)
        );
    }
}
