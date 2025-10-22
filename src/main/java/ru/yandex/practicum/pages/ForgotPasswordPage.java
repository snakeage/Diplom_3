package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {
    private final By emailInput = By.xpath("//input[@name='name']");
    private final By recoverButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        sendKeysToElement(emailInput, email);
    }

    @Step("Клик по кнопке восстановления")
    public void clickRecoverButton() {
        clickElement(recoverButton);
    }

    @Step("Клик по ссылке входа")
    public void clickLoginLink() {
        closeModalIfPresent();
        clickElement(loginLink);
    }
}