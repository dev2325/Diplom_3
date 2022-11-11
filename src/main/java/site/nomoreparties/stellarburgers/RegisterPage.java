package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    protected RegisterPage(WebDriver driver) {
        super(driver);
    }

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    protected By fieldName = By.xpath(".//label[text()='Имя']/parent::div/input");
    protected By fieldEmail = By.xpath(".//label[text()='Email']/parent::div/input");
    protected By fieldPassword = By.xpath(".//input[@name='Пароль']");

    protected By buttonRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    protected By buttonLogin = By.xpath(".//*[@href='/login']");

    protected By errorInvalidPassword = By.xpath(".//p[text()='Некорректный пароль']");

    @Step("Click 'Login' button")
    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Click 'Register' button")
    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    @Step("Set name value to name field")
    public void setName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Set email value to email field")
    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Set password value to password field")
    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Register")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
}
