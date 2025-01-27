import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.RandomUserGenerator;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.helper.WebElementActions;
import talentLMS.page.LoginPage;
import talentLMS.page.UsersPage;
import talentLMS.page.userRole.AdministratorUserRole;
import talentLMS.page.userRole.InstructorUserRole;
import talentLMS.page.userRole.LearnerUserRole;
import talentLMS.page.userRole.SwitchRolePage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    public LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    UsersPage usersPage = new UsersPage();
    AdministratorUserRole administratorUserRole = new AdministratorUserRole();
    InstructorUserRole instructorUserRole =new InstructorUserRole();
    LearnerUserRole learnerUserRole = new LearnerUserRole();
    SwitchRolePage switchRolePage = new SwitchRolePage();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://agema.talentlms.com/dashboard");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password"));
    }


    @AfterTest
    public void closeDriver (){
        driver.close();
        driver.quit();
    }

}
