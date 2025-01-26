package userRoleTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class InstructorUserRoleTest extends BaseTest {
@Test
    public void instructorUserRoleTest(){
    instructorUserRole.moveUserRole();
    instructorUserRole.clickInstructorUserRole();

    String expectedAddCourseBtn = "Add course";
    String actualAddCourseBtn = instructorUserRole.addCourseBtn.getText();
    Assert.assertEquals(actualAddCourseBtn, expectedAddCourseBtn, actualAddCourseBtn+" не совподает: " + expectedAddCourseBtn);
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(instructorUserRole.addCourseBtn));
    } catch (Exception e) {
        System.out.println(instructorUserRole.addCourseBtn.getText()+ " не кликалебен");
    }


    String expectedAddGroupBtn ="Add group";
    String actualAddGroupBtn = instructorUserRole.addGroupBtn.getText();
    Assert.assertEquals(actualAddGroupBtn, expectedAddGroupBtn,actualAddGroupBtn+" не совподает: " + expectedAddCourseBtn);
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(instructorUserRole.addGroupBtn));
    } catch (Exception e) {
        System.out.println(instructorUserRole.addCourseBtn.getText()+ " не кликалебен");
    }

    String expectedAddConferenceBtn = "Add conference";
    String actualAddConferenceBtn = instructorUserRole.addConferenceBtn.getText();
    Assert.assertEquals(actualAddConferenceBtn, expectedAddConferenceBtn, actualAddConferenceBtn+" не совподает: " +expectedAddConferenceBtn);
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(instructorUserRole.addConferenceBtn));
    } catch (Exception e) {
        System.out.println(instructorUserRole.addCourseBtn.getText()+ " не кликалебен");
    }

    String expectedAddDiscussionBtn = "Add discussion";
    String actualAddDiscussionBtn = instructorUserRole.addDiscussionBtn.getText();
    Assert.assertEquals(actualAddDiscussionBtn, expectedAddDiscussionBtn, actualAddDiscussionBtn+" не совподает: " +expectedAddDiscussionBtn);
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(instructorUserRole.addDiscussionBtn));
    } catch (Exception e) {
        System.out.println(instructorUserRole.addCourseBtn.getText()+ " не кликалебен");
    }


    String expectedAddEventBtn ="Add event";
    String actualAddEventBtn = instructorUserRole.addEventBtn.getText();
    Assert.assertEquals(actualAddEventBtn, expectedAddEventBtn, actualAddEventBtn+" не совподает: " +expectedAddEventBtn);
    try {
        // Проверка кликабельности без выполнения клика
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(instructorUserRole.addEventBtn));
    } catch (Exception e) {
        System.out.println(instructorUserRole.addCourseBtn.getText()+ " не кликалебен");
    }

    ArrayList<String> courses = new ArrayList<>();
    courses.add("[Edit me] Guide for Learners (001)");
    courses.add("What is TalentLibrary? (002)");
    for (WebElement element : instructorUserRole.courses){
        if (courses.contains(element.getText())){
            System.out.println("OK  " + element.getText() );
        }else {
            System.out.println("False  " + element.getText());
        }
    }




}
}
