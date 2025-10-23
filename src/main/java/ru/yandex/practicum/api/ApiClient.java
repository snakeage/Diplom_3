package ru.yandex.practicum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.practicum.api.requests.LoginRequest;
import ru.yandex.practicum.api.requests.UserRequest;

public class ApiClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api";
    private static final String REGISTER_ENDPOINT = "/auth/register";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_ENDPOINT = "/auth/user";

    @Step("Создать пользователя через API")
    public Response createUser(String email, String password, String name) {
        UserRequest user = new UserRequest(email, password, name);
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
        LoginRequest loginUser = new LoginRequest(email, password);
        return RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(loginUser)
                .post(LOGIN_ENDPOINT);
    }
}