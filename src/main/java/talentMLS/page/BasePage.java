package talentMLS.page;

import org.openqa.selenium.support.PageFactory;
import talentMLS.driver.Driver;
import talentMLS.helper.WebElementActions;

public abstract class BasePage {
    WebElementActions webElementActions = new WebElementActions();

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
