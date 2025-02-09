package talentLMS.page.eventsEngine;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Notification;
import talentLMS.enums.Event;
import talentLMS.enums.Recipient;
import talentLMS.page.BasePage;
import java.io.IOException;
import java.util.List;
/**
 * @author Arsen Kaiypbekov
 */
public class AddNotificationPage extends BasePage {
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
    @FindBy(xpath = "//label[@class ='checkbox']/input[@name ='status']")
    public WebElement statusCheckbox;
    @FindBy(xpath = "//div/div/input[@value = 'Create notification']")
    public WebElement addNotification;
    @FindBy(xpath = "//a[@class ='brand']")
    public WebElement home;

    @FindBy(xpath = "//label[(text() = 'Name')]/ancestor::div/div/span/span[@class = 'help-inline']")
    public WebElement wrongNameDataMessage;
    @FindBy(xpath = "//label[(text() = 'Event')]/ancestor::div/div/span/span[@class = 'help-inline']")
    public WebElement wrongEventDataMessage;
    @FindBy(xpath = "//label[(text() = 'Recipient')]/ancestor::div/div/span/span[@class = 'help-inline']")
    public WebElement wrongRecipienDataMessage;

    public AddNotificationPage inputName(String name){
        webElementActions.sendKeys(inputName,name);
        return this;
    }

    public AddNotificationPage selectEvent(Event event){
        webElementActions.click(showEventList);
        for (WebElement a: eventList){
            if(a.getText().contains(event.getDescription())){
                webElementActions.click(a);
            }
        }
        return this;
    }

    public AddNotificationPage selectRecipient(Recipient recipient) throws IOException {
        webElementActions.click(showRecipientList);
        for (WebElement b: availableRecipientList){
            if (b.getText().contains(recipient.getRecipient())){
                webElementActions.click(b);
                return this;
            }

            for (WebElement c: unavilableRecipientList){
                if (c.getText().contains(recipient.getRecipient())){
                    throw new IOException("This recipient is unselectable");
                }
            }
        }
        return this;
    }

    public AddNotificationPage inputMessageBox(String text){
        webElementActions.sendKeys(notificationMessageBox, text);
        return this;
    }

    public AddNotificationPage setStatus(boolean status){
        if(status){
           if(!statusCheckbox.isSelected()){
               webElementActions.click(statusCheckbox);
           }
           return this;
        }
        if(statusCheckbox.isSelected()){
            webElementActions.click(statusCheckbox);
        }
        return this;
    }

    public AddNotificationPage addNewEventNotification(Notification notification) throws IOException {
        inputName(notification.getName())
                .selectEvent(notification.getEvent())
                .selectRecipient(notification.getRecipient())
                .inputMessageBox(notification.getBody());
        return this;
    }

    public EventsEnginePage submit(){
        webElementActions.click(addNotification);
        return new EventsEnginePage();
    }
}
