package talentLMS;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.fileUtils.ConfigReader;
/**
 * @author Arsen Kaiypbekov
 */
public class LoginTest extends BaseTest{

    private String url = "https://abracadabra.talentlms.com/index";

    @Test(priority = 1)
    public void wrongDataLogin() {
        driver.get(url);
        loginPage.doLogin("asdasd", "123578");
        Assert.assertTrue(loginPage.wrongLoginDataMessage.isDisplayed());
    }

    @Test(priority = 2)
    public void blankPasswordDataLogin() {
        driver.manage().deleteAllCookies();
        driver.get(url);
        loginPage.doLogin("adsd", "");
        Assert.assertTrue(loginPage.emptyPasswordMessage.isDisplayed());
    }

    @Test(priority = 3)
    public void blankUsernameDataLogin(){
        driver.manage().deleteAllCookies();
        driver.get(url);
        loginPage.doLogin("","12367");
        Assert.assertTrue(loginPage.emptyUsernameMessage.isDisplayed());
    }

    @Test(priority = 4)
    public void rightDataLogin() {
        driver.manage().deleteAllCookies();
        driver.get(url);
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password"));
        Assert.assertNotSame(driver.getCurrentUrl(),url);
    }
}
