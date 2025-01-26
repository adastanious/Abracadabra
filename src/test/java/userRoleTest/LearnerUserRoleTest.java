package userRoleTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LearnerUserRoleTest extends BaseTest {
@Test
    public void learnerUserRoleTest(){
    learnerUserRole.moveUserRole();
    learnerUserRole.clickLearnerUserRole();


    ArrayList<String> expectedCourses = new ArrayList<>();
    expectedCourses.add("[Edit me] Guide for Learners (001)");
    expectedCourses.add("What is TalentLibrary? (002)");
    ArrayList<String> actualCourses = new ArrayList<>();
    for (WebElement element : learnerUserRole.courses){
        actualCourses.add(element.getText());
        if (expectedCourses.contains(element.getText())){
            System.out.println("OK  " + element.getText() );
        }else {
            System.out.println("False  " + element.getText());
        }
    }
    Assert.assertEquals(expectedCourses, actualCourses, "Курсы не совподают");
}
}
