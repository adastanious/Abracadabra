package talentLMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.Category;
import talentLMS.entity.Courses;
import talentLMS.page.categoriesPage.CategoriesPage;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.userRole.AdministratorUserRole;
import talentLMS.page.userRole.Component;
import talentLMS.page.userRole.InstructorUserRole;
import talentLMS.page.userRole.LearnerUserRole;
import talentLMS.page.userTypes.UserTypes;
import talentLMS.page.users.UserPage;
import talentLMS.entity.User;
import talentLMS.entity.Sections;
import talentLMS.helper.WebElementActions;
import talentLMS.page.login.LoginPage;
import talentLMS.utils.randomEntityUtils.RandomUserGenerator;
import talentLMS.utils.randomEntityUtils.RandomUserTypeGenerator;

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
    CategoriesPage categoriesPage = new CategoriesPage();
    Category category = new Category();
    public AdministratorUserRole administratorUserRole = new AdministratorUserRole();
    public InstructorUserRole instructorUserRole = new InstructorUserRole();
    public LearnerUserRole learnerUserRole = new LearnerUserRole();
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public RandomUserTypeGenerator randomUserTypeGenerator = new RandomUserTypeGenerator();
    public UserTypes userTypes = new UserTypes();

    @BeforeSuite
    public void beforeSuite() {

        driver = Driver.getDriver();
    }

}
