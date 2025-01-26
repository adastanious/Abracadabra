package talentLMS.CourseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateCourseTest extends AddNewCourseTest {

    @Test
    public void updateCourseTest(){
        driver.get("https://abracadabra.talentlms.com/index");
        coursePage.updateCourse();

        String updatedCourse = course.getUpdatedCourseName();
        Assert.assertTrue(coursePage.createdCourse.getText().contains(updatedCourse));
    }
}
