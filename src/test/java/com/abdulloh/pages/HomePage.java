
package com.abdulloh.pages;

import com.abdulloh.utils.ConfigReader;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {


    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(ConfigReader.get("base.url"));
    }

    public RegistrationPage startRegistration() {
        page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("HERE")
                        .setExact(true)
        ).click();

        return new RegistrationPage(page);
    }

}
