package userRoleTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdministratorUserRoleTest extends BaseTest {
    /**
     @author Turan, Agema, Nazik
     */
    @Test
    public void administratorUserRoleTest() {
        // Навести на элемент роли пользовотеля и нажать роль администратора
        component.selectRole("administrator");

        // проверка элемента Add user с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddUser = "Add user";
        String actualAddUser = administratorUserRole.addUser.getText();
        Assert.assertEquals(actualAddUser, expectedAddUser, actualAddUser + " не совпадают");
        administratorUserRole.isElementClickable(administratorUserRole.addUser, wait);

        // проверка элемента Add course с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddCourse = "Add course";
        String actualAddCourse = administratorUserRole.addCourse.getText();
        Assert.assertEquals(actualAddCourse, expectedAddCourse, actualAddCourse + " не совпадают.");
        administratorUserRole.isElementClickable(administratorUserRole.addCourse, wait);

        // проверка элемента Add category с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddCategories = "Add category";
        String actualAddCategories = administratorUserRole.addCategories.getText();
        Assert.assertEquals(actualAddCategories, expectedAddCategories, actualAddCategories + " не совпадаютю");
        administratorUserRole.isElementClickable(administratorUserRole.addCategories, wait);

        // проверка элемента Add group с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddGroups = "Add group";
        String actualAddGroups = administratorUserRole.addGroups.getText();
        Assert.assertEquals(actualAddGroups, expectedAddGroups, actualAddGroups + " не совпадают.");
        administratorUserRole.isElementClickable(administratorUserRole.addGroups, wait);

        // проверка элемента Add branch с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddBranch = "Add branch";
        String actualAddBranch = administratorUserRole.addBranch.getText();
        Assert.assertEquals(actualAddBranch, expectedAddBranch, actualAddBranch + " не совпадают.");
        administratorUserRole.isElementClickable(administratorUserRole.addBranch, wait);

        // проверка элемента Add notification с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddNotification = "Add notification";
        String actualAddNotification = administratorUserRole.addNotification.getText();
        Assert.assertEquals(actualAddNotification, expectedAddNotification, actualAddNotification + " не совпадают");
        administratorUserRole.isElementClickable(administratorUserRole.addNotification, wait);

        // проверка элемента Add automation с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddAutomation = "Add automation";
        String actualAddAutomation = administratorUserRole.addAutomaition.getText();
        Assert.assertEquals(actualAddAutomation, expectedAddAutomation, actualAddAutomation + " не совпадают.");
        administratorUserRole.isElementClickable(administratorUserRole.addAutomaition, wait);

        // проверка элемента Add user type с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddUserType = "Add user type";
        String actualAddUserType = administratorUserRole.addUserType.getText();
        Assert.assertEquals(actualAddUserType, expectedAddUserType, actualAddUserType + " не совпадают.");
        administratorUserRole.isElementClickable(administratorUserRole.addUserType, wait);

        // проверка элемента IMPORT - EXPORT с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedImportExport = "IMPORT - EXPORT";
        String actualImportExport = administratorUserRole.importExport.getText();
        Assert.assertEquals(actualImportExport, expectedImportExport, actualImportExport + " не совпадают.");
        administratorUserRole.isElementClickable(administratorUserRole.importExport, wait);
    }
}

