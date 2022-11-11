package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.api.APIActions;

import java.util.concurrent.TimeUnit;

public class LogoutTest extends BaseTest {

    @Before
    public void setUp() {
        // для запуска тестов в яндекс.браузере
        //System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        driver = new ChromeDriver();
        objHeaderPage = new HeaderPage(driver);
        objHomePage = new HomePage(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objProfilePage = new ProfilePage(driver);
        driver.get(HomePage.HOME_PAGE_URL);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        apiActions = new APIActions();
        prepareNewUser();
        login();
    }

    @Test
    @DisplayName("Logout positive test")
    public void logoutPositiveTest() {
        objHeaderPage.clickProfileButton();
        objProfilePage.waitForVisibilityOfElement(objProfilePage.buttonLogout);

        // разлогинимся и по наличию кнопки 'Восстановить пароль' убедимся что вышли
        objProfilePage.clickLogoutButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);
        Assert.assertTrue(objLoginPage.isElementDisplayed(objLoginPage.buttonForgotPassword));
    }

    @After
    public void tearDown() {
        apiActions.deleteUser(user);
        driver.quit();
    }
}
