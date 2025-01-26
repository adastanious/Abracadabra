package UserManage;

import org.testng.annotations.Test;

public class _03_DeleteUserTest extends _02_EditUserTest {
    @Test
    public void deleteUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.deleteUsers(email);
    }
}
