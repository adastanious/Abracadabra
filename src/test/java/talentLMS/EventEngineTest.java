package talentLMS;

import org.testng.annotations.Test;
import talentLMS.entity.Event;
import talentLMS.entity.Notification;
import talentLMS.entity.Recipient;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.eventsEngine.AddEventPage;
import talentLMS.page.eventsEngine.EventsEnginePage;
import java.io.IOException;
/**
 * @author Arsen Kaiypbekov
 */
public class EventEngineTest extends BaseTest{

    EventsEnginePage eventsEnginePage = new EventsEnginePage();
    AddEventPage addEventPage = new AddEventPage();

    @Test
    public void eventsTest() throws IOException {
        driver.get("https://abracadabra.talentlms.com");
        loginPage.doLogin(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).selectSection(sections.getEventsEngine());
        eventsEnginePage.addEvent();
        Notification testNotification = new Notification("New notification", Event.ONASSIGNMENTGRADING, Recipient.ACCOUNTOWNER,"This is test notification");
        addEventPage.addNewEventNotification(testNotification)
                .submit();
    }
}
