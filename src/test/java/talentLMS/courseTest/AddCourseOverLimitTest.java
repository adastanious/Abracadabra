package talentLMS.courseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCourseOverLimitTest extends AddNewCourseTest {

    @Test
    public void addCourseOverLimitTest(){
        driver.get("https://abracadabra.talentlms.com/index");
        coursePage.addCourseWithNameOverLimit();

        WebElement isRequired = driver.findElement(By.xpath("//span[@class='help-inline' and contains(text(), 'Course name')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Course name' cannot exceed 100 characters");
    }
}
