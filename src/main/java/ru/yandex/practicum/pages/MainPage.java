package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    private static final String URL = "https://stellarburgers.education-services.ru/";

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalCabinetButton = By.xpath("//a[contains(@href, '/account')]");
    private final By bunsSection = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Булки']]");
    private final By saucesSection = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Соусы']]");
    private final By fillingsSection = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Начинки']]");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(URL);
        closeModalIfPresent();
        waitForModalToDisappear();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        closeModalIfPresent();
        clickElement(loginButton);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalCabinetButton() {
        closeModalIfPresent();
        clickElement(personalCabinetButton);
    }

    @Step("Переход к разделу 'Булки'")
    public void navigateToBunsSection() {
        closeModalIfPresent();
        System.out.println("Navigating to Buns section");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(bunsSection);
        js.executeScript("arguments[0].click();", element);
    }

    @Step("Переход к разделу 'Соусы'")
    public void navigateToSaucesSection() {
        closeModalIfPresent();
        System.out.println("Navigating to Sauces section");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(saucesSection);
        js.executeScript("arguments[0].click();", element);
    }

    @Step("Переход к разделу 'Начинки'")
    public void navigateToFillingsSection() {
        closeModalIfPresent();
        System.out.println("Navigating to Fillings section");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(fillingsSection);
        js.executeScript("arguments[0].click();", element);
    }

    @Step("Проверка отображения раздела 'Булки'")
    public boolean isBunsSectionDisplayed() {
        return isActiveTabSelected(bunsSection);
    }

    @Step("Проверка отображения раздела 'Соусы'")
    public boolean isSaucesSectionDisplayed() {
        return isActiveTabSelected(saucesSection);
    }

    @Step("Проверка отображения раздела 'Начинки'")
    public boolean isFillingsSectionDisplayed() {
        return isActiveTabSelected(fillingsSection);
    }

    private boolean isActiveTabSelected(By tabLocator) {
        try {
            WebElement tab = driver.findElement(tabLocator);
            return tab.getAttribute("class").contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }
}