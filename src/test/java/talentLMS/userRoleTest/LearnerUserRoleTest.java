<<<<<<<< HEAD:src/test/java/talentLMS/userRoleTest/LearnerUserRoleTest.java
package talentLMS.userRoleTest;
========
package talentLMS.userRole;
>>>>>>>> main:src/test/java/talentLMS/userRole/LearnerUserRoleTest.java

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;

import java.util.ArrayList;

import static talentLMS.enums.Role.LEARNER;

public class LearnerUserRoleTest extends BaseTest {
    /**
     @author Turan, Agema, Nazik
     */
    @Test
    public void learnerUserRoleTest() {
        // Навести на элемент роли пользовотеля и нажать роль ученика
        component.selectRole(LEARNER);

        //Проверка доступных курсов и сравнение с ожидаемым списком курсов
        ArrayList<String> expectedCourses = new ArrayList<>(); // ожидаемые курсы
        ArrayList<String> actualCourses = new ArrayList<>(); // фактисеские курсы из веб элементов
        for (WebElement element : learnerUserRole.courses) {
            actualCourses.add(element.getText());
            expectedCourses.add(element.getText());
        }
        Assert.assertEquals(expectedCourses, actualCourses, "Курсы не совподают");
    }
}


