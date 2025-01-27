package talentLMS.userManage;

import lombok.Getter;
import org.testng.annotations.Test;
import talentMLS.fileUtils.ConfigReader;
@Getter
public class AddNewUserTest extends BaseTest {
    String email = randomUser.getEmail();

    @Test (priority = 1)
    public void addNewUserTest() {
        driver.get("https://abracadabra.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).switchToLegacyInterface();
        userPage.addNewUser(randomUser, this.email);
    }


}
