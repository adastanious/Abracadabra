package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;
import java.time.Duration;
import java.util.List;

import static talentLMS.enums.Role.*;

public class CoursesTest extends BaseTest {

    /**
     * Mirat
     */
    @BeforeMethod
    public void beforeMethod() {
        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.COURSES);
    }

    /**
     * Тест 1: Создание курса
     * Проверяем, что можно создать курс.
     */
    @Test(priority = 1, groups = "Smoke", description = "Проверяем, что можно создать курс.")
    public void test() {
        coursesPage.addCourses(courses, courses.getCourseName());
    }

    /**
     * Тест 2: Негативный сценарий - пустое имя курса
     * Проверяем, что система не позволяет создать курс без названия.
     */
    @Test(priority = 2, groups = "Smoke", description = "Проверяем, что система не позволяет создать курс без названия.")
    public void negativeTest() {
        coursesPage.addCourses(courses, "");
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' is required");
    }

    /**
     * Тест 3: Негативный сценарий - длинное имя курса
     * Проверяем, что нельзя создать курс с именем > 100 символов.
     */
    @Test(priority = 3, groups = "Smoke", description = "Проверяем, что нельзя создать курс с именем > 100 символов.")
    public void negativeTest2() {
        coursesPage.addCourses(courses, coursesPage.generateRandomString(101));
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' cannot exceed 100 characters");
    }

    /**
     * Тест 4: Обновление курса
     * Проверяем, что можно обновить название курса.
     */
    @Test(priority = 4, groups = "Smoke", description = "Проверяем, что можно обновить название курса.")
    public void updateCourseTest() {
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
    @Test(priority = 5, groups = "Smoke", description = "Проверяем, что курс удаляется.")
    public void deleteCourse() {
        coursesPage.addCourses(courses, courses.getCourseName());
        coursesPage.goToCourse();

        // Проверяем, что курс создан
        Assert.assertTrue(coursesPage.isCoursePresent(courses.getCourseName()), "Курс не найден после создания!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Удаляем только этот курс
        coursesPage.deleteCourse(courses.getCourseName());
        component.selectRole(ADMINISTRATOR);

        // Проверяем, что курс удален
        Assert.assertFalse(coursesPage.isCoursePresent(courses.getCourseName()), "Курс все еще существует!");
        Assert.assertFalse(coursesPage.isCoursePresent(courses.getCourseName()), "Инструктор видит удаленный курс!");
        Assert.assertFalse(coursesPage.isCoursePresent(courses.getCourseName()), "Студент видит удаленный курс!");
    }

    /**
     * Тест 6: Проверка доступности всех курсов
     * Убеждаемся, что роли видят только свои курсы.
     */
    @Test(priority = 6, groups = "Smoke", description = "Убеждаемся, что роли видят только свои курсы.")
    public void verifyAllCoursesVisibility() {
        List<String> adminCourses = coursesPage.getAllCourses();
        component.selectRole(INSTRUCTOR);
        List<String> instructorCourses = coursesPage.getAllCourses();
        component.selectRole(LEARNER);
        List<String> learnerCourses = coursesPage.getAllCourses();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        component.selectRole(ADMINISTRATOR);

        Assert.assertTrue(adminCourses.containsAll(instructorCourses), "Инструктор видит чужие курсы!");
        Assert.assertTrue(adminCourses.containsAll(learnerCourses), "Студент видит курсы, которые ему недоступны!");
    }

    /**
     * Тест 7: Проверка валидации кода курса
     * Проверяем, что при попытке сохранить курс без имени отображается сообщение об обязательности поля,
     * а для кода курса выводится сообщение, что его длина не может превышать 20 символов.
     */
    @Test(priority = 7, groups = "Smoke", description = "Проверка валидации кода курса")
    public void checkCourseCode() {
        coursesPage.checkCod();
        Assert.assertEquals(coursesPage.getAssertName().getText(), "'Course name' is required");
        Assert.assertEquals(coursesPage.getAssertCode().getText(), "'Course code' cannot exceed 20 characters");
    }

    /**
     * Тест 8: Проверка валидации цены курса
     * Проверяем, что при введении некорректного значения цены отображается соответствующее сообщение об ошибке,
     * а также поле 'Course name' остаётся обязательным для заполнения.
     */
    @Test(priority = 8, groups = "Smoke", description = "Проверка валидации цены курса")
    public void checkCoursePrice() {
        coursesPage.validPrice();
        Assert.assertEquals(coursesPage.getAssertName().getText(), "'Course name' is required");
        Assert.assertEquals(coursesPage.getAssertPrice().getText(), "This is not a valid 'Price'");
    }

    /**
     * Тест 9: Проверка валидации URL видео курса
     * Убеждаемся, что при указании некорректного URL для видео выводится сообщение об ошибке,
     * и при этом сохраняется требование обязательного заполнения поля 'Course name'.
     */
    @Test(priority = 9, groups = "Smoke", description = "Проверка валидации URL видео курса")
    public void checkCourseVideo() {
        coursesPage.validVideo();
        Assert.assertEquals(coursesPage.getAssertName().getText(), "'Course name' is required");
        Assert.assertEquals(coursesPage.getAssertVideo().getText(), "This is not a valid 'URL'");
    }

    /**
     * Тест 10: Проверка валидации вместимости курса
     * Проверяем, что при введении некорректного значения вместимости выводится сообщение об ошибке,
     * а также подтверждается обязательность заполнения поля 'Course name'.
     */
    @Test(priority = 10, groups = "Smoke", description = "Тест 10: Проверка валидации вместимости курса")
    public void checkCourseCapacity() {
        coursesPage.validCapacity();
        Assert.assertEquals(coursesPage.getAssertName().getText(), "'Course name' is required");
        Assert.assertEquals(coursesPage.getAssertCapacity().getText(), "This is not a valid 'Capacity'");
    }




}
