
package com.abdulloh.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TermsAndConditionsDialog {

    private final Page page;
    private final Locator dialog;
    private final Locator scrollContainer;
    private final Locator acceptButton;
    private final Locator acceptButtonContainer;
    private final TimerPopup timerPopup;

    public TermsAndConditionsDialog(Page page) {
        this.page = page;
        this.timerPopup = new TimerPopup(page);

        this.dialog = page.locator(".modal")
                .filter(new Locator.FilterOptions()
                        .setHas(
                                page.locator(".terms-and-conditions")
                        ));

        this.scrollContainer = dialog.locator(
                ".terms-and-conditions__text-content"
        );

        this.acceptButtonContainer = dialog.locator(
                ".terms-and-conditions__accept-button-container"
        );

        this.acceptButton = dialog.getByRole(
                AriaRole.BUTTON,
                new Locator.GetByRoleOptions()
                        .setName("Accept")
                        .setExact(true)
        );
    }

    public void waitUntilVisible() {
        dialog.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );
    }

    public void scrollToBottom() {
        timerPopup.closeIfVisible();

        scrollContainer.hover();

        page.keyboard().down("Alt");

        try {
            page.mouse().wheel(0, 500);
        } finally {
            page.keyboard().up("Alt");
        }

        page.waitForFunction(
                "() => !document.querySelector(" +
                        "'.terms-and-conditions__accept-button-container'" +
                        ").classList.contains('disabled')",
                null
        );
    }

    public boolean isAcceptEnabled() {
        return !Boolean.TRUE.equals(
                acceptButtonContainer.evaluate(
                        "el => el.classList.contains('disabled')"
                )
        );
    }

    public void accept() {
        timerPopup.closeIfVisible();

        if (!isAcceptEnabled()) {
            throw new IllegalStateException(
                    "Terms & Conditions have not been fully scrolled"
            );
        }

        acceptButton.click();
    }

    public boolean isClosed() {
        try {
            dialog.waitFor(
                    new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.HIDDEN)
                            .setTimeout(5000)
            );

            return true;

        } catch (TimeoutError e) {
            return false;
        }
    }
}
