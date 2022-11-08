package site.nomoreparties.stellarburgers;

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

    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    public void clickForgotPasswordButton() {
        driver.findElement(buttonForgotPassword).click();
    }

    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}
