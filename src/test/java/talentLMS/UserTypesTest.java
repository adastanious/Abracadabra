package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.entity.UserTypeEntity;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;
import java.util.List;

public class UserTypesTest extends BaseTest{

    @Test(priority = 1)
    public void addNewUserTypeAdminTest() {
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(AdminSection.USER_TYPES);
        userTypes.addUserTypeAdministrator(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 2)
    public void addNewUserTypeInstructorTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addUserTypeInstructor(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 3)
    public void addNewUserTypeLearnerTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addUserTypeLearner(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 4)
    public void editTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.edit(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 5)
    public void deleteTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.delete(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 6)
    public void deleteNegativeTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.deleteNegative(randomUserTypeGenerator.randomUserType());

        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-block' and  contains(text(),'You must specify')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "You must specify a replacement user type");
    }

    @Test(priority = 8)
    public void  addAdministratorUsersTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorUsers(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 9)
    public void  addAdministratorCoursesTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorCourses(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 10)
    public void  addAdministratorCroupsTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorCroups(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 11)
    public void  addAdministratorCategoriesTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorCategories(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 12)
    public void  addAdministratorBranchesTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorBranches(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 13)
    public void  addAdministratorEventsNotificationsTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorEventsNotifications(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 14)
    public void  addAdministratorAutomationsTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorEventsAutomations(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 15)
    public void  addAdministratorImportExportTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorImportExport(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 16)
    public void  addAdministratorUserTypesTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorUserTypes(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 17)
    public void  addAdministratorAccountSettingsTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorAccountSettings(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 18)
    public void  addAdministratorReportsTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorReports(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 19)
    public void  addAdministratorFilesTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorFiles(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 20)
    public void  addAdministratorSkillsTest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorSkills(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 21)
    public void  addAdministratorAITest(){
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdministratorAI(randomUserTypeGenerator.randomUserType());
    }

    @Test(priority = 22)
    public void getListOfUsersTest() throws InterruptedException {
        List<UserTypeEntity> userTypeEntities = userTypes.getUserTypeFromTable();
        for (UserTypeEntity userTypeEntity : userTypeEntities){
            System.out.println(userTypeEntity);
        }
        Thread.sleep(5000);
    }
}
