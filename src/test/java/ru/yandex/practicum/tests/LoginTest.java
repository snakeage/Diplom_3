package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.api.ApiClient;
import ru.yandex.practicum.helpers.DriverHelper;
import ru.yandex.practicum.pages.*;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private ProfilePage profilePage;
    private ApiClient apiClient;
    private String email;
    private String password;
    private String name;
    private String accessToken;
    private String refreshToken;

    @Before
    public void setUp() throws IOException {
        DriverHelper driverHelper = new DriverHelper();
        driver = driverHelper.initDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        profilePage = new ProfilePage(driver);
        apiClient = new ApiClient();

        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(8);
        Response createResponse = apiClient.createUser(email, password, name);
        accessToken = createResponse.path("accessToken");
        refreshToken = createResponse.path("refreshToken");

        mainPage.open();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            apiClient.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа через кнопку на главной странице")
    public void loginFromMainPage() {
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        mainPage.clickPersonalCabinetButton();
        assertTrue("Не отображается кнопка выхода после входа", profilePage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через личный кабинет")
    public void loginFromPersonalCabinet() {
        mainPage.clickPersonalCabinetButton();
        loginPage.login(email, password);
        mainPage.clickPersonalCabinetButton();
        assertTrue("Не отображается кнопка выхода после входа", profilePage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа из формы регистрации")
    public void loginFromRegisterPage() {
        mainPage.clickPersonalCabinetButton();
        loginPage.clickRegisterLink();
        registerPage.waitForPageLoad();
        registerPage.clickLoginLink();
        loginPage.login(email, password);
        mainPage.clickPersonalCabinetButton();
        assertTrue("Не отображается кнопка выхода после входа", profilePage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа из формы восстановления пароля")
    public void loginFromForgotPasswordPage() {
        mainPage.clickPersonalCabinetButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();
        loginPage.login(email, password);
        mainPage.clickPersonalCabinetButton();
        assertTrue("Не отображается кнопка выхода после входа", profilePage.isLogoutButtonDisplayed());
    }
}