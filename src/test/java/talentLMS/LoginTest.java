package talentLMS;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentMLS.fileUtils.ConfigReader;

public class LoginTest extends BaseTest {
    public void loginTest(String login, String password){
        driver.get("https://abracadabra.talentlms.com/index");
        if(login!=""&password!=""){
            try {
                loginPage.doLogin(login, password).switchToLegacyInterface();
            }
            catch (RuntimeException e){
                throw new RuntimeException ("Wrong login or password", e);
            }
        }
        else{
            try {
                loginPage.doLogin(login, password).switchToLegacyInterface();
            }
            catch (RuntimeException e){
                throw new RuntimeException ("Blank fields are not allowed", e);
            }
        }
    }
    @Test(priority = 3)
    public void rightDataLogin() {
        loginTest(ConfigReader.getProperty("login"),ConfigReader.getProperty("password"));
    }
    @Test(priority = 2)
    public void wrongDataLogin() {
        try {
            loginTest("asdasd", "123578");
            Assert.fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(), "Wrong login or password", "Error message does not match");
        }
    }
    @Test(priority = 1)
    public void blankDataLogin() {
        try {
            loginTest("", "");
            Assert.fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(), "Blank fields are not allowed", "Error message does not match");
        }
    }
}
