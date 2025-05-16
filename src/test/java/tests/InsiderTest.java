package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import pages.CareersPage;
import pages.HomePage;
import pages.QAPage;

import java.time.Duration;
import java.util.List;

public class InsiderTest {
    WebDriver driver;
    HomePage homePage;
    CareersPage careersPage;
    QAPage qaPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qaPage = new QAPage(driver);
    }

    @Test
    public void testInsiderCareerFlow() throws InterruptedException {
        driver.get("https://useinsider.com/");
        Assert.assertTrue("Home page did not open.", homePage.isHomePageOpened());

        homePage.clickCompanyMenu();
        Thread.sleep(500);
        homePage.clickCareersMenu();

        Assert.assertTrue("Locations block not displayed", careersPage.isLocationsBlockDisplayed());
        Assert.assertTrue("Teams block not displayed", careersPage.isTeamsBlockDisplayed());
        Assert.assertTrue("Life at Insider block not displayed", careersPage.isLifeAtInsiderBlockDisplayed());

        careersPage.goToQualityAssurance();

        qaPage.clickSeeAllQAJobs();
        qaPage.filterByLocation("Istanbul, Turkey");
        qaPage.filterByDepartment("Quality Assurance");

        Assert.assertTrue("Job list is empty after filtering", qaPage.isJobsListDisplayed());

        List<WebElement> jobs = qaPage.getAllJobs();

        for (WebElement job : jobs) {
            String position = job.findElement(qaPage.getJobPositionLocator()).getText();
            String department = job.findElement(qaPage.getJobDepartmentLocator()).getText();
            String location = job.findElement(qaPage.getJobLocationLocator()).getText();

            Assert.assertTrue("Position does not contain 'Quality Assurance'", position.contains("Quality Assurance"));
            Assert.assertTrue("Department does not contain 'Quality Assurance'", department.contains("Quality Assurance"));
            Assert.assertTrue("Location does not contain 'Istanbul, Turkey'", location.contains("Istanbul, Turkey"));
        }

        qaPage.clickFirstViewRole();

        Assert.assertTrue("Not redirected to Lever form", driver.getCurrentUrl().contains("lever"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
