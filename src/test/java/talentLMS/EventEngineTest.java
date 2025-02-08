package talentLMS;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.enums.*;
import talentLMS.entity.Notification;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.eventsEngine.AddNotificationPage;
import talentLMS.page.eventsEngine.EventsEnginePage;
import java.io.IOException;
import java.time.Duration;

/**
 * @author Arsen Kaiypbekov
 */
public class EventEngineTest extends BaseTest{

    EventsEnginePage eventsEnginePage = new EventsEnginePage();
    AddNotificationPage addNotificationPage = new AddNotificationPage();

    @BeforeMethod
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password")).selectSection(AdminSection.EVENTS_ENGINE);
    }

    @Test(priority = 1)
    public void addNotificationTest() throws IOException {
        eventsEnginePage.addEvent();
        Notification testNotification = new Notification("New notification", Event.ONASSIGNMENTGRADING, Recipient.ACCOUNTOWNER,"This is test notification");
        Assert.assertTrue(addNotificationPage.addNewEventNotification(testNotification)
                .submit()
                .toastMessage.getText().contains("Success")&&eventsEnginePage.checkNotificationExists(testNotification.getName()));
    }
    @Test(priority = 2)
    public void deleteNotificationsTest() {
        String name = "New notification";
        eventsEnginePage.deleteNotification(name);
        Assert.assertFalse(eventsEnginePage.checkNotificationExists(name));
    }


}
