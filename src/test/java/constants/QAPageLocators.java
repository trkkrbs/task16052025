package constants;

import org.openqa.selenium.By;

public class QAPageLocators {
    public static final By SEE_ALL_QA_JOBS_BTN = By.xpath("//a[contains(text(),'See all QA jobs')]");
    public static final By LOCATION_FILTER = By.xpath("//select[@id='location-filter']");
    public static final By DEPARTMENT_FILTER = By.xpath("//select[@id='department-filter']");
    public static final By JOB_LIST_ITEMS = By.cssSelector(".job-listing-item");
    public static final By JOB_POSITION = By.cssSelector(".job-position");
    public static final By JOB_DEPARTMENT = By.cssSelector(".job-department");
    public static final By JOB_LOCATION = By.cssSelector(".job-location");
    public static final By VIEW_ROLE_BTN = By.xpath("//button[contains(text(),'View Role')]");
}
