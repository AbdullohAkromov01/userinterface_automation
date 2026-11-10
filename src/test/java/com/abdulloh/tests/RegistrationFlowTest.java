
package com.abdulloh.tests;

import com.abdulloh.base.BaseTest;
import com.abdulloh.components.TermsAndConditionsDialog;
import com.abdulloh.pages.AvatarAndInterestsPage;
import com.abdulloh.pages.HomePage;
import com.abdulloh.pages.PersonalDetailsPage;
import com.abdulloh.pages.RegistrationPage;
import com.abdulloh.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegistrationFlowTest extends BaseTest {

    @Test
    public void shouldCompleteRegistrationUntilCard3() {

        // Step 1: Open home page
        HomePage homePage = new HomePage(page);
        homePage.open();

        // Step 2: Navigate to Card 1
        RegistrationPage registrationPage =
                homePage.startRegistration();

        Assert.assertTrue(
                registrationPage.isDisplayed(),
                "Card 1 should be displayed"
        );

        // Step 3: Enter random registration data
        registrationPage
                .enterPassword(TestDataGenerator.randomPassword())
                .enterEmail(TestDataGenerator.randomEmailName())
                .enterDomain(TestDataGenerator.defaultEmailDomain())
                .selectEmailExtension(".com")
                .uncheckTermsCheckbox();

        assertThat(registrationPage.getSelectedEmailExtensionLocator())
                .hasText(".com");

        // Step 4: Accept Terms & Conditions
        TermsAndConditionsDialog dialog =
                registrationPage.openTermsAndConditions();

        dialog.waitUntilVisible();
        dialog.scrollToBottom();
        dialog.accept();

        Assert.assertTrue(
                dialog.isClosed(),
                "Terms & Conditions dialog should close"
        );

        // Step 5: Navigate to Card 2
        AvatarAndInterestsPage avatarPage =
                registrationPage.clickNext();

        assertThat(avatarPage.getPageContainer()).isVisible();

        // Step 6: Upload avatar
        avatarPage.uploadAvatar(
                "src/test/resources/images/avatar.png"
        );

        assertThat(avatarPage.getAvatarImage()).isVisible();

        // Step 7: Select three interests
        avatarPage.selectRandomInterests(3);

        Assert.assertEquals(
                avatarPage.getSelectedInterestsCount(),
                3,
                "Exactly three interests should be selected"
        );

        // Step 8: Navigate to Card 3
        PersonalDetailsPage detailsPage =
                avatarPage.clickNext();

        assertThat(detailsPage.getPageContainer()).isVisible();
    }
}
