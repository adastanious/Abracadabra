package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

    @Test(priority = 6)
    public void deleteNegativeTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.deleteNegative(randomUserTypeGenerator.randomUserType());

        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-block' and  contains(text(),'You must specify')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "You must specify a replacement user type");
    }

    @Test(priority = 7)
    public void addAdminUserTypeTest() {
        driver.get("https://abracadabra.talentlms.com/acl/index");
        userTypes.addAdminUserType(randomUserTypeGenerator.randomUserType());
    }
}
