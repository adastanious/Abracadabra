package talentLMS.accountAndSettings;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;

public class UsersTest extends BaseTest {



    @Test(description = "verify that the selected default user type is specified in the Add user form", priority = 1)
    public void selectDefaultUserTypeTest() {
        for (int i = 0; i < settingsUsersPage.getUserTypes().size(); i++) {
            settingsUsersPage.selectDefaultUserType(i);
            driver.get("https://aidas.talentlms.com/user/create");
            String actualUserType = settingsUsersPage.getUserTypeInAddUser().getText();
            String expectedUserType = settingsUsersPage.getUserTypes().get(i).getText();
            Assert.assertEquals(actualUserType, expectedUserType, "User types are different");
        }
    }
}
