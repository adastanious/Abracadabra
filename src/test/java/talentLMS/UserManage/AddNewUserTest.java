package talentLMS.UserManage;

import org.testng.annotations.Test;
import talentMLS.entity.User;
import talentMLS.fileUtils.ConfigReader;

public class AddNewUserTest extends BaseTest {
    User randomUser = randomUserGenerator.randomUser();
    String email = randomUser.getEmail();

    @Test (priority = 1)
    public void addNewUserTest() {

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).switchToLegacyInterface();
        userPage.addNewUser(randomUser, email);
    }


}
