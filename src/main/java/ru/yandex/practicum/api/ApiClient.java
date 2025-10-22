package ru.yandex.practicum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api";
    private static final String REGISTER_ENDPOINT = "/auth/register";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_ENDPOINT = "/auth/user";
    private static final String LOGOUT_ENDPOINT = "/auth/logout";

    public static class User {
        public String email;
        public String password;
        public String name;

        public User(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }
    }

    public static class LoginUser {
        public String email;
        public String password;

        public LoginUser(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static class LogoutUser {
        public String token;

        public LogoutUser(String token) {
            this.token = token;
        }
    }

    @Step("Создать пользователя через API")
    public Response createUser(String email, String password, String name) {
        User user = new User(email, password, name);
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .post(REGISTER_ENDPOINT);
    }

    @Step("Удалить пользователя через API")
    public void deleteUser(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .delete(USER_ENDPOINT);
    }

    @Step("Логин пользователя через API для получения токена")
    public Response loginUser(String email, String password) {
        LoginUser loginUser = new LoginUser(email, password);
        return RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(loginUser)
                .post(LOGIN_ENDPOINT);
    }

    @Step("Логаут пользователя через API")
    public void logoutUser(String refreshToken) {
        LogoutUser logoutUser = new LogoutUser(refreshToken);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(logoutUser)
                .post(LOGOUT_ENDPOINT);
    }
}