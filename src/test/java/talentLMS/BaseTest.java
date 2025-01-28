package talentLMS;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.Courses;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.users.UserPage;
import talentLMS.entity.User;
import talentLMS.entity.Sections;
import talentLMS.helper.WebElementActions;
import talentLMS.page.login.LoginPage;
import talentLMS.utils.randomEntityUtils.RandomUserGenerator;

public abstract class BaseTest {
    public WebDriver driver;

    WebElementActions webElementActions = new WebElementActions();
    LoginPage loginPage = new LoginPage();
    Sections sections = new Sections();
    UserPage userPage = new UserPage();
    public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    User randomUser = randomUserGenerator.randomUser();
    CoursesPage coursesPage = new CoursesPage();
    Courses courses = new Courses();

    @BeforeSuite
    public void beforeSuite() {

        driver = Driver.getDriver();
    }
}
