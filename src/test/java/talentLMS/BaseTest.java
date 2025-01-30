package talentLMS;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.enams.AdminSection;
import talentLMS.entity.User;
import talentLMS.page.dashboard.DashboardPage;
import talentLMS.page.users.UserPage;
import talentLMS.entity.Courses;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.login.LoginPage;
import talentLMS.utils.randomEntityUtils.RandomUserGenerator;

public abstract class BaseTest {
    public WebDriver driver;
    LoginPage loginPage = new LoginPage();
    UserPage userPage = new UserPage();
    public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    User randomUser = randomUserGenerator.randomUser();
    CoursesPage coursesPage = new CoursesPage();
    Courses courses = new Courses();
    DashboardPage dashboardPage = new DashboardPage();

    @BeforeSuite
    public void beforeSuite() {

        driver = Driver.getDriver();
    }

}
