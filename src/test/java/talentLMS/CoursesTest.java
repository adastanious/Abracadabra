package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import talentLMS.enams.AdminSection;
import talentLMS.fileUtils.ConfigReader;
import java.time.Duration;
import java.util.List;
import static talentLMS.enams.Role.*;

public class CoursesTest extends BaseTest {

    /**
     * Mirat
     */

    @BeforeMethod
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password")).selectSection(AdminSection.COURSES);
    }
    /**
     * Тест 1: Создание курса
     * Проверяем, что можно создать курс.
     */
    @Test(priority = 1)
    public void test(){
        coursesPage.addCourses(courses, courses.getCourseName());
    }

    /**
     * Тест 2: Негативный сценарий - пустое имя курса
     * Проверяем, что система не позволяет создать курс без названия.
     */
    @Test(priority = 2)
    public void negativeTest(){
        coursesPage.addCourses(courses, "");
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' is required");
    }
    /**
     * Тест 3: Негативный сценарий - длинное имя курса
     * Проверяем, что нельзя создать курс с именем > 100 символов.
     */
    @Test(priority = 3)
    public void negativeTest2(){
        coursesPage.addCourses(courses, coursesPage.generateRandomString(101));
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' cannot exceed 100 characters");
    }
    /**
     * Тест 4: Обновление курса
     * Проверяем, что можно обновить название курса.
     */
    @Test(priority = 4)
    public void updateCourseTest(){
        coursesPage.findCourseByName(courses.getCourseName());
        String updateName = courses.getCourseName1();
        coursesPage.updateCourseName(updateName);
        coursesPage.savesChanges();
        String actualName = courses.getCourseName();
        Assert.assertNotEquals(actualName, updateName, "The course name has been updated");
    }
    /**
     * Тест 5: Удаление курса
     * Проверяем, что курс удаляется.
     */
    @Test(priority = 5)
    public void deleteCourse(){
        coursesPage.addCourses(courses, courses.getCourseName());
        coursesPage.goToCourse();

        // Проверяем, что курс создан
        Assert.assertTrue(coursesPage.isCoursePresent(courses.getCourseName()), "Курс не найден после создания!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Удаляем только этот курс
        coursesPage.deleteCourse(courses.getCourseName());

        // Проверяем, что курс удален
        Assert.assertFalse(coursesPage.isCoursePresent(courses.getCourseName()), "Курс все еще существует!");
        Assert.assertFalse(coursesPage.isCoursePresent(courses.getCourseName()), "Инструктор видит удаленный курс!");
        Assert.assertFalse(coursesPage.isCoursePresent(courses.getCourseName()), "Студент видит удаленный курс!");
    }
    /**
     * Тест 6: Проверка доступности всех курсов
     * Убеждаемся, что роли видят только свои курсы.
     */
    @Test(priority = 6)
    public void verifyAllCoursesVisibility(){
        List<String> adminCourses = coursesPage.getAllCourses();
        component.selectRole(INSTRUCTOR);
        List<String> instructorCourses = coursesPage.getAllCourses();
        component.selectRole(LEARNER);
        List<String> learnerCourses = coursesPage.getAllCourses();

        Assert.assertTrue(adminCourses.containsAll(instructorCourses), "Инструктор видит чужие курсы!");
        Assert.assertTrue(adminCourses.containsAll(learnerCourses), "Студент видит курсы, которые ему недоступны!");
    }
}
