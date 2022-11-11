package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{

    protected ProfilePage(WebDriver driver) {
        super(driver);
    }

    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    protected By buttonLogout = By.xpath(".//button[text()='Выход']");

    @Step("Click 'Logout' button")
    public void clickLogoutButton() {
        driver.findElement(buttonLogout).click();
    }
}
