package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.api.ApiClient;
import ru.yandex.practicum.pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
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

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        profilePage = new ProfilePage(driver);
        apiClient = new ApiClient();

        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(8);

        // Создаём пользователя через API — токен получаем в tearDown
        apiClient.createUser(email, password, name);

        mainPage.open();
    }

    @After
    public void tearDown() {
        // Попытка получить токен, если его нет (тест мог упасть до входа)
        if (email != null && password != null && accessToken == null) {
            try {
                Response loginResponse = apiClient.loginUser(email, password);
                if (loginResponse.getStatusCode() == 200) {
                    accessToken = loginResponse.path("accessToken");
                }
            } catch (Exception e) {
                System.out.println("Не удалось получить токен: " + e.getMessage());
            }
        }

        // Удаление пользователя
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
        assertTrue("Кнопка выхода не отображается после входа", profilePage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через личный кабинет")
    public void loginFromPersonalCabinet() {
        mainPage.clickPersonalCabinetButton();
        loginPage.login(email, password);
        mainPage.clickPersonalCabinetButton();
        assertTrue("Кнопка выхода не отображается после входа", profilePage.isLogoutButtonDisplayed());
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
        assertTrue("Кнопка выхода не отображается после входа", profilePage.isLogoutButtonDisplayed());
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
        assertTrue("Кнопка выхода не отображается после входа", profilePage.isLogoutButtonDisplayed());
    }
}