package insider.tests.task;

import insider.pages.InsiderPage;
import insider.utilities.AllureReportListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import insider.utilities.ConfigReader;
import insider.utilities.Driver;
import insider.utilities.ReusableMethods;

@Listeners({AllureReportListener.class})
public class C01 {

    InsiderPage insiderPage = new InsiderPage();
    //Class seviyesinde insiderPage classini tanimlayarak tum testlerde tekrar tekrar obje olusturmanin onune geciyorum.

    @Test
    public void test01() {
        //Visit https://useinsider.com/ and check Insider home page is opened or not
        Driver.getDriver().get(ConfigReader.getProperty("insiderUrl"));
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://useinsider.com/");
    }

    /*
    1) allure reports icin pom.xml 'e mvn.repository sitesinden dependency' leri ekliyoruz.
    2) @Description ve @Severity taglari ile allure report eklemesi yapiyoruz.
    3) Test adimlarinda allure.step ("aciklama") her bir info'yu giriyoruz.
    4) Testi calistiriyoruz.
    4) allure reports dosyalari kendiliginden proje seviyesinde olusan allure-results package'in icinde konumlanir.
    5) Asagida terminalde allure serve  komutunu calistiriyoruz. //allure serve allure-results
    6) Acilan pencerede raporlari detayli bir sekilde gozlemleyebiliriz.
     */
    @Test
    @Description("Bu testte career page teki; locations, teams, life at insider blocklarinin goruldugunu test edecegiz")
    @Severity(SeverityLevel.CRITICAL)
    public void test02() {
        //Select the “Company” menu in the navigation bar, select “Careers”
        //and check Career page, its Locations, Teams, and Life at Insider blocks are open or not
        Driver.getDriver().get(ConfigReader.getProperty("insiderUrl"));
        insiderPage.cookiesDeclineAll.click();
        ReusableMethods.actionsMoveToElement(insiderPage.companyDropDown);
        ReusableMethods.visibleWait(insiderPage.careers, 2);
        ReusableMethods.screenShot("insider");
        insiderPage.careers.click();
        ReusableMethods.wait(2);
        Allure.step("Careers Page sayfasina gidildi");

        ReusableMethods.actionsMoveToElement(insiderPage.teamsLink);
        Assert.assertTrue(insiderPage.teamsLink.isDisplayed());
        ReusableMethods.screenShotOfWebElement(insiderPage.teamsLink);
        ReusableMethods.wait(1);
        Allure.step("teamsLink goruldu");


        ReusableMethods.scroll(insiderPage.locationsBlock);
        ReusableMethods.visibleWait(insiderPage.locationsBlock,1);
        Assert.assertTrue(insiderPage.locationsBlock.isDisplayed());
        ReusableMethods.screenShotOfWebElement(insiderPage.locationsBlock);
        ReusableMethods.wait(1);
        Allure.step("locations block goruldu");


        ReusableMethods.scroll(insiderPage.lifeAtInsiderBlock);
        ReusableMethods.visibleWait(insiderPage.lifeAtInsiderBlock,1);
        Assert.assertTrue(insiderPage.lifeAtInsiderBlock.isDisplayed());
        ReusableMethods.screenShotOfWebElement(insiderPage.lifeAtInsiderBlock);
        ReusableMethods.wait(1);
        Allure.step("lifeatinsider block goruldu");


    }

    @Test
    @Description("Bu test fail aliyor.Dolayisiyla otomatik olarak allure report classi yardimiyla screen shot alinacak")
    @Severity(SeverityLevel.CRITICAL)
    public void test03() {
        //Go to https://useinsider.com/careers/quality-assurance/,
        //Click “See all QA jobs”,
        //Filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”,
        //Check the presence of the jobs list


        //Go to https://useinsider.com/careers/quality-assurance/,
        Driver.getDriver().get(ConfigReader.getProperty("insiderQualityAssuranceUrl"));

        //Bu kisimda sayfada acilan cookies seeAllQAJobsLink elementinin onune gececeginden,
        //ElementClickInterceptedException hatasi alabiliriz.
        //Cookies'i accept ya da decline etmeliyiz.
        ReusableMethods.visibleWait(insiderPage.cookieDecline, 3);
        insiderPage.cookieDecline.click();
//        ReusableMethods.screenShot("AfterCookieDecline");
//        ReusableMethods.addScreenshotToAllureReport();

        //Click “See all QA jobs”,
        insiderPage.seeAllQAJobsLink.click();

        //Filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”,
        ReusableMethods.visibleWait(insiderPage.locationFilter, 3);
        insiderPage.locationFilter.click();
        ReusableMethods.wait(5);
        for (WebElement option : insiderPage.locationOptions) {
            if (option.getText().equals("Istanbul, Turkiye")) {
                option.click();
                break;
            }
        }
        Assert.assertEquals(insiderPage.textLocation.getText(), "Istanbul, Turkiye", "location Istanbul, Turkiye secilemedi");

        //Department filtresini aç
        insiderPage.departmentFilter.click();
        ReusableMethods.visibleWait(insiderPage.departmentQA, 3);
        ReusableMethods.wait(5);
        for (WebElement option : insiderPage.departmentOptions) {
            if (option.getText().equals("Quality Assurance")) {
                option.click();
                break;
            }
        }
        Assert.assertEquals(insiderPage.textDepartment.getText(), "Quality Assurance", "department Quality Assurance secilemedi");


        //Check the presence of the jobs list
        ReusableMethods.visibleWait(insiderPage.jobListContainer, 3);

        //Liste boş değilse test başarılıdır
        Assert.assertTrue(insiderPage.jobListItems.size() > 0, "Filtrelenen iş ilanı bulunamadı!");

        // (İsteğe bağlı) Tüm job kartlarını yazdır
        for (WebElement w : insiderPage.jobListItems) {
            System.out.println(w.getText());
        }
    }
}
