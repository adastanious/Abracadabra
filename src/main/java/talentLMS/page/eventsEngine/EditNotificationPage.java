package talentLMS.page.eventsEngine;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Notification;
import talentLMS.enums.Event;
import talentLMS.enums.Recipient;
import talentLMS.page.BasePage;

import java.io.IOException;
import java.util.List;

public class EditNotificationPage extends BasePage {
    @FindBy(xpath = "//div/input[@name = 'name']")
    WebElement editNameCell;
    @FindBy(xpath = "//label[contains(text(),'Event')]/ancestor::div/div/div/a/span[@class = 'select2-arrow']")
    WebElement eventListDropdown;
    @FindBy(xpath = "//label[contains(text(),'Recipient')]/ancestor::div/div/div/a/span[@class = 'select2-arrow']")
    WebElement recipientListDropdown;
    @FindBy (xpath = "//select/option")
    public List<WebElement> eventList;
    @FindBy(xpath = "//div/ul/li[@class = 'select2-results-dept-0 select2-result select2-result-selectable']")
    public List <WebElement> availableRecipientList;
    @FindBy(xpath = "//div/ul/li[@class  = 'select2-results-dept-0 select2-result select2-result-unselectable select2-disabled']")
    public List <WebElement> unavilableRecipientList;
    @FindBy(xpath = "//div/div/textarea[contains(@name,'notification')]")
    public WebElement notificationMessageBox;

    public EditNotificationPage editNotificationName(String name){
        editNameCell.click();
        webElementActions.sendKeys(editNameCell, name);
        return this;
    }

    public EditNotificationPage selectEvent(Event event){
        webElementActions.click(eventListDropdown);
        for (WebElement a: eventList){
            if(a.getText().contains(event.getDescription())){
                webElementActions.click(a);
            }
        }
        return this;
    }

    public EditNotificationPage selectRecipient(Recipient recipient) throws IOException {
        webElementActions.click(recipientListDropdown);
        for (WebElement b: availableRecipientList){
            if (b.getText().contains(recipient.getRecipient())){
                webElementActions.click(b);
            }

            for (WebElement c: unavilableRecipientList){
                if (c.getText().contains(recipient.getRecipient())){
                    throw new IOException("This recipient is unselectable");
                }
            }
        }
        return this;
    }
    public EditNotificationPage editMessage(String text){
        notificationMessageBox.clear();
        webElementActions.sendKeys(notificationMessageBox, text);
        return this;
    }

    public EventsEnginePage editNotification(Notification notification) throws IOException {
        editNotificationName(notification.getName())
                .selectEvent(notification.getEvent())
                .selectRecipient(notification.getRecipient())
                .editMessage(notification.getBody());
        return new EventsEnginePage();
    }

}
