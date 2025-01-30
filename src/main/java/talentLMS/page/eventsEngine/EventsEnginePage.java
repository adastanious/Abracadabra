package talentLMS.page.eventsEngine;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;
/**
 * @author Arsen Kaiypbekov
 */
public class EventsEnginePage extends BasePage {
    @FindBy(xpath = "//div/a[contains(text(),'Add notification')]")
    WebElement addEventBtn;

    public AddEventPage addEvent(){
        webElementActions.click(addEventBtn);
        return new AddEventPage();
    }
}
