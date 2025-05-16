package pages;

import constants.HomePageLocators;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageOpened() {
        return driver.getTitle().toLowerCase().contains("insider");
    }

    public void clickCompanyMenu() {
        driver.findElement(HomePageLocators.COMPANY_MENU).click();
    }

    public void clickCareersMenu() {
        driver.findElement(HomePageLocators.CAREERS_MENU_ITEM).click();
    }
}
