import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.entity.User;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.LoginPage;

import java.time.Duration;

public class UserAddTest extends BaseTest{

    @Test(priority = 1)
    public void test() {
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().usersPage();
    }


    @Test(priority = 2)
    public void userAddTest(){
        driver.get("https://nasya15.talentlms.com/user/create");
        User randomUser = randomUserGenerator.randomUser();
        usersPage.addNewUser(randomUser);
    }


    @Test(priority = 3)
    public void negativeTest(){
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().usersPage();
        driver.get("https://nasya15.talentlms.com/user/create");
        usersPage.addNewUserNoCorrect("Alisa", "Petrovna", "alisa.com", "AlisaPetr", "123A456p$","My name is Alis, i am voice assistant");
        WebElement isRequired = driver.findElement(By.xpath("(//div/div/span/span[@class='help-inline'])[1]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }


}