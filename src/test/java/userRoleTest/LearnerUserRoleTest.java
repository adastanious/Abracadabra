package userRoleTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LearnerUserRoleTest extends BaseTest {
    /**
     @author Turan, Agema, Nazik
     */
    @Test
    public void learnerUserRoleTest() {
        // Навести на элемент роли пользовотеля и нажать роль ученика
        component.selectRole("learner");

        //Проверка доступных курсов и сравнение с ожидаемым списком курсов
        ArrayList<String> expectedCourses = new ArrayList<>(); // ожидаемые курсы
        expectedCourses.add("[Edit me] Guide for Learners (001)");
        expectedCourses.add("What is TalentLibrary? (002)");
        ArrayList<String> actualCourses = new ArrayList<>(); // фактисеские курсы из веб элементов
        for (WebElement element : learnerUserRole.courses) {
            actualCourses.add(element.getText());
        }
        Assert.assertEquals(expectedCourses, actualCourses, "Курсы не совподают");
    }
}
