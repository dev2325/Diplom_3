package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

    protected HeaderPage(WebDriver driver) {
        super(driver);
    }

    protected By logoMain = By.className("AppHeader_header__logo__2D0X2");

    protected By buttonConstructor = By.xpath(".//p[text()='Конструктор']");
    protected By buttonProfile = By.xpath(".//*[@href='/account']");

    @Step("Click logo")
    public void clickLogo() {
        driver.findElement(logoMain).click();
    }

    @Step("Click profile button")
    public void clickProfileButton() {
        driver.findElement(buttonProfile).click();
    }

    @Step("Click constructor button")
    public void clickConstructorButton() {
        driver.findElement(buttonConstructor).click();
    }
}
