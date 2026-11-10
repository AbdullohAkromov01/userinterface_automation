package com.abdulloh.pages;

import com.abdulloh.components.TermsAndConditionsDialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegistrationPage {

    private final Page page;

    private final Locator passwordInput;
    private final Locator emailInput;
    private final Locator domainInput;
    private final Locator emailExtensionDropdown;
    private final Locator emailExtensionOptions;
    private final Locator termsCheckbox;
    private final Locator termsLink;
    private final Locator nextButton;

    public RegistrationPage(Page page) {
        this.page = page;

        this.passwordInput =
                page.getByPlaceholder("Choose Password");

        this.emailInput =
                page.getByPlaceholder("Your email");

        this.domainInput =
                page.getByPlaceholder("Domain");

        this.emailExtensionDropdown =
                page.locator(".login-form .dropdown");

        this.emailExtensionOptions =
                emailExtensionDropdown.locator(".dropdown__list");

        this.termsCheckbox =
                page.locator("#accept-terms-conditions");

        this.termsLink =
                page.locator(".login-form__terms-conditions");

        this.nextButton =
                page.locator(".login-form a.button--secondary")
                        .filter(new Locator.FilterOptions()
                                .setHasText("Next"));
    }

    public RegistrationPage enterPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        emailInput.fill(email);
        return this;
    }

    public RegistrationPage enterDomain(String domain) {
        domainInput.fill(domain);
        return this;
    }

    public boolean isDisplayed() {
        return passwordInput.isVisible()
                && emailInput.isVisible();
    }

    public RegistrationPage selectEmailExtension(String extension) {
        emailExtensionDropdown
                .locator(".dropdown__header")
                .click();

        emailExtensionOptions
                .getByText(
                        extension,
                        new Locator.GetByTextOptions()
                                .setExact(true)
                )
                .click();

        return this;
    }

    public AvatarAndInterestsPage clickNext() {
        nextButton.click();

        AvatarAndInterestsPage nextPage =
                new AvatarAndInterestsPage(page);

        nextPage.waitUntilDisplayed();

        return nextPage;
    }

    public RegistrationPage uncheckTermsCheckbox() {
        if (termsCheckbox.isChecked()) {
            page.locator("label[for='accept-terms-conditions']")
                    .click();
        }

        return this;
    }

    public TermsAndConditionsDialog openTermsAndConditions() {
        termsLink.click();
        return new TermsAndConditionsDialog(page);
    }

    public Locator getSelectedEmailExtensionLocator() {
        return emailExtensionDropdown.locator(".dropdown__field");
    }
}