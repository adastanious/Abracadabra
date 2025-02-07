package talentLMS.userRoleTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.fileUtils.ConfigReader;

import java.util.ArrayList;

import static talentLMS.enums.Role.INSTRUCTOR;

public class InstructorUserRoleTest extends BaseTest {
    /**
     * @author Turan, Agema, Nazik
     */
    @Test
    public void instructorUserRoleTest() {
        // Навести на элемент роли пользовотеля и нажать роль инcтруктора
        component.selectRole(INSTRUCTOR);

        // проверка элемента Add course с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddCourseBtn = "Add course";
        String actualAddCourseBtn = instructorUserRole.addCourseBtn.getText();
        Assert.assertEquals(actualAddCourseBtn, expectedAddCourseBtn, actualAddCourseBtn + " не совподает: " + expectedAddCourseBtn);
        webElementActions.isElementClickable(instructorUserRole.addCourseBtn);

        // проверка элемента Add group с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddGroupBtn = "Add group";
        String actualAddGroupBtn = instructorUserRole.addGroupBtn.getText();
        Assert.assertEquals(actualAddGroupBtn, expectedAddGroupBtn, actualAddGroupBtn + " не совподает: " + expectedAddCourseBtn);
        webElementActions.isElementClickable(instructorUserRole.addGroupBtn);

        // проверка элемента Add conference с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddConferenceBtn = "Add conference";
        String actualAddConferenceBtn = instructorUserRole.addConferenceBtn.getText();
        Assert.assertEquals(actualAddConferenceBtn, expectedAddConferenceBtn, actualAddConferenceBtn + " не совподает: " + expectedAddConferenceBtn);
        webElementActions.isElementClickable(instructorUserRole.addConferenceBtn);

        // проверка элемента Add discussion с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddDiscussionBtn = "Add discussion";
        String actualAddDiscussionBtn = instructorUserRole.addDiscussionBtn.getText();
        Assert.assertEquals(actualAddDiscussionBtn, expectedAddDiscussionBtn, actualAddDiscussionBtn + " не совподает: " + expectedAddDiscussionBtn);
        webElementActions.isElementClickable(instructorUserRole.addDiscussionBtn);

        // проверка элемента Add event с ожидаемым и фактическим текстом, и проверка на кликабельность элемента
        String expectedAddEventBtn = "Add event";
        String actualAddEventBtn = instructorUserRole.addEventBtn.getText();
        Assert.assertEquals(actualAddEventBtn, expectedAddEventBtn, actualAddEventBtn + " не совподает: " + expectedAddEventBtn);
        webElementActions.isElementClickable(instructorUserRole.addEventBtn);

        //Проверка доступных курсов и сравнение с ожидаемым списком курсов
        ArrayList<String> expectedCourses = new ArrayList<>();  // ожидаемые курсы
        ArrayList<String> actualCourses = new ArrayList<>(); // фактисеские курсы из веб элементов
        for (WebElement element : instructorUserRole.courses) {
            actualCourses.add(element.getText());
            expectedCourses.add(element.getText());
        }
        Assert.assertEquals(expectedCourses, actualCourses, "Курсы не совподают");
    }
}


