package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.driver.Driver;
import talentLMS.enums.AdminSection;
import talentLMS.entity.UserEntity;
import talentLMS.fileUtils.ConfigReader;
import java.time.Duration;
import java.util.List;


public class UserPageTest extends BaseTest {

    String email = randomUser.getEmail();

    @Test(priority = 1)
    public void addNewUserTest() {
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).selectSection(AdminSection.USERS);
        userPage.addNewUser(randomUser);
    }

    @Test(priority = 2)
    public void fillIncorrectEmailTest() {
        driver.get("https://abracadabra.talentlms.com/user/create");
        userPage.userWithIncorrectEmail(randomUserGenerator.randomUserWithIncorrectEmail());

        WebElement isRequired = driver.findElement(By.xpath("(//div/div/span/span[@class='help-inline'])[1]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }

    @Test(priority = 3)
    public void editUserTest() {
        driver.get("https://abracadabra.talentlms.com/user/index");
        userPage.editUser(randomUserGenerator.randomUser());
    }

    @Test(priority = 4)
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

    @Test(priority = 6)
    public void getListOfUsersTest() throws InterruptedException {
// Вызов метода получения списка всех сотрудников и вывода на консоль
        List<UserEntity> userEntities = userPage.getUserFromTable();
        for (UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void logIntoAccountTest() {
        String testUsername = "L. Dmitrievna";
        try {
            userPage.logIntoAccount(testUsername);
            System.out.println("Тест пройден: вход выполнен для пользователя: " + testUsername);

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement backToAdminBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//i[@class='icon-undo tl-icon16']")
            ));
            Assert.assertTrue(backToAdminBtn.isDisplayed(), "Кнопка 'Назад' не отображается.");
        } catch (NoSuchElementException e) {
            Assert.fail("Тест провален: " + e.getMessage());
        }
    }

    @Test(priority = 8)
    public void editAccountTest() {
        userPage.editAccount("L. Dmitrievna",randomUserGenerator.randomUser());
        driver.get("https://abracadabra.talentlms.com/user/index");
    }
}