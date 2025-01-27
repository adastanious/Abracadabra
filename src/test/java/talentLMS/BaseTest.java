package talentLMS;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.RandomUserGenerator;
import talentLMS.entity.Sections;
import talentLMS.entity.User;
import talentLMS.helper.WebElementActions;
import talentLMS.page.UserPage.Users;
import talentLMS.page.login.LoginPage;

public abstract class BaseTest {
    public WebDriver driver;

    WebElementActions webElementActions = new WebElementActions();
    LoginPage loginPage = new LoginPage();
    Sections sections = new Sections();
    Users userPage = new Users();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    User randomUser = randomUserGenerator.randomUser();

    @BeforeSuite
    public void beforeSuite() {

        driver = Driver.getDriver();
    }
}
