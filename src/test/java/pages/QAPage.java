package pages;

import constants.QAPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class QAPage {
    WebDriver driver;

    public QAPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSeeAllQAJobs() {
        driver.findElement(QAPageLocators.SEE_ALL_QA_JOBS_BTN).click();
    }

    public void filterByLocation(String location) {
        WebElement locationDropdown = driver.findElement(QAPageLocators.LOCATION_FILTER);
        locationDropdown.click();
        locationDropdown.findElement(By.xpath(".//option[contains(text(),'" + location + "')]")).click();
    }

    public void filterByDepartment(String department) {
        WebElement departmentDropdown = driver.findElement(QAPageLocators.DEPARTMENT_FILTER);
        departmentDropdown.click();
        departmentDropdown.findElement(By.xpath(".//option[contains(text(),'" + department + "')]")).click();
    }

    public boolean isJobsListDisplayed() {
        return driver.findElements(QAPageLocators.JOB_LIST_ITEMS).size() > 0;
    }

    public List<WebElement> getAllJobs() {
        return driver.findElements(QAPageLocators.JOB_LIST_ITEMS);
    }

    public void clickFirstViewRole() {
        driver.findElement(QAPageLocators.VIEW_ROLE_BTN).click();
    }

    public By getJobPositionLocator() {
        return QAPageLocators.JOB_POSITION;
    }

    public By getJobDepartmentLocator() {
        return QAPageLocators.JOB_DEPARTMENT;
    }

    public By getJobLocationLocator() {
        return QAPageLocators.JOB_LOCATION;
    }
}
