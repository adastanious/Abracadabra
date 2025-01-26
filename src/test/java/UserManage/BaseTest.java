package UserManage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import talentMLS.driver.Driver;
import talentMLS.page.login.LoginPage;
import talentMLS.page.users.UserPage;
import talentMLS.utils.randomEntityUtils.RandomUserGenerator;

public abstract class BaseTest {
    public WebDriver driver;
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }
}
