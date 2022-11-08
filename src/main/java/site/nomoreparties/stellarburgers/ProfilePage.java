package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{

    protected ProfilePage(WebDriver driver) {
        super(driver);
    }

    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    protected By buttonLogout = By.xpath(".//button[text()='Выход']");

    public void clickLogoutButton() {
        driver.findElement(buttonLogout).click();
    }
}
