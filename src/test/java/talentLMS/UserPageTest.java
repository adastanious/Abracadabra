package talentLMS;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentMLS.fileUtils.ConfigReader;
@Getter
public class UserPageTest extends BaseTest {
    String email = randomUser.getEmail();

    @Test (priority = 1)
    public void addNewUserTest() {
        driver.get("https://abracadabra.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).switchToLegacyInterface();
        userPage.addNewUser(randomUser, this.email);
    }

    @Test (priority = 2)
    public void addIncorrectEmailTest() {
        driver.get("https://abracadabra.talentlms.com/user/create");
        userPage.addNewUserNoCorrect(randomUser,"alisa123.com");

        WebElement isRequired = driver.findElement(By.xpath("//div[@class='span8']/child::*[3]//span[@class='help-block']"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }

    @Test (priority = 3)
    public void editUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.editUserName(randomUserGenerator.randomUser(), email);
    }

    @Test (priority = 4)
    public void deleteUsersTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.deleteUsers(email);
    }


}
