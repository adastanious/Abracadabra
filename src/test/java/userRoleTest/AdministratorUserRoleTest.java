package userRoleTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdministratorUserRoleTest extends BaseTest {


@Test
    public void administratorUserRoleTest(){
administratorUserRole.moveUserRole();
administratorUserRole.clickAdministratorUserRole();
    String expectedAddUser = "Add user";
    String actualAddUser = administratorUserRole.addUser.getText();
    Assert.assertEquals(actualAddUser, expectedAddUser, actualAddUser + " не совпадают");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addUser));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addUser.getText()+ " не кликалебен");
    }


    String expectedAddCourse = "Add course";
    String actualAddCourse = administratorUserRole.addCourse.getText();
    Assert.assertEquals(actualAddCourse, expectedAddCourse, actualAddCourse + " не совпадают.");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addCourse));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addCourse.getText()+ " не кликалебен");
    }

    String expectedAddCategories = "Add category";
    String actualAddCategories = administratorUserRole.addCategories.getText();
    Assert.assertEquals(actualAddCategories, expectedAddCategories, actualAddCategories + " не совпадаютю");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addCategories));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addCategories.getText()+ " не кликалебен");
    }

    String expectedAddGroups = "Add group";
    String actualAddGroups = administratorUserRole.addGroups.getText();
    Assert.assertEquals(actualAddGroups, expectedAddGroups, actualAddGroups + " не совпадают.");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addGroups));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addGroups.getText()+ " не кликалебен");
    }

    String expectedAddBranch = "Add branch";
    String actualAddBranch = administratorUserRole.addBranch.getText();
    Assert.assertEquals(actualAddBranch, expectedAddBranch, actualAddBranch + " не совпадают.");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addBranch));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addBranch.getText()+ " не кликалебен");
    }

    String expectedAddNotification = "Add notification";
    String actualAddNotification = administratorUserRole.addNotification.getText();
    Assert.assertEquals(actualAddNotification, expectedAddNotification, actualAddNotification + " не совпадают");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addNotification));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addNotification.getText()+ " не кликалебен");
    }

    String expectedAddAutomation = "Add automation";
    String actualAddAutomation = administratorUserRole.addAutomaition.getText();
    Assert.assertEquals(actualAddAutomation,expectedAddAutomation, actualAddAutomation + " не совпадают.");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addAutomaition));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addAutomaition.getText()+ " не кликалебен");
    }

    String expectedAddUserType = "Add user type";
    String actualAddUserType = administratorUserRole.addUserType.getText();
    Assert.assertEquals(actualAddUserType, expectedAddUserType, actualAddUserType + " не совпадают.");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.addUserType));
    } catch (Exception e) {
        System.out.println(administratorUserRole.addUserType.getText()+ " не кликалебен");
    }

    String expectedImportExport = "IMPORT - EXPORT";
    String actualImportExport = administratorUserRole.importExport.getText();
    Assert.assertEquals(actualImportExport, expectedImportExport, actualImportExport + " не совпадают.");
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(administratorUserRole.importExport));
    } catch (Exception e) {
        System.out.println(administratorUserRole.importExport.getText()+ " не кликалебен");
    }
}
}

