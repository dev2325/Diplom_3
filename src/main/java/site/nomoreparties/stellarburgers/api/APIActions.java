package site.nomoreparties.stellarburgers.api;

import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.HomePage.HOME_PAGE_URL;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.dto.LoginRequest;
import site.nomoreparties.stellarburgers.dto.UserRequest;

public class APIActions {

    String bearerToken;
    Response response;

    @Step("Delete user")
    public void deleteUser(UserRequest user) {
        // подготовим объект юзера чтобы при помощи него авторизоваться
        LoginRequest loginRequest = user.prepareFrom(user);

        // авторизуемся и сохраним ответ
        if (user.getPassword().length() >= 6) {
            response = given()
                    .header("Content-type", "application/json")
                    .baseUri(HOME_PAGE_URL)
                    .and()
                    .body(loginRequest)
                    .when()
                    .post("api/auth/login");

            JsonPath jsonPathEvaluator = response.jsonPath();
            // возьмем accessToken из ответа и сохраним его
            bearerToken = jsonPathEvaluator.get("accessToken");

            // удалим пользователя используя его токен
            given()
                    .header("Authorization", bearerToken)
                    .header("Content-type", "application/json")
                    .baseUri(HOME_PAGE_URL)
                    .delete("api/auth/user");
        }
    }
}

