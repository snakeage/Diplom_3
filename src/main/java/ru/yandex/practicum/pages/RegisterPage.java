package ru.yandex.practicum.pages;

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
    private final By loginButton = By.xpath("//button[contains(@class, 'button_button_type_primary') and contains(text(), 'Войти')]");;
    private final By loginLink = By.xpath("//a[contains(@href, '/login') and contains(text(), 'Войти')]");;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        System.out.println("Entering name: " + name);
        closeModalIfPresent();
        sendKeysToElement(nameInput, name);
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

    public void clickRegisterButton() {
        System.out.println("Clicking register button");
        closeModalIfPresent();
        clickElement(registerButton);
    }

    public void clickLoginLink() {
        System.out.println("Clicking login link");
        closeModalIfPresent();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
        System.out.println("Login button clicked");
    }

    public void clickLoginButton() {
        System.out.println("Clicking login button");
        closeModalIfPresent();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
        System.out.println("Login button clicked");
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
    }

    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getTextFromElement(errorMessage);
    }
}