package com.abdulloh.tests;

import com.abdulloh.base.BaseTest;
import com.abdulloh.components.TimerComponent;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TimerTest extends BaseTest {

    @Test
    public void shouldStartTimerAtZero() {

        openGamePage();

        TimerComponent timer = new TimerComponent(page);

        assertThat(timer.getTimer()).isVisible();

        assertThat(timer.getTimer()).hasText("00:00:00");
    }
}