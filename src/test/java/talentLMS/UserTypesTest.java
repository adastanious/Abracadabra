package talentLMS;

import org.testng.annotations.Test;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;

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
}
