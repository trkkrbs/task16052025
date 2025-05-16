package pages;

import constants.CareersPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CareersPage {
    WebDriver driver;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locations başlığını bulup sayfada görünür hale getiren ve görüntülenme durumunu döndüren metod
    public boolean isLocationsBlockDisplayed() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Locations')]")));

            // Elementi sayfada görünür yapmak için scroll et
            js.executeScript("arguments[0].scrollIntoView(true);", element);

            // Kısa bir bekleme yapabiliriz (opsiyonel)
            Thread.sleep(500);

            return element.isDisplayed();

        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }

    public boolean isTeamsBlockDisplayed() {
        return driver.findElement(CareersPageLocators.TEAMS_BLOCK).isDisplayed();
    }

    public boolean isLifeAtInsiderBlockDisplayed() {
        return driver.findElement(CareersPageLocators.LIFE_AT_INSIDER_BLOCK).isDisplayed();
    }

    public void goToQualityAssurance() {
        driver.findElement(CareersPageLocators.QUALITY_ASSURANCE_LINK).click();
    }
}