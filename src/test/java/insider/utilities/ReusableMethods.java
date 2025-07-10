package insider.utilities;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReusableMethods {

    //HARD WAIT METHOD
    public static void wait(int saniye){
        try{
            Thread.sleep(saniye*1000);;
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    //Alert ACCEPT
    public static void alertAccept(){
        Driver.getDriver().switchTo().alert().accept();
    }

    //Alert DISMISS
    public static void alertDismiss(){
        Driver.getDriver().switchTo().alert().dismiss();
    }

    //Alert getText()
    public static  void alertText(){
        Driver.getDriver().switchTo().alert().getText();
    }

    //Alert promptBox
    public static void alertPrompt(String text){
        Driver.getDriver().switchTo().alert().sendKeys(text);
    }
    //DropDown VisibleText
    /*
    Select select2 = new Select(gun);
    select2.selectByVisibleText("7");
    //ddmVisibleText(gun,"7"); --> Yukaridaki kullanim yerine sadece method ile handle edebilirim.
     */

    //DropDown VisibleText
    public static void ddmVisibleText(WebElement ddm, String secenek){
        Select select = new Select(ddm);
        select.selectByVisibleText(secenek);
    }

    //DropDown Index
    public static void ddmIndex(WebElement ddm, int index){
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }

    //DropDown Value
    public static void ddmValue(WebElement ddm, String secenek){
        Select select = new Select(ddm);
        select.selectByValue(secenek);
    }

    //SwitchToWindow1
    public static void switchToWindow(int sayi){
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public static void window(int sayi){
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
    }

    //MoveToElement (Hover) Methodu
    public static void actionsMoveToElement(WebElement element) {
        new Actions(Driver.getDriver()).moveToElement(element).perform();
    }

    //EXPLICIT WAIT METHODS
    //Visible Wait
    public static void visibleWait(WebElement element, int sayi){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    //VisibleElementLocator Wait
    public static WebElement visibleWait(By locator, int sayi){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Alert Wait
    public static void alertWait(int sayi){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //Tum Sayfa ScreenShot
    public static void screenShot(String name){
        String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
        TakesScreenshot ts = (TakesScreenshot)Driver.getDriver();
        String dosyaYolu = System.getProperty("user.dir")+ "/src/test/java/insider/testOutputs/screenshots/" + name + date + ".png";
        try{
            Files.write(Paths.get(dosyaYolu), ts.getScreenshotAs(OutputType.BYTES));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    //WebElement ScreenShot
    public static void screenShotOfWebElement(WebElement webElement){
        String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
        TakesScreenshot ts = (TakesScreenshot)Driver.getDriver();
        String dosyaYolu = System.getProperty("user.dir") + "/src/test/java/insider/testOutputs/webElementScreenshots/" + date + ".png";
        try{
            Files.write(Paths.get(dosyaYolu), webElement.getScreenshotAs(OutputType.BYTES));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    //WebTable (Hücre Verisi Yazdırma)
    public static void printData(int satir, int sutun) {
        WebElement satirSutun = Driver.getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + satir + "]/td[" + sutun + "]"));
        System.out.println(satirSutun.getText());
    }

    // Click Method (Normal click başarısızsa JS ile click)
    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    //JS Scroll (Belirli bir elemente kaydırma)
    public static void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //JS Sayfa Sonuna Scroll
    public static void scrollEnd() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //JS Sayfa Başına Scroll
    public static void scrollHome() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    //JS SendKeys
    public static void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);
    }

    //JS SetAttributeValue
    public static void setAttributeJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }

    //JS GetAttributeValue
    public static void getValueByJS(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String attribute_Value = (String) js.executeScript("return document.getElementById('" + id + "').getAttribute('" + attributeName + "')");
        System.out.println("Attribute Value: = " + attribute_Value);
    }

    //Bu method ile allure reporta screenshot eklenir
    public static File addScreenshotToAllureReport(){
        File screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Reusable Class icindeki addScreenshotToAllureReport() ile ekran goruntusu eklendi");
        return screenshotAs;
    }







}
