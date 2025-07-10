package insider.pages;

import insider.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InsiderPage {
    public InsiderPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[text()='Decline All']")
    public  WebElement cookiesDeclineAll;
    @FindBy(xpath = "//a[contains(text(), 'Company')]")
    public WebElement companyDropDown;

    @FindBy(xpath = "//a[text()='Careers']")
    public WebElement careers;

    @FindBy(xpath = "//a[text()='See all teams']")
    public WebElement teamsLink;

    @FindBy(xpath = "//h3[contains(text(), 'Our Locations')]")
    public WebElement locationsBlock;

    @FindBy(xpath = "//h2[text()='Life at Insider']")
    public WebElement lifeAtInsiderBlock;

    @FindBy(xpath = "//a[@id='wt-cli-reject-btn']")
    public WebElement cookieDecline;

    @FindBy(xpath = "//a[text()='See all QA jobs']")
    public WebElement seeAllQAJobsLink;

    //Dropdown acan element
//    @FindBy(xpath = "//span[@id='select2-filter-by-location-container']")
//    public WebElement locationFilter;

//    @FindBy(xpath = "//span[@aria-labelledby='select2-filter-by-location-container']")
//    public WebElement locationFilter;

    @FindBy(xpath = "//span[@data-select2-id='1']")
    public WebElement locationFilter;

    @FindBy(xpath = "//ul[@id='location-options']//li") // Açılan seçenekler listesi (örnek)
    public List<WebElement> locationOptions;
    @FindBy(xpath = "//li[text()='Istanbul, Turkiye']")
    public WebElement locationIstanbul;
    @FindBy(xpath = "//span[@title='Istanbul, Turkiye']")
    public WebElement textLocation;

//    @FindBy(xpath = "//li[@id='select2-filter-by-location-result-s0b5-Istanbul, Turkiye']")
//    public WebElement locationIstanbul;

    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    public WebElement departmentFilter;

    @FindBy(xpath = "//li[@id='select2-filter-by-department-result-d9e4-Quality Assurance']")
    public WebElement departmentQA;

    @FindBy(xpath = "//ul[@id='select2-filter-by-department-results']")
    public  List<WebElement> departmentOptions;

    @FindBy(xpath = "//span[@title='Quality Assurance']")
    public WebElement textDepartment;




    @FindBy(id= "jobs-list")
    public WebElement jobListContainer;

//    @FindBy(xpath = "//div[@class='position-list-item-wrapper bg-light']")
//    public WebElement jobListItems;

    @FindBy(xpath = "//div[contains(@class, 'position-list-item')]")
    public List<WebElement> jobListItems;


}
