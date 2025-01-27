package talentLMS.courseTest;

import org.testng.annotations.Test;
import talentLMS.userManage.BaseTest;
import talentMLS.fileUtils.ConfigReader;


public class AddNewCourseTest extends BaseTest {

    @Test
    public void addNewCourseTest(){
        driver.get("https://abracadabra.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).switchToLegacyInterface();
        coursePage.addCourses();
    }
}
