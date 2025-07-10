package insider.tests.task;

import insider.utilities.AllureReportListener;
import insider.utilities.Driver;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureReportListener.class})
public class FailScreenshotTest2 {

    @Test
    @Description("Bu class ve testi fail olan testlerde allure reports'un screenshot alabildigini test edebilmek icin olusturdum.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Google sayfasina gidip Assert.fail() methodu ile hatali sonuc al")
    @Step("Kullanici google anasayfasina gider")
    public void sampleFailingTest() {
        try {
            Assert.fail("Bu test bilerek fail oldu!");
        } catch (AssertionError e) {
            takeScreenshot();
            throw e;
        }
    }

    @Attachment(value = "Hata Ekran Görüntüsü", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
