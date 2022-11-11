package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.WebStorage;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {

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
        objForgotPasswordPage = new ForgotPasswordPage(driver);
        driver.get(HomePage.HOME_PAGE_URL);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        prepareNewUser();
    }

    @Test
    @DisplayName("Login with 'Login to account' button positive test")
    public void loginWithButtonLoginToAccountPositiveTest() {
        objHomePage.clickLoginToAccountButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.login(user.getEmail(), user.getPassword());
        // кнопка "Оформить заказ" есть только у авторизованных пользователей. проверим ее отображение
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonPlaceOrder);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder));
    }

    @Test
    @DisplayName("Login with 'Profile' button positive test")
    public void loginWithButtonProfilePositiveTest() {
        objHeaderPage.clickProfileButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.login(user.getEmail(), user.getPassword());
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonPlaceOrder);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder));
    }

    @Test
    @DisplayName("Login with button on registration form positive test")
    public void loginWithButtonOnRegistrationFormPositiveTest() {
        objHomePage.clickLoginToAccountButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.clickRegisterButton();
        objRegisterPage.waitForVisibilityOfElement(objRegisterPage.fieldName);

        objRegisterPage.clickLoginButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.login(user.getEmail(), user.getPassword());
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonPlaceOrder);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder));
    }

    @Test
    @DisplayName("Login with button on 'Forgot password' form positive test")
    public void loginWithButtonOnForgotPasswordFormPositiveTest() {
        objHomePage.clickLoginToAccountButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.clickForgotPasswordButton();
        objForgotPasswordPage.waitForVisibilityOfElement(objForgotPasswordPage.buttonRestore);

        objForgotPasswordPage.clickLoginButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.login(user.getEmail(), user.getPassword());
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonPlaceOrder);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder));
    }

    @After
    public void logout() {
        // если залогинены, то разлогинимся и вернемся на главную
        if (objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder)) {
            objHeaderPage.clickProfileButton();

            objProfilePage.waitForVisibilityOfElement(objProfilePage.buttonLogout);
            objProfilePage.clickLogoutButton();
            objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

            objHeaderPage.clickLogo(); // возвращаемся на главную
            objHomePage.waitForVisibilityOfElement(objHomePage.buttonLoginToAccount);
            Assert.assertEquals("Ожидаемый URL не совпадает с фактическим", HomePage.HOME_PAGE_URL, driver.getCurrentUrl());
        } else { // в противном случае (если какой-то тест упадет) просто вернемся на главную
            objHeaderPage.clickLogo();
            objHomePage.waitForVisibilityOfElement(objHomePage.buttonLoginToAccount);
        }
    }

    @After
    public void clearBrowserData() {
        driver.manage().deleteAllCookies();
        ((WebStorage) driver).getSessionStorage().clear();
        ((WebStorage) driver).getLocalStorage().clear();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
