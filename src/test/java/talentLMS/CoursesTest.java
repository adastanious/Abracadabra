package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import talentLMS.enams.AdminSection;
import talentLMS.fileUtils.ConfigReader;
import java.time.Duration;
import java.util.Random;

public class CoursesTest extends BaseTest {

    @Test(priority = 1)
    public void test(){
        driver.get("https://abracadabra.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).selectSection(AdminSection.COURSES);
        coursesPage.addCourses(courses, courses.getCourseName());
    }

    @Test(priority = 2)
    public void negativeTest(){
        driver.get("https://abracadabra.talentlms.com/course/index");
        coursesPage.addCourses(courses, "");
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' is required");
    }

    @Test(priority = 3)
    public void negativeTest2(){
        driver.get("https://abracadabra.talentlms.com/course/index");
        coursesPage.addCourses(courses, generateRandomString(101));
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' cannot exceed 100 characters");
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }

    @Test(priority = 4)
    public void updateCourseTest(){
        driver.get("https://abracadabra.talentlms.com/course/index");
        coursesPage.findCourseByName(courses.getCourseName());
        String updateName = courses.getCourseName1();
        coursesPage.updateCourseName(updateName);
        coursesPage.savesChanges();
        String actualName = courses.getCourseName();
        Assert.assertNotEquals(actualName, updateName, "The course name has been updated");
    }

    @Test(priority = 5)
    public void deleteCourse(){
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).selectSection(AdminSection.COURSES);
        coursesPage.addCourses(courses, courses.getCourseName());

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
}
