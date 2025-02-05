package talentLMS.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import talentLMS.driver.Driver;
import talentLMS.helper.WebElementActions;

public abstract class BasePage {
    public WebElementActions webElementActions = new WebElementActions();
    public WebDriver driver = Driver.getDriver();


    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}