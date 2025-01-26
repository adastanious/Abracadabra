import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.RandomUserGenerator;
import talentLMS.helper.WebElementActions;
import talentLMS.page.LoginPage;
import talentLMS.page.UsersPage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    public LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    UsersPage usersPage = new UsersPage();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://lolowka16.talentlms.com/index");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
