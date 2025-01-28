package talentLMS;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;

import talentLMS.entity.User;
import talentLMS.page.users.UserPage;

import talentLMS.entity.Courses;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.entity.Sections;
import talentLMS.helper.WebElementActions;
import talentLMS.page.login.LoginPage;
import talentLMS.utils.randomEntityUtils.RandomUserGenerator;

public abstract class BaseTest {
    public WebDriver driver;

    public WebElementActions webElementActions = new WebElementActions();
    LoginPage loginPage = new LoginPage();
    Sections sections = new Sections();
    public UserPage userPage = new UserPage();
    public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    public User randomUser = randomUserGenerator.randomUser();
    User randomUser = randomUserGenerator.randomUser();
    CoursesPage coursesPage = new CoursesPage();
    Courses courses = new Courses();

    @BeforeSuite
    public void beforeSuite() {

        driver = Driver.getDriver();
    }
}
