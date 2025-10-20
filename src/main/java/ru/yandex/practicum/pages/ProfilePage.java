package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoutButtonDisplayed() {
        closeModalIfPresent();
        wait.until(ExpectedConditions.urlContains("/profile"));
        return isElementDisplayed(logoutButton);
    }

    public void clickLogoutButton() {
        closeModalIfPresent();
        clickElement(logoutButton);
    }
}