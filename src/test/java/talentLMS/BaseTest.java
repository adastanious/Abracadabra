package talentLMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.*;
import talentLMS.page.accountAndSettings.BasicSettingsPage;
import talentLMS.page.accountAndSettings.UsersPage;
import talentLMS.page.categoriesPage.CategoriesPage;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.dashboard.DashboardPage;
import talentLMS.page.userRole.AdministratorUserRole;
import talentLMS.page.userRole.Component;
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
    public UserPage userPage = new UserPage();
    public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    public User randomUser = randomUserGenerator.randomUser();
    public CoursesPage coursesPage = new CoursesPage();
    public Courses courses = new Courses();
    public Component component = new Component();
    public CategoriesPage categoriesPage = new CategoriesPage();
    public Category category = new Category();
    public AdministratorUserRole administratorUserRole = new AdministratorUserRole();
    public InstructorUserRole instructorUserRole = new InstructorUserRole();
    public LearnerUserRole learnerUserRole = new LearnerUserRole();
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public BasicSettingsPage basicSettingsPage = new BasicSettingsPage();
    public RandomSettingsGenerator randomSettingsGenerator = new RandomSettingsGenerator();
    public DashboardPage dashboardPage = new DashboardPage();
    public UsersPage settingsUsersPage = new UsersPage();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }

}
