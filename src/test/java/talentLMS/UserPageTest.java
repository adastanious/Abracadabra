package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;


public class UserPageTest extends BaseTest {

    String email = randomUser.getEmail();
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test (priority = 1)
    public void addNewUserTest() {
        dashboardPage.selectSection(AdminSection.USERS);
        userPage.addNewUser(randomUser);
    }

    @Test (priority = 2)
    public void fillIncorrectEmailTest() {
        dashboardPage.selectSection(AdminSection.USERS);
        userPage.userWithIncorrectEmail(randomUserGenerator.randomUserWithIncorrectEmail());

        WebElement isRequired = driver.findElement(By.xpath("(//div/div/span/span[@class='help-inline'])[1]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }

    @Test (priority = 3)
    public void editUserTest() {
        dashboardPage.selectSection(AdminSection.USERS);
        userPage.editUser(randomUserGenerator.randomUser());
    }

    @Test (priority = 4)
    public void deleteUserTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.deleteUser(email);
    }

    @Test(priority = 5)
    public void uniqueEmailTest() {
        driver.get("https://abracadabra.talentlms.com/user/create");
        userPage.uniqueEmail(randomUserGenerator.emailUniquenessCheck());

        WebElement isRequired = driver.findElement(By.xpath("(//span/span[@class='help-inline'])"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "Someone is already using this email address");
        driver.get("https://abracadabra.talentlms.com/user/index");
    }
}
