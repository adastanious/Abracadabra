
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.entity.User;
import talentLMS.fileUtils.ConfigReader;


public class UserAddTest extends BaseTest {

    @Test(priority = 1)
    public void test() {
        loginPage.login(ConfigReader.getProperty("login"), ConfigReader.getProperty("password")).switchToLegacyInterface().usersPage();
    }

    @Test(priority = 2)
    public void userAddTest() {
        driver.get("https://aiperi0606.talentlms.com/user/create");
        User randomUser = randomUserGenerator.randomUser();
        usersPage.addNewUser(randomUser);
    }

    @Test(priority = 3)
    public void negativeTest() {
        // loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().usersPage();
        driver.get("https://aiperi0606.talentlms.com/user/create");
        usersPage.addNewUserNoCorrect("Alisa", "Petrovna", "alisa.com", "AlisaPetr", "123A456p$", "My name is Alis, i am voice assistant");
        WebElement isRequired = driver.findElement(By.xpath("(//div/div/span/span[@class='help-inline'])[1]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }

    @Test(priority = 4)
    public void editUsersTest() {
        driver.get("https://aiperi0606.talentlms.com/user/index");
        usersPage.editUserName(randomUserGenerator.randomUser());
    }

    // Тест для удаления пользователя
    @Test(priority = 5)
    public void deleteUsersTest() {
        driver.get("https://aiperi0606.talentlms.com/user/index");
        usersPage.deleteUsers(randomUserGenerator.randomUser());
    }
}
