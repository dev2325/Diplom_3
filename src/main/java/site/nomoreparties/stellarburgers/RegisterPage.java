package site.nomoreparties.stellarburgers;

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

    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    public void setName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
}
