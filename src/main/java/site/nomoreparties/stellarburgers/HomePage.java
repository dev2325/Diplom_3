package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    protected HomePage(WebDriver driver) {
        super(driver);
    }

    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    protected By buttonLoginToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    protected By buttonPlaceOrder = By.xpath(".//button[text()='Оформить заказ']"); // есть только у авторизованных

    protected By titleBuildBurger = By.xpath(".//h1[text()='Соберите бургер']");

    protected By tabBuns = By.xpath(".//span[text()='Булки']");
    protected By tabSauces = By.xpath(".//span[text()='Соусы']");
    protected By tabMain = By.xpath(".//span[text()='Начинки']");
    protected By tabBunsSelected =
            By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    protected By tabSaucesSelected =
            By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    protected By tabMainSelected =
            By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    public void clickLoginToAccountButton() {
        driver.findElement(buttonLoginToAccount).click();
    }

    public void clickTabBuns() {
        driver.findElement(tabBuns).click();
    }

    public void clickTabSauces() {
        driver.findElement(tabSauces).click();
    }

    public void clickTabMain() {
        driver.findElement(tabMain).click();
    }
}
