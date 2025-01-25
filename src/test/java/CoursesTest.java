import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import talentLMS.fileUtils.ConfigReader;
import java.util.Random;

public class CoursesTest extends  BaseTest {

    @Test(priority = 1)
    public void test(){
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().coursesPage();
        coursesPage.addCourses(courses, courses.getCourseName());
    }

    @Test(priority = 2)
    public void negativeTest(){
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().coursesPage();
        coursesPage.addCourses(courses, "");
        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' is required");
    }

    @Test(priority = 3)
    public void negativeTest2(){
        loginPage.login(ConfigReader.getProperty("login"),ConfigReader.getProperty("password")).switchToLegacyInterface().coursesPage();
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
}
