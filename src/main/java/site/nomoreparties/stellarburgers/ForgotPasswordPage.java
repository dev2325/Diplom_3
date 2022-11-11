package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    protected ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    protected By buttonLogin = By.xpath(".//*[@href='/login']");
    protected By buttonRestore = By.xpath(".//button[text()='Восстановить']");

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

}
