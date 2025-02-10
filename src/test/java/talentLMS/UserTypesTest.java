package talentLMS;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.driver.Driver;
import talentLMS.entity.UserTypeEntity;
import talentLMS.enums.AdminSection;
import talentLMS.enums.ErrorMessage;
import java.time.Duration;
import java.util.List;

/**
 @author Aizada
 */

public class UserTypesTest extends BaseTest{

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(priority = 1)
    public void addNewUserTypeAdminTest() {
        userTypes.addUserTypeAdministrator(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 2)
    public void addNewUserTypeInstructorTest() {
        userTypes.addUserTypeInstructor(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 3)
    public void addNewUserTypeLearnerTest() {
        userTypes.addUserTypeLearner(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 4)
    public void editTest() {
        userTypes.edit(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 5)
    public void deleteTest() throws InterruptedException {
        userTypes.deleteType(randomUserTypeGenerator.randomUserType());
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void deleteNegativeTest(){
        userTypes.deleteNegative(randomUserTypeGenerator.randomUserType());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(userTypes.getAssertText1)); // Ждём появления ошибки
        String expectedText = ErrorMessage.USER_TYPES_DELETE.getMessage(); // Ожидаемое сообщение ошибки
        String actualText = userTypes.getAssertText1.getText(); // Получаем текст ошибки
        Assert.assertEquals(actualText, expectedText, "не выбрал тип пользователя");
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(priority = 7)
    public void  addAdministratorUsersTest(){
        userTypes.addAdministratorUsers(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 8)
    public void  addAdministratorCoursesTest(){
        userTypes.addAdministratorCourses(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 9)
    public void  addAdministratorCroupsTest(){
        userTypes.addAdministratorCroups(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 10)
    public void  addAdministratorCategoriesTest(){
        userTypes.addAdministratorCategories(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 11)
    public void  addAdministratorBranchesTest(){
        userTypes.addAdministratorBranches(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 12)
    public void  addAdministratorEventsNotificationsTest(){
        userTypes.addAdministratorEventsNotifications(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 13)
    public void  addAdministratorAutomationsTest(){
        userTypes.addAdministratorEventsAutomations(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 14)
    public void  addAdministratorImportExportTest(){
        userTypes.addAdministratorImportExport(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 15)
    public void  addAdministratorUserTypesTest(){
        userTypes.addAdministratorUserTypes(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 16)
    public void  addAdministratorAccountSettingsTest(){
        userTypes.addAdministratorAccountSettings(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 17)
    public void  addAdministratorReportsTest(){
        userTypes.addAdministratorReports(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 18)
    public void  addAdministratorFilesTest(){
        userTypes.addAdministratorFiles(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 19)
    public void  addAdministratorSkillsTest(){
        userTypes.addAdministratorSkills(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 20)
    public void  addAdministratorAITest(){
        userTypes.addAdministratorAI(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 21)
    public void  addInstructorCoursesTest(){
        userTypes.addInstructorCourses(randomUserTypeGenerator.randomUserType());
    }


    @Test(priority = 22)
    public void getListOfUsersTest() throws InterruptedException {
        List<UserTypeEntity> userTypeEntities = userTypes.getUserTypeFromTable();
        for (UserTypeEntity userTypeEntity : userTypeEntities){
            System.out.println(userTypeEntity);
        }
        Thread.sleep(5000);
    }

    @Test(priority = 23)
    public void notCorrectUserTypeTest() {
        dashboardPage.selectSection(AdminSection.USER_TYPES);
        userTypes.notCorrectUserType(randomUserTypeGenerator.randomUserType());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(userTypes.AssertText2)); // Ждём появления ошибки
        String expectedText = ErrorMessage.USER_TYPES_NOT_CORRECT.getMessage(); // Ожидаемое сообщение ошибки
        String actualText = userTypes.AssertText2.getText(); // Получаем текст ошибки
        Assert.assertEquals(actualText, expectedText, "The assigned default role is not available for this user type");
    }
}
