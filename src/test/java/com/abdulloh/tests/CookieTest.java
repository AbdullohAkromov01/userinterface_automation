package com.abdulloh.tests;

import com.abdulloh.base.BaseTest;
import com.abdulloh.components.CookieBanner;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CookieTest extends BaseTest {

    @Test
    public void shouldAcceptCookies() {

        openGamePage();

        CookieBanner cookieBanner = new CookieBanner(page);

        assertThat(cookieBanner.getBanner()).isVisible();

        cookieBanner.acceptCookies();

        assertThat(cookieBanner.getBanner()).isHidden();
    }
}