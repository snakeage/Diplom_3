package ru.yandex.practicum.pages;

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

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickElement(By locator) {
        closeModalIfPresent();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        System.out.println("Element " + locator + " is displayed: " + element.isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500); // Задержка для анимаций
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    protected void sendKeysToElement(By locator, String text) {
        closeModalIfPresent();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        System.out.println("Element " + locator + " is displayed: " + element.isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getTextFromElement(By locator) {
        return findElement(locator).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForModalToDisappear() {
        By modalOverlay = By.xpath("//div[contains(@class, 'Modal_modal_overlay__')]");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
            System.out.println("Modal overlay disappeared");
        } catch (Exception e) {
            System.out.println("No modal overlay found or timeout");
        }
    }

    protected void closeModalIfPresent() {
        By modalCloseButton = By.xpath("//button[@aria-label='Закрыть' or contains(@class, 'Modal_modal__close')]");
        for (int i = 0; i < 3; i++) { // Повторные попытки закрытия
            if (isElementDisplayed(modalCloseButton)) {
                System.out.println("Closing modal window");
                try {
                    clickElement(modalCloseButton);
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