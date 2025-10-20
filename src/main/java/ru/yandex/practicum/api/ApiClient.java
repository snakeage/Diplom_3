package ru.yandex.practicum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api";
    private static final String REGISTER_ENDPOINT = "/auth/register";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_ENDPOINT = "/auth/user";
    private static final String LOGOUT_ENDPOINT = "/auth/logout";

    @Step("Создать пользователя через API")
    public Response createUser(String email, String password, String name) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("name", name);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(body)
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
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return RestAssured.given()
                .log().all() // Логируем запрос и ответ
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(body)
                .post(LOGIN_ENDPOINT);
    }

    @Step("Логаут пользователя через API")
    public void logoutUser(String refreshToken) {
        Map<String, String> body = new HashMap<>();
        body.put("token", refreshToken);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(body)
                .post(LOGOUT_ENDPOINT);
    }
}