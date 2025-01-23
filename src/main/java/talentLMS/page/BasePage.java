package talentLMS.page;

import org.openqa.selenium.support.PageFactory;
import talentLMS.driver.Driver;
import talentLMS.helper.WebElementActions;

public abstract class BasePage {
    WebElementActions webElementActions = new WebElementActions();

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
