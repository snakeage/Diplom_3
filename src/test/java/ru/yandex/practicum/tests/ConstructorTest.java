package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка перехода к булкам")
    public void navigateToBuns() {
        mainPage.navigateToBunsSection();
        assertTrue("Раздел булок не отображается", mainPage.isBunsSectionDisplayed());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка перехода к соусам")
    public void navigateToSauces() {
        mainPage.navigateToSaucesSection();
        assertTrue("Раздел соусов не отображается", mainPage.isSaucesSectionDisplayed());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка перехода к начинкам")
    public void navigateToFillings() {
        mainPage.navigateToFillingsSection();
        assertTrue("Раздел начинок не отображается", mainPage.isFillingsSectionDisplayed());
    }
}