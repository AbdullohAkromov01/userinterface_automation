package com.abdulloh.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvatarAndInterestsPage {

    private final Page page;
    private final Locator pageContainer;
    private final Locator uploadButton;
    private final Locator avatarImage;
    private final Locator nextButton;

    public AvatarAndInterestsPage(Page page) {
        this.page = page;

        pageContainer = page.locator(".avatar-and-interests-page");

        uploadButton = pageContainer.locator(
                ".avatar-and-interests__upload-button"
        );

        avatarImage = pageContainer.locator(
                ".avatar-and-interests__avatar-image"
        );

        nextButton = pageContainer.locator(
                ".avatar-and-interests-page__buttons-row"
        ).getByText(
                "Next",
                new Locator.GetByTextOptions().setExact(true)
        );
    }

    public Locator getPageContainer() {
        return pageContainer;
    }

    public Locator getAvatarImage() {
        return avatarImage;
    }

    public AvatarAndInterestsPage uploadAvatar(String imagePath) {
        FileChooser chooser = page.waitForFileChooser(
                uploadButton::click
        );

        chooser.setFiles(Paths.get(imagePath));

        return this;
    }

    public AvatarAndInterestsPage unselectAllInterests() {
        pageContainer.locator(
                "label[for='interest_unselectall']"
        ).click();

        return this;
    }

    public PersonalDetailsPage clickNext() {
        nextButton.click();
        return new PersonalDetailsPage(page);
    }

    public int getSelectedInterestsCount() {
        return pageContainer.locator(
                ".avatar-and-interests__interests-list " +
                        "input[type='checkbox']:checked"
        ).count();
    }

    public AvatarAndInterestsPage selectRandomInterests(int count) {

        unselectAllInterests();

        Locator interests = pageContainer.locator(
                "input[type='checkbox'][id^='interest_']" +
                        ":not(#interest_selectall)" +
                        ":not(#interest_unselectall)"
        );

        int total = interests.count();

        if (count < 1 || count > total) {
            throw new IllegalArgumentException(
                    "Requested " + count +
                            " interests, but only " + total + " are available"
            );
        }

        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            indexes.add(i);
        }

        Collections.shuffle(indexes);

        for (int i = 0; i < count; i++) {

            String id = interests.nth(indexes.get(i))
                    .getAttribute("id");

            page.locator("label[for='" + id + "']").click();
        }

        return this;
    }

    public void waitUntilDisplayed() {
        pageContainer.waitFor();
    }
}