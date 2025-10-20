package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {
    // Локаторы
    private final By emailInput = By.xpath("//input[@name='name']");
    private final By recoverButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        sendKeysToElement(emailInput, email);
    }

    public void clickRecoverButton() {
        clickElement(recoverButton);
    }

    public void clickLoginLink() {
        closeModalIfPresent();
        clickElement(loginLink);
    }
}