package UserManage;

import org.testng.annotations.Test;

public class _02_EditUserTest extends _01_AddNewUserTest {

    @Test
    public void editUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.editUserName(randomUserGenerator.randomUser(),email);
    }
}
