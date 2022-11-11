package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.APIActions;
import site.nomoreparties.stellarburgers.dto.UserRequest;

public abstract class BaseTest {

    protected static WebDriver driver;

    protected static HeaderPage objHeaderPage;
    protected static HomePage objHomePage;
    protected static LoginPage objLoginPage;
    protected static RegisterPage objRegisterPage;
    protected static ProfilePage objProfilePage;
    protected static ForgotPasswordPage objForgotPasswordPage;

    static UserRequest user;
    static APIActions apiActions;

    @Step("Prepare new user: register and back to home page")
    public static void prepareNewUser() {
        objHomePage.clickLoginToAccountButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.clickRegisterButton();
        objRegisterPage.waitForVisibilityOfElement(objRegisterPage.fieldName);

        user = UserRequest.getRandomUser(); // подготовим объект юзера с рандомными данными
        objRegisterPage.register(user.getName(), user.getEmail(), user.getPassword()); // регистрируем юзера
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objHeaderPage.clickLogo(); // возвращаемся на главную
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonLoginToAccount);
        Assert.assertEquals("Ожидаемый URL не совпадает с фактическим", HomePage.HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Log in")
    public static void login() {
        objHomePage.clickLoginToAccountButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.login(user.getEmail(), user.getPassword());
        // кнопка "Оформить заказ" есть только у авторизованных пользователей. проверим ее отображение
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonPlaceOrder);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder));
    }
}
