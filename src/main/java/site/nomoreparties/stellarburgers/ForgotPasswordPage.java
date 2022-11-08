package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    protected ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    protected By buttonLogin = By.xpath(".//*[@href='/login']");
    protected By buttonRestore = By.xpath(".//button[text()='Восстановить']");

    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

}
