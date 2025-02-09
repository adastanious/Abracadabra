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
import java.util.UUID;
/**
 * @author Arsen Kaiypbekov
 */
public class EventEngineTest extends BaseTest{
    Notification testNotification = new Notification(generateRandomString(10), Event.ONASSIGNMENTGRADING, Recipient.ACCOUNTOWNER,"This is test notification",true);
    EventsEnginePage eventsEnginePage = new EventsEnginePage();
    AddNotificationPage addNotificationPage = new AddNotificationPage();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(UUID.randomUUID().toString().replace("-", ""));
        }
        return sb.substring(0, length);
    }

    @BeforeMethod
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface().selectSection(AdminSection.EVENTS_ENGINE);
    }
    //positive cases
    @Test(priority = 1)
    public void addNotificationTest() throws IOException {
        eventsEnginePage.addNotification();
        Assert.assertTrue(addNotificationPage.addNewEventNotification(testNotification)
                .submit()
                .toastMessage.getText().contains("Success")&&eventsEnginePage.checkNotificationExists(testNotification.getName()));
    }
    @Test(priority = 3)
    public void deleteNotificationsTest() {
        eventsEnginePage.deleteNotification(testNotification.getName());
        Assert.assertFalse(eventsEnginePage.checkNotificationExists(testNotification.getName()));
    }
    @Test(priority = 2)
    public void editNotificationTest() throws IOException {
        String editedName = generateRandomString(10);
        eventsEnginePage.editNotification(testNotification.getName()).editNotificationName(editedName).selectEvent(Event.ONBRANCHASSIGNMENT).selectRecipient(Recipient.SUPERADMINS).editMessage("Edited message").submit();
        testNotification.setName(editedName);
        Assert.assertTrue(eventsEnginePage.checkNotificationExists(editedName));
    }

    //negative cases
    @Test(priority = 4)
    public void addNotificationWithBlankName() throws IOException {
        eventsEnginePage.addNotification();
        Notification blankNameNotification = testNotification;
        blankNameNotification.setName("");
        addNotificationPage.addNewEventNotification(blankNameNotification)
                .submit();
        Assert.assertTrue(addNotificationPage.wrongNameDataMessage.getText().contains("'Name' is required"));
    }
    @Test(priority = 5)
    public void addNotificationWithOverloadedName() throws IOException {
        eventsEnginePage.addNotification()
                .addNewEventNotification(testNotification)
                .inputName(generateRandomString(101))
                .submit();
        Assert.assertTrue(addNotificationPage.wrongNameDataMessage.getText().contains("'Name' cannot exceed 100 characters"));
    }



}
