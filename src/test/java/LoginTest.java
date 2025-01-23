import org.testng.annotations.Test;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.LoginPage;

public class LoginTest extends BaseTest{
    @Test
    public void test() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().usersPage();
    }
}
