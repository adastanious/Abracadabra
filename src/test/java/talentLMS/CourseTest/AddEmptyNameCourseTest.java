package talentLMS.CourseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEmptyNameCourseTest extends AddNewCourseTest {

    @Test
    public void addEmptyNameCourseTest(){
        driver.get("https://abracadabra.talentlms.com/index");
        coursePage.addCourseWithEmptyName();

        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' is required");
    }
}
