package ru.yandex.practicum.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverHelper {
    protected WebDriver driver;
    private static final String YANDEX_DRIVER_PATH = "src/test/resources/yandexdriver";

    public WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("browserProperty = " + browserProperty);
        BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case YANDEX:
                File yandexDriver = new File(YANDEX_DRIVER_PATH);
                if (!yandexDriver.exists()) {
                    throw new IOException("Yandex driver not found at: " + YANDEX_DRIVER_PATH);
                }
                if (!yandexDriver.canExecute()) {
                    throw new IOException("Yandex driver is not executable: " + YANDEX_DRIVER_PATH);
                }
                System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new IOException("Browser undefined: " + browserProperty);
        }
        return driver;
    }
}