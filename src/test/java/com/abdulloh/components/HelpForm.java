
package com.abdulloh.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HelpForm {

    private final Locator form;
    private final Locator sendToBottomButton;

    public HelpForm(Page page) {
        this.form = page.locator(".help-form");

        this.sendToBottomButton = form.locator(
                ".help-form__send-to-bottom-button"
        );
    }

    public void sendToBottom() {
        sendToBottomButton.click();
    }

    public Locator getForm() {
        return form;
    }
}
