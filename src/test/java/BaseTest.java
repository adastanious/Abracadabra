import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import talentLMS.driver.Driver;
import talentLMS.entity.Courses;
import talentLMS.helper.WebElementActions;
import talentLMS.page.CoursesPage;
import talentLMS.page.LoginPage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    public LoginPage loginPage = new LoginPage();
    public Courses courses = new Courses();
    public CoursesPage coursesPage = new CoursesPage();

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.getDriver(); // Инициализация драйвера
        driver.manage().deleteAllCookies();
        driver.get("https://abracadabra.talentlms.com/index"); // Переход на стартовую страницу
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Ожидание
    }
}
