package talentLMS;

import lombok.Getter;
import org.testng.annotations.Test;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;

@Getter
public class UserPageTest extends BaseTest {
    String email = randomUser.getEmail();

    @Test (priority = 1)
    public void addNewUserTest() {
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(AdminSection.USERS);
        userPage.addNewUser(randomUser);
    }

    @Test (priority = 2)
    public void fillSameDataTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.addNewUser(randomUser);
    }
    @Test (priority = 3)
    public void fillIncorrectEmailTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.addNewUser(randomUser);
    }

    @Test (priority = 4)
    public void editUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.editUser(randomUserGenerator.randomUser());
    }

    @Test (priority = 5)
    public void deleteUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.deleteUser(email);
    }
}