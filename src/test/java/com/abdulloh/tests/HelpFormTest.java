package com.abdulloh.tests;

import com.abdulloh.base.BaseTest;
import com.abdulloh.components.HelpForm;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HelpFormTest extends BaseTest {

    @Test
    public void shouldCollapseHelpForm() {

        openGamePage();

        HelpForm helpForm = new HelpForm(page);

        helpForm.sendToBottom();

        assertThat(helpForm.getForm()).hasClass(
                Pattern.compile("(^|\\s)is-hidden(\\s|$)")
        );
    }
}