
package talentLMS.userRoleTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;

import static talentLMS.enums.Role.ADMINISTRATOR;

public class AdministratorUserRoleTest extends BaseTest {
    /**
     @author Turan, Agema, Nazik
     */
    @Test
    public void administratorUserRoleTest()  {
        // Навести на элемент роли пользовотеля и нажать роль администратора
        component.selectRole(ADMINISTRATOR);

        // проверка элемента Add user с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddUser = "Add user";
        String actualAddUser = administratorUserRole.addUser.getText();
        Assert.assertEquals(actualAddUser, expectedAddUser, actualAddUser + " не совпадают");
        webElementActions.isElementClickable(administratorUserRole.addUser);

        // проверка элемента Add course с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddCourse = "Add course";
        String actualAddCourse = administratorUserRole.addCourse.getText();
        Assert.assertEquals(actualAddCourse, expectedAddCourse, actualAddCourse + " не совпадают.");
        webElementActions.isElementClickable(administratorUserRole.addCourse);

        // проверка элемента Add category с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddCategories = "Add category";
        String actualAddCategories = administratorUserRole.addCategories.getText();
        Assert.assertEquals(actualAddCategories, expectedAddCategories, actualAddCategories + " не совпадаютю");
        webElementActions.isElementClickable(administratorUserRole.addCategories);

        // проверка элемента Add group с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddGroups = "Add group";
        String actualAddGroups = administratorUserRole.addGroups.getText();
        Assert.assertEquals(actualAddGroups, expectedAddGroups, actualAddGroups + " не совпадают.");
        webElementActions.isElementClickable(administratorUserRole.addGroups);

        // проверка элемента Add branch с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddBranch = "Add branch";
        String actualAddBranch = administratorUserRole.addBranch.getText();
        Assert.assertEquals(actualAddBranch, expectedAddBranch, actualAddBranch + " не совпадают.");
        webElementActions.isElementClickable(administratorUserRole.addBranch);

        // проверка элемента Add notification с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddNotification = "Add notification";
        String actualAddNotification = administratorUserRole.addNotification.getText();
        Assert.assertEquals(actualAddNotification, expectedAddNotification, actualAddNotification + " не совпадают");
        webElementActions.isElementClickable(administratorUserRole.addNotification);

        // проверка элемента Add automation с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddAutomation = "Add automation";
        String actualAddAutomation = administratorUserRole.addAutomaition.getText();
        Assert.assertEquals(actualAddAutomation, expectedAddAutomation, actualAddAutomation + " не совпадают.");
        webElementActions.isElementClickable(administratorUserRole.addAutomaition);

        // проверка элемента Add user type с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddUserType = "Add user type";
        String actualAddUserType = administratorUserRole.addUserType.getText();
        Assert.assertEquals(actualAddUserType, expectedAddUserType, actualAddUserType + " не совпадают.");
        webElementActions.isElementClickable(administratorUserRole.addUserType);

        // проверка элемента IMPORT - EXPORT с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedImportExport = "IMPORT - EXPORT";
        String actualImportExport = administratorUserRole.importExport.getText();
        Assert.assertEquals(actualImportExport, expectedImportExport, actualImportExport + " не совпадают.");
        webElementActions.isElementClickable(administratorUserRole.importExport);
    }

}

