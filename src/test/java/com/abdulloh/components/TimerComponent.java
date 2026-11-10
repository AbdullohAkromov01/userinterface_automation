
package com.abdulloh.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TimerComponent {

    private final Locator timer;

    public TimerComponent(Page page) {
        this.timer = page.locator(
                ".view__content .timer"
        );
    }

    public Locator getTimer() {
        return timer;
    }
}
