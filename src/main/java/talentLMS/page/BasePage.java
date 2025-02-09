package talentLMS.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;
import talentLMS.entity.Sections;
import talentLMS.helper.WebElementActions;
import talentLMS.entity.Component;

import java.time.Duration;

public abstract class BasePage {
    public WebElementActions webElementActions = new WebElementActions();
    public WebDriver driver = Driver.getDriver();
    public Sections sections = new Sections();


    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}