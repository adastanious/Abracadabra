package talentLMS.UserManage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import talentMLS.driver.Driver;
import talentMLS.entity.Course;
import talentMLS.page.course.CoursePage;
import talentMLS.page.login.LoginPage;
import talentMLS.page.users.UserPage;
import talentMLS.utils.randomEntityUtils.RandomUserGenerator;

    public abstract class BaseTest {
        public WebDriver driver;
        public LoginPage loginPage = new LoginPage();
        public UserPage userPage = new UserPage();
        public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
        public CoursePage coursePage = new CoursePage();

        public Course course = new Course();

        @BeforeMethod
        public void beforeMethod() {
            driver = Driver.getDriver();
            driver.get("https://abracadabra.talentlms.com/index");
        }
    }

