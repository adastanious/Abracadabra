package talentLMS;

import org.testng.annotations.Test;
import talentLMS.fileUtils.ConfigReader;

public class GroupTest extends BaseTest {

    @Test(priority = 1)
    public void addNewGroups() {
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).selectSection(sections.getGroups());
        groups.addNewGroup(randomGroup);
    }

    @Test(priority = 2)
    public void editGroupTest() {
        driver.get("https://abracadabra.talentlms.com/group/index");
        groups.editGroup(randomGroupGenerator.randomGroup());
    }

    @Test(priority = 3)
    public void deleteGroupTest() {
        driver.get("https://abracadabra.talentlms.com/group/index");
        groups.deleteGroup(randomGroupGenerator.randomGroup());

    }
}