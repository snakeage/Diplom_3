package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.api.ApiClient;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.ProfilePage;
import ru.yandex.practicum.pages.RegisterPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;
    private ApiClient apiClient;
    private String accessToken;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);
        apiClient = new ApiClient();

        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(8);

        mainPage.open();
        mainPage.clickPersonalCabinetButton();
        loginPage.clickRegisterLink();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            apiClient.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации через UI с уникальными данными")
    public void successfulRegistration() {
        System.out.println("Starting registration with: " + name + ", " + email + ", " + password);
        registerPage.register(name, email, password);

        if (driver.getCurrentUrl().contains("/register")) {
            System.out.println("Still on register page, clicking login button");
            registerPage.clickLoginLink();
        }

        loginPage.waitForLoginPage();
        loginPage.login(email, password);
        mainPage.clickPersonalCabinetButton();
        assertTrue("Кнопка выхода не отображается после регистрации и входа", profilePage.isLogoutButtonDisplayed());

        Response loginResponse = apiClient.loginUser(email, password);
        accessToken = loginResponse.path("accessToken");
        System.out.println("Access token received: " + accessToken);
        assertThat("Access token not received", accessToken, org.hamcrest.Matchers.notNullValue());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверка ошибки при пароле меньше 6 символов")
    public void invalidPasswordRegistration() {
        String shortPassword = RandomStringUtils.randomAlphanumeric(5);
        registerPage.register(name, email, shortPassword);
        assertTrue("Ошибка пароля не отображается", registerPage.isErrorDisplayed());
        assertThat("Сообщение об ошибке неверное", registerPage.getErrorMessage(), equalTo("Некорректный пароль"));
    }
}