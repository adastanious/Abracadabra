package talentLMS.usersTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;

import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;


public class UserSmokeTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        driver.get(ConfigReader.getProperty("dashboardURL"));
    }


    @Test(groups = {"Smoke"}, description = "Тест проверяет добавление нового пользователя с использованием случайных данных.", priority = 1)
    public void addNewUserTest() {
        dashboardPage.selectSection(AdminSection.USERS);
        userPage.addNewUser(randomUser);
    }


    @Test(groups = {"Smoke"}, description = "Тест проверяет возможность редактирования существующего пользователя.", priority = 2)
    public void editUserTest() {
        dashboardPage.selectSection(AdminSection.USERS);
        userPage.editUser(randomUserGenerator.randomUser());
    }


    @Test(groups = {"Smoke"}, description = "Тест проверяет сообщение об ошибке при попытке добавить пользователя с уже существующим email.", priority = 3)
    public void uniqueEmailTest() {
        userPage.uniqueEmail(randomUserGenerator.emailUniquenessCheck());
        String actualResult = userPage.uniqueEmailError.getText();
        Assert.assertEquals(actualResult, "Someone is already using this email address");
    }


    @Test(groups = {"Smoke"}, description = "Тест проверяет добавление неактивного пользователя и отображение статуса \"INACTIVE\".", priority = 4)
    public void addUserNotActiveTest() {
        userPage.addUserNotActive(randomUserGenerator.randomUser());
        String actualResult = userPage.userIsNotActiveErrorText.getText();
        Assert.assertEquals(actualResult, "INACTIVE");
    }

}