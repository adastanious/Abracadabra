package talentLMS.page.eventsEngine;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Notification;
import talentLMS.page.BasePage;
import java.io.IOException;
import java.util.List;
/**
 * @author Arsen Kaiypbekov
 */
public class AddEventPage extends BasePage {
    @FindBy (xpath = "//form/div/div/div/input[contains(@name,'name')]")
    public WebElement inputName;
    @FindBy(xpath = "(//div/a/span[@class = 'select2-arrow'])[1]")
    public WebElement showEventList;
    @FindBy(xpath = "(//div/a/span[@class = 'select2-arrow'])[2]")
    public WebElement showRecipientList;
    @FindBy (xpath = "//select/option")
    public List <WebElement> eventList;
    @FindBy(xpath = "//div/ul/li[@class = 'select2-results-dept-0 select2-result select2-result-selectable']")
    public List <WebElement> availableRecipientList;
    @FindBy(xpath = "//div/ul/li[@class  = 'select2-results-dept-0 select2-result select2-result-unselectable select2-disabled']")
    public List <WebElement> unavilableRecipientList;
    @FindBy(xpath = "//div/div/textarea[contains(@name,'notification')]")
    public WebElement notificationMessageBox;
    @FindBy(xpath = "//div/div/input[@value = 'Create notification']")
    public WebElement addNotification;
    @FindBy(xpath = "//a[@class ='brand']")
    public WebElement home;

    public AddEventPage addNewEventNotification(Notification notification) throws IOException {
        webElementActions.sendKeys(inputName,notification.getName())
                .click(showEventList);
        for (WebElement a: eventList){
            if(a.getText().contains(notification.getEvent().getDescription())){
                webElementActions.click(a);
            }
        }
        webElementActions.click(showRecipientList);

        for (WebElement b: availableRecipientList){
            if (b.getText().contains(notification.getRecipient().getRecipient())){
                webElementActions.click(b)
                        .sendKeys(notificationMessageBox, notification.getBody());
                return new AddEventPage();
            }

            for (WebElement c: unavilableRecipientList){
                if (c.getText().contains(notification.getRecipient().getRecipient())){
                    throw new IOException("This recipient is unselectable");
                }
            }
        }
        return new AddEventPage();
    }
    public EventsEnginePage submit(){
        webElementActions.click(addNotification);
        return new EventsEnginePage();
    }
}
