package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    protected By fieldEmail = By.xpath(".//input[@name='name']");
    protected By fieldPassword = By.xpath(".//input[@name='Пароль']");

    protected By buttonLogin = By.xpath(".//button[text()='Войти']");
    protected By buttonRegister = By.xpath(".//*[@href='/register']");
    protected By buttonForgotPassword = By.xpath(".//*[@href='/forgot-password']"); // уникальный элемент

    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Login' button")
    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Click 'Register' button")
    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    @Step("Click 'Forgot password' button")
    public void clickForgotPasswordButton() {
        driver.findElement(buttonForgotPassword).click();
    }

    @Step("Set email value to email field")
    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Set password value to password field")
    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Log in")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}
