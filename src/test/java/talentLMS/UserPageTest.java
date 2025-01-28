package talentLMS;

import lombok.Getter;
import org.testng.annotations.Test;
import talentMLS.fileUtils.ConfigReader;
@Getter
public class UserPageTest extends BaseTest {
    String email = randomUser.getEmail();

    @Test (priority = 1)
    public void addNewUserTest() {
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(sections.getUsers());
        userPage.addNewUser(randomUser, this.email,false);
    }

    @Test (priority = 2)
    public void fillSameDataTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.addNewUser(randomUser, this.email,true);
    }
    @Test (priority = 3)
    public void fillIncorrectEmailTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.addNewUser(randomUser, "alisa.com",true);
    }

    @Test (priority = 4)
    public void editUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.editUserName(randomUserGenerator.randomUser(), email);
    }

    @Test (priority = 5)
    public void deleteUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.deleteUsers(email);
    }
}
