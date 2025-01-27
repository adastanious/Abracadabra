package talentLMS;

import lombok.Getter;
import org.testng.annotations.Test;
import talentLMS.fileUtils.ConfigReader;

@Getter
public class UserPageTest extends BaseTest {

    String email = randomUser.getEmail();

    @Test(priority = 1)
    public void addNewUserTest() {
        driver.get("https://nasya15.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).selectSection(sections.getUsers());
    }

    @Test(priority = 2)
    public void addIncorrectEmailTest() {
        driver.get("https://nasya15.talentlms.com/dashboard");
        userPage.addNewUser(randomUser, this.email, true);
    }

    @Test(priority = 3)
    public void editUsersTest() {
        driver.get("https://nasya15.talentlms.com/user/index");
        userPage.editUserName(randomUserGenerator.randomUser(), email);
    }

    @Test(priority = 4)
    public void deleteUsersTest() {
        driver.get("https://nasya15.talentlms.com/user/index");
        userPage.deleteUsers(email);
    }
}

