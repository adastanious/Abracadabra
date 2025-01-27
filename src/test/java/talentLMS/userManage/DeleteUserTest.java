package talentLMS.userManage;

import org.testng.annotations.Test;

public class DeleteUserTest extends EditUserTest {
    @Test (priority = 4)
    public void deleteUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.deleteUsers(email);
    }
}
