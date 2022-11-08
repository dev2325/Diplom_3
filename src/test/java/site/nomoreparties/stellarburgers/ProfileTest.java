package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ProfileTest extends BaseTest {

    @BeforeClass
    public static void setUp() {
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
        prepareNewUser();
        login();
    }

    @Test
    @DisplayName("Navigate to profile page positive test")
    public void navigateToProfilePagePositiveTest() {
        objHeaderPage.clickProfileButton();
        objProfilePage.waitForVisibilityOfElement(objProfilePage.buttonLogout);
        Assert.assertEquals("Ожидаемый URL не совпадает с фактическим", ProfilePage.PROFILE_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Navigate from profile page to BurgerConstructor using Constructor button positive test")
    public void navigateFromProfileWithConstructorButtonPositiveTest() {
        objHeaderPage.clickProfileButton();
        objProfilePage.waitForVisibilityOfElement(objProfilePage.buttonLogout);

        objHeaderPage.clickConstructorButton();
        objHomePage.waitForVisibilityOfElement(objHomePage.titleBuildBurger);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.titleBuildBurger));
    }

    @Test
    @DisplayName("Navigate from profile page to BurgerConstructor using logo positive test")
    public void navigateFromProfileWithLogoButtonPositiveTest() {
        objHeaderPage.clickProfileButton();
        objProfilePage.waitForVisibilityOfElement(objProfilePage.buttonLogout);

        objHeaderPage.clickLogo();
        objHomePage.waitForVisibilityOfElement(objHomePage.titleBuildBurger);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.titleBuildBurger));
    }

    @After
    public void navigateToHomePage() {
        objHeaderPage.clickLogo(); // после каждого теста возвращаемся на главную
        objHomePage.waitForVisibilityOfElement(objHomePage.titleBuildBurger);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
