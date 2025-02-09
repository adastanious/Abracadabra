package talentLMS.page.eventsEngine;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.enums.EventsEngineNavigationBar;
import talentLMS.page.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arsen Kaiypbekov
 */
public class EventsEnginePage extends BasePage {
    @FindBy(xpath = "//div/a[contains(text(),'Add notification')]")
    public WebElement addEventBtn;
    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement toastMessage;
    @FindBy(xpath = "//div/ul/li[@class = 'active' or @class = 'hidden-phone']/a")
    public List<WebElement> notificationsNavigationTab;
    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> notificationsList;


    public AddNotificationPage addNotification(){
        webElementActions.click(addEventBtn);
        return new AddNotificationPage();
    }

    public EventsEnginePage navigationTab(EventsEngineNavigationBar section){
        for (WebElement a:notificationsNavigationTab){
            if (a.getText().equals(section.getCurrentSection())){
                webElementActions.click(a);
                return this;
            }
        }
        return this;
    }

    public List<String[]> getAllNotifications() {
        List<String[]> notifications = new ArrayList<>();
        int i = 1;
        for (WebElement row : notificationsList) {
            WebElement name = row.findElement(By.xpath("(//tr/td/a/span)[" + i + "]"));
            List<WebElement> columns = row.findElements(By.tagName("td"));
            String[] notification = new String[3];
            notification[0] = name.getText();
            notification[1] = columns.get(1).getText();
            notification[2] = columns.get(2).getText();
            notifications.add(notification);
            i++;
        }
        return notifications;
    }

    public EventsEnginePage deleteNotification(String name){
        webElementActions.moveToElement(driver.findElement(By.xpath("//span[text()='"+name+"']/ancestor::tr/td/div/i")))
                .click(driver.findElement(By.xpath("//span[text()='"+name+"']/ancestor::tr/td/div/div/i[@alt = 'Delete']")))
                .waitButtonToBeClickable(driver.findElement(By.xpath("//div/div/a[@id='tl-confirm-submit']")))
                .click(driver.findElement(By.xpath("//div/div/a[@id='tl-confirm-submit']")))
                .waitElementToBeDisappear(driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]")));
        return this;
    }

    public boolean checkNotificationExists(String name){
        for(String [] notificationInfo : getAllNotifications()){
            if (notificationInfo[0].contains(name)){
                return true;
            }
        }
        return false;
    }

    public EditNotificationPage editNotification(String name){
        webElementActions.moveToElement(driver.findElement(By.xpath("//span[text()='"+name+"']/ancestor::tr/td/div/i")))
                .click(driver.findElement(By.xpath("//span[text()='"+name+"']/ancestor::tr/td/div/div/i[@alt = 'Edit']")));
        return new EditNotificationPage();
    }
}
