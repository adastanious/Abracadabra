package UserManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentMLS.entity.User;
import talentMLS.fileUtils.ConfigReader;

public class _01_AddNewUserTest extends BaseTest {
    User randomUser = randomUserGenerator.randomUser();
    String email = randomUser.getEmail();

    @Test
    public void addNewUserTest() {
        driver.get("https://abracadabra.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).switchToLegacyInterface();
        userPage.addNewUser(randomUser, email);
    }

    @Test
    public void negativeTest() {
        driver.get("https://abracadabra.talentlms.com/user/create");
        userPage.addNewUserNoCorrect(randomUser,"alisa123.com");
        WebElement isRequired = driver.findElement(By.xpath("//div[@class='span8']/child::*[3]//span[@class='help-block']"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }
}
