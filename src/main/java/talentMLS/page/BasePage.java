package talentMLS.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import talentMLS.driver.Driver;
import talentMLS.entity.Sections;
import talentMLS.helper.WebElementActions;


public abstract class BasePage {
    public WebElementActions webElementActions = new WebElementActions();
    public WebDriver driver = Driver.getDriver();
    public Sections sections = new Sections();



    public BasePage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }
}
