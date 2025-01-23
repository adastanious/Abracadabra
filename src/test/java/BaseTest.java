import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import talentLMS.driver.Driver;
import talentLMS.helper.WebElementActions;
import talentLMS.page.LoginPage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();

    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://arka.talentlms.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

}
