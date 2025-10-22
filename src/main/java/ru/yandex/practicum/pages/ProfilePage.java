package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка отображения кнопки выхода")
    public boolean isLogoutButtonDisplayed() {
        closeModalIfPresent();
        wait.until(ExpectedConditions.urlContains("/profile"));
        return isElementDisplayed(logoutButton);
    }

    @Step("Клик по кнопке выхода")
    public void clickLogoutButton() {
        closeModalIfPresent();
        clickElement(logoutButton);
    }
}