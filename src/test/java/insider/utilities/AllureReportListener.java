package insider.utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureReportListener implements ITestListener {

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔹 onTestStart - Test başlıyor: " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ onTestSuccess - Test başarılı: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ onTestFailure - Test başarısız: " + getTestMethodName(result));

        WebDriver driver = Driver.getDriver();

        if (driver != null) {
            System.out.println("📸 Ekran görüntüsü alınıyor...");
            byte[] screenshot = attachScreenshot(driver); // ✅ return değeri kullanılıyor
        } else {
            System.out.println("⚠️ Driver null, ekran görüntüsü alınamadı.");
        }

        saveTextLog(getTestMethodName(result) + " testi başarısız oldu, ekran görüntüsü alındı.");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️ onTestSkipped - Test atlandı: " + getTestMethodName(result));
        saveTextLog(getTestMethodName(result) + " testi atlandı.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("⚠️ onTestFailedButWithinSuccessPercentage - Belirli başarı yüzdesiyle başarısız: " + getTestMethodName(result));
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🟢 onStart - Test seti başlatıldı: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🔴 onFinish - Test seti tamamlandı: " + context.getName());
    }

    // 🔽 Allure’a ekran görüntüsü olarak ekleme yapan method
    @Attachment(value = "📸 Hata Anı Ekran Görüntüsü", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("❗ Screenshot alınamadı: " + e.getMessage());
            return new byte[0];
        }
    }

    // 🔽 Allure’a text log olarak bilgi ekler
    @Attachment(value = "{0}", type = "text/plain")
    public String saveTextLog(String message) {
        return message;
    }
}
