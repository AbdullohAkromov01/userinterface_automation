
package com.abdulloh.base;

import com.abdulloh.utils.ConfigReader;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        BrowserType browserType = switch (
                ConfigReader.get("browser").toLowerCase()
                ) {
            case "chromium" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: "
                            + ConfigReader.get("browser")
            );
        };

        browser = browserType.launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(ConfigReader.getBoolean("headless"))
        );

        context = browser.newContext();
        page = context.newPage();
    }

    protected void openGamePage() {
        page.navigate(ConfigReader.get("game.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
