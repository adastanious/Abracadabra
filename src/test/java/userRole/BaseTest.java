package userRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.login.LoginPage;
import talentLMS.page.userRole.AdministratorUserRole;
import talentLMS.page.userRole.Component;
import talentLMS.page.userRole.InstructorUserRole;
import talentLMS.page.userRole.LearnerUserRole;

import java.time.Duration;

public class BaseTest {
    /**
     @author Turan, Agema, Nazik
     */
    public WebDriver driver;

    public LoginPage loginPage = new LoginPage();
    AdministratorUserRole administratorUserRole = new AdministratorUserRole();
    InstructorUserRole instructorUserRole =new InstructorUserRole();
    LearnerUserRole learnerUserRole = new LearnerUserRole();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Component component = new Component();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.doLogin(ConfigReader.getProperty("login"),ConfigReader.getProperty("password"));
    }
}

