import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.helper.WebElementActions;

public abstract class BaseTest {
    public WebDriver driver;

    WebElementActions webElementActions = new WebElementActions();

    @BeforeSuite
    public void beforeSuite() {

        driver = Driver.getDriver();
    }
}
