package talentLMS;

import lombok.Getter;
import org.testng.annotations.Test;
import talentLMS.fileUtils.ConfigReader;

    @Getter
    public class UserPageTest extends BaseTest {

        @Test(priority = 1)
        public void addNewUserTest() {
            driver.get("https://abracadabra.talentlms.com/index");

            loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(sections.getUsers());
        }

}
