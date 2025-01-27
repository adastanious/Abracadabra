package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentMLS.fileUtils.ConfigReader;


public class CoursePageTest extends BaseTest {

    @Test
    public void addNewCourseTest(){
        driver.get("https://abracadabra.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(sections.getCourses());
        coursePage.addCourses();
    }

    @Test
    public void addEmptyNameCourseTest(){
        driver.get("https://abracadabra.talentlms.com/index");
        coursePage.addCourseWithEmptyName();

        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' is required");
    }

    @Test
    public void addCourseOverLimitTest(){
        driver.get("https://abracadabra.talentlms.com/index");
        coursePage.addCourseWithNameOverLimit();

        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' cannot exceed 100 characters");
    }

    @Test
    public void updateCourseTest(){
        driver.get("https://abracadabra.talentlms.com/index");

        WebElement createdCourse = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + course.getCourseName() + "')]"));
        WebElement editBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + course.getCourseName() + "')]/parent::*/following-sibling::*[4]//i[@title='Edit']"));

        coursePage.updateCourse(createdCourse,editBtn);

        String updatedCourse = course.getUpdatedCourseName();

        Assert.assertTrue(createdCourse.getText().contains(updatedCourse));
    }
}
