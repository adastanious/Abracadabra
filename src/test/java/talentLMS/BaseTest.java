package talentLMS;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.*;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.accountAndSettings.BasicSettingsPage;
import talentLMS.page.categoriesPage.CategoriesPage;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.dashboard.DashboardPage;
import talentLMS.page.userRole.AdministratorUserRole;
import talentLMS.page.userRole.InstructorUserRole;
import talentLMS.page.userRole.LearnerUserRole;
import talentLMS.page.users.UserPage;
import talentLMS.helper.WebElementActions;
import talentLMS.page.login.LoginPage;
import talentLMS.utils.randomEntityUtils.RandomSettingsGenerator;
import talentLMS.utils.randomEntityUtils.RandomUserGenerator;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;
    public WebElementActions webElementActions = new WebElementActions();
    public LoginPage loginPage = new LoginPage();
    Sections sections = new Sections();
    public UserPage userPage = new UserPage();
    public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    public User randomUser = randomUserGenerator.randomUser();
    CoursesPage coursesPage = new CoursesPage();
    Courses courses = new Courses();
    public Component component = new Component();
    public CategoriesPage categoriesPage = new CategoriesPage();
    public Category category = new Category();
    public AdministratorUserRole administratorUserRole = new AdministratorUserRole();
    public InstructorUserRole instructorUserRole = new InstructorUserRole();
    public LearnerUserRole learnerUserRole = new LearnerUserRole();
    public DashboardPage dashboardPage = new DashboardPage();
    BasicSettingsPage basicSettingsPage = new BasicSettingsPage();
    RandomSettingsGenerator randomSettingsGenerator = new RandomSettingsGenerator();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }

    @BeforeClass
    public void beforeClass(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password"));
    }
//    @AfterClass
//    public void afterClass(){
//        driver.close();
//        driver.quit();
//    }

}
