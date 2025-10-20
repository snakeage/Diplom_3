package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    private static final String URL = "https://stellarburgers.education-services.ru/";

    // Локаторы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalCabinetButton = By.xpath("//a[contains(@href, '/account')]");
    private final By bunsSection = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Булки']]");
    private final By saucesSection = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Соусы']]");
    private final By fillingsSection = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Начинки']]");
    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        closeModalIfPresent();
        waitForModalToDisappear();
    }

    public void clickLoginButton() {
        closeModalIfPresent();
        clickElement(loginButton);
    }

    public void clickPersonalCabinetButton() {
        closeModalIfPresent();
        clickElement(personalCabinetButton);
    }

    public void navigateToBunsSection() {
        closeModalIfPresent();
        System.out.println("Navigating to Buns section");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(bunsSection);
        js.executeScript("arguments[0].click();", element);
    }

    public void navigateToSaucesSection() {
        closeModalIfPresent();
        System.out.println("Navigating to Sauces section");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(saucesSection);
        js.executeScript("arguments[0].click();", element);
    }

    public void navigateToFillingsSection() {
        closeModalIfPresent();
        System.out.println("Navigating to Fillings section");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(fillingsSection);
        js.executeScript("arguments[0].click();", element);
    }

    public boolean isBunsSectionDisplayed() {
        return isElementDisplayed(bunsHeader);
    }

    public boolean isSaucesSectionDisplayed() {
        return isElementDisplayed(saucesHeader);
    }

    public boolean isFillingsSectionDisplayed() {
        return isElementDisplayed(fillingsHeader);
    }
}