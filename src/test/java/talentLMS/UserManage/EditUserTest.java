package talentLMS.UserManage;

import org.testng.annotations.Test;

public class EditUserTest extends AddNewUserTest {

    @Test (priority = 3)
    public void editUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.editUserName(randomUserGenerator.randomUser(),email);
    }
}
