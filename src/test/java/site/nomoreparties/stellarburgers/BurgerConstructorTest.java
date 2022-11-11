package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BurgerConstructorTest extends BaseTest {

    @BeforeClass
    public static void setUp() {
        // для запуска тестов в яндекс.браузере
        //System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        driver = new ChromeDriver();
        objHeaderPage = new HeaderPage(driver);
        objHomePage = new HomePage(driver);
        driver.get(HomePage.HOME_PAGE_URL);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Navigate to buns tab positive test")
    public void navigateToBunsTabPositiveTest() {
        // уйти с дефолтной вкладки 'Булки' на любую другую невыбранную
        if (objHomePage.isElementDisplayed(objHomePage.tabSaucesSelected)) {
            objHomePage.clickTabMain();
            objHomePage.waitForVisibilityOfElement(objHomePage.tabMainSelected);
        } else {
            objHomePage.clickTabSauces();
            objHomePage.waitForVisibilityOfElement(objHomePage.tabSaucesSelected);
        }
        objHomePage.clickTabBuns();
        objHomePage.waitForVisibilityOfElement(objHomePage.tabBunsSelected);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.tabBunsSelected));
    }

    @Test
    @DisplayName("Navigate to sauces tab positive test")
    public void navigateToSaucesTabPositiveTest() {
        objHomePage.clickTabSauces();
        objHomePage.waitForVisibilityOfElement(objHomePage.tabSaucesSelected);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.tabSaucesSelected));
    }

    @Test
    @DisplayName("Navigate to main tab positive test")
    public void navigateToMainTabPositiveTest() {
        objHomePage.clickTabMain();
        objHomePage.waitForVisibilityOfElement(objHomePage.tabMainSelected);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.tabMainSelected));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
