package base;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void getDriver(String url){
        driver.get(url);
    }
    public WebElement find(By locator){
        return driver.findElement(locator);
    }
    public  void clickToWebElement (By locator){
        find(locator).click();
    }
    public void searchInputMethod(String input,By locator) {
        find(locator).clear();
        find(locator).sendKeys(input, Keys.ENTER);
    }
    public WebElement isElementDisplayed (By locator){
        WebElement element = find(locator);
        Assert.assertTrue(element.isDisplayed());
        return element;
    }
    public String getTextMethodString(By locator) {
        String text = find(locator).getText();
        return text;
    }
    public void popUpCheck(String amount,By locator){
        String actualResult=getTextMethodString(locator);
        Assert.assertTrue(actualResult.contains(amount));
    }
}