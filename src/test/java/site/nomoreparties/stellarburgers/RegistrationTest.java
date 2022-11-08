package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationTest extends BaseTest {

    @Before
    public void setUp() {
        // для запуска тестов в яндекс.браузере
        //System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        driver = new ChromeDriver();
        objHeaderPage = new HeaderPage(driver);
        objHomePage = new HomePage(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        driver.get(HomePage.HOME_PAGE_URL);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Register a new user positive test")
    public void registerNewUserPositiveTest() {
        navigateToRegisterPage();
        user = User.getRandomUser(); // подготовим объект юзера с рандомными данными
        objRegisterPage.register(user.getName(), user.getEmail(), user.getPassword());

        // на случай если после регистрации автоматически не перекинет на страницу авторизации, идем туда принудительно
        driver.get(LoginPage.LOGIN_PAGE_URL);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        // авторизуемся чтобы убедиться что юзер создался
        objLoginPage.login(user.getEmail(), user.getPassword());
        // кнопка "Оформить заказ" есть только у авторизованных пользователей. проверим ее видимость
        objHomePage.waitForVisibilityOfElement(objHomePage.buttonPlaceOrder);
        Assert.assertTrue(objHomePage.isElementDisplayed(objHomePage.buttonPlaceOrder));
    }

    @Test
    @DisplayName("Try to register a new user with password less than six symbols then error")
    public void tryRegisterNewUserWithPasswordLessSixSymThenError() {
        navigateToRegisterPage();
        user = User.getRandomUserWithPasswordLessSixSymbols(); // подготовим юзера с коротким паролем до 6 символов
        objRegisterPage.register(user.getName(), user.getEmail(), user.getPassword());
        objRegisterPage.waitForVisibilityOfElement(objRegisterPage.errorInvalidPassword);
        Assert.assertTrue(objRegisterPage.isElementDisplayed(objRegisterPage.errorInvalidPassword));
    }

    @Step("Navigate to register page")
    public void navigateToRegisterPage() {
        objHomePage.clickLoginToAccountButton();
        objLoginPage.waitForVisibilityOfElement(objLoginPage.buttonForgotPassword);

        objLoginPage.clickRegisterButton();
        objRegisterPage.waitForVisibilityOfElement(objRegisterPage.fieldName);
        Assert.assertEquals("Ожидаемый URL не совпадает с фактическим", RegisterPage.REGISTER_PAGE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
