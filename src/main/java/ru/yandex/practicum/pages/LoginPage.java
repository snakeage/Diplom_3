package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input[@type='text']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[contains(@class, 'button_button_type_primary') and contains(text(), 'Войти')]");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        System.out.println("Entering email: " + email);
        closeModalIfPresent();
        sendKeysToElement(emailInput, email);
    }

    public void enterPassword(String password) {
        System.out.println("Entering password: " + password);
        closeModalIfPresent();
        sendKeysToElement(passwordInput, password);
    }

    public void clickLoginButton() {
        System.out.println("Clicking login button");
        closeModalIfPresent();
        clickElement(loginButton);
    }

    public void clickRegisterLink() {
        System.out.println("Clicking register link");
        closeModalIfPresent();
        clickElement(registerLink);
    }

    public void clickForgotPasswordLink() {
        System.out.println("Clicking forgot password link");
        closeModalIfPresent();
        clickElement(forgotPasswordLink);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void waitForLoginPage() {
        System.out.println("Waiting for login page to load");
        wait.until(ExpectedConditions.urlContains("/login"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        System.out.println("Login page loaded");
    }
}