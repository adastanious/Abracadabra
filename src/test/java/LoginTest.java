import org.testng.annotations.Test;
import talentMLS.fileUtils.ConfigReader;

public class LoginTest extends BaseTest {
    @Test
    public void test() {

        loginPage.doLogin(ConfigReader.getProperty("login"),ConfigReader.getProperty("password"));
    }
}
