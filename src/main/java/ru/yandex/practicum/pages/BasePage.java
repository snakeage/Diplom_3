package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final By MODAL_OVERLAY = By.xpath("//div[contains(@class, 'Modal_modal_overlay__')]");
    private final By MODAL_CLOSE_BUTTON = By.xpath("//button[@aria-label='Закрыть' or contains(@class, 'Modal_modal__close')]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Поиск элемента")
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Клик по элементу")
    protected void clickElement(By locator) {
        closeModalIfPresent();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        System.out.println("Element " + locator + " is displayed: " + element.isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    @Step("Ввод текста в элемент")
    protected void sendKeysToElement(By locator, String text) {
        closeModalIfPresent();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        System.out.println("Element " + locator + " is displayed: " + element.isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(text);
    }

    @Step("Получение текста элемента")
    protected String getTextFromElement(By locator) {
        return findElement(locator).getText();
    }

    @Step("Проверка отображения элемента")
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание исчезновения модального окна")
    protected void waitForModalToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_OVERLAY));
            System.out.println("Modal overlay disappeared");
        } catch (Exception e) {
            System.out.println("No modal overlay found or timeout");
        }
    }

    @Step("Закрытие модального окна, если оно присутствует")
    protected void closeModalIfPresent() {
        for (int i = 0; i < 3; i++) {
            if (isElementDisplayed(MODAL_CLOSE_BUTTON)) {
                System.out.println("Closing modal window");
                try {
                    clickElement(MODAL_CLOSE_BUTTON);
                    waitForModalToDisappear();
                    break;
                } catch (Exception e) {
                    System.out.println("Failed to close modal: " + e.getMessage());
                }
            } else {
                System.out.println("No modal window found");
                break;
            }
        }
    }
}