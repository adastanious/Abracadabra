import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import talentLMS.entity.User;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.LoginPage;

import java.time.Duration;

public class UserAddTest extends BaseTest{

    @Test
    public void test() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().usersPage();
    }

    @Test
    public void userAddTest(){
        driver.get("https://nasya15.talentlms.com/user/create");
        User randomUser = randomUserGenerator.randomUser();
        usersPage.addNewUser(randomUser);

    }
}
