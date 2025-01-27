package talentLMS.courseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateCourseTest extends AddNewCourseTest {

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
