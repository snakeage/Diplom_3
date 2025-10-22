package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input[@type='text']");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input[@type='text']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath("//p[contains(@class, 'input__error')]");
    private final By loginLink = By.xpath("//a[contains(@href, '/login') and contains(text(), 'Войти')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        System.out.println("Entering name: " + name);
        closeModalIfPresent();
        sendKeysToElement(nameInput, name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        System.out.println("Entering email: " + email);
        closeModalIfPresent();
        sendKeysToElement(emailInput, email);
    }

    @Step("Ввод пароля: {password}")
    public void enterPassword(String password) {
        System.out.println("Entering password: " + password);
        closeModalIfPresent();
        sendKeysToElement(passwordInput, password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegisterButton() {
        System.out.println("Clicking register button");
        closeModalIfPresent();
        clickElement(registerButton);
    }

    @Step("Клик по ссылке входа")
    public void clickLoginLink() {
        System.out.println("Clicking login link");
        closeModalIfPresent();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
        System.out.println("Login link clicked");
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
    }

    @Step("Регистрация пользователя {name}, {email}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки")
    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return getTextFromElement(errorMessage);
    }
}