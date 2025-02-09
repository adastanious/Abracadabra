package talentLMS.page.coursePage;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Courses;
import talentLMS.page.BasePage;
import java.util.List;

@Getter

public class CoursesPage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    WebElement courseName;

    @FindBy(xpath = "//span[@class='select2-chosen' and contains(text(), 'category')]")
    WebElement category;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    WebElement categoryAdd;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    List <WebElement> categoryClick;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']//textarea[@name='description']")
    WebElement description;

    @FindBy(xpath = "//label[@class='checkbox inline' and contains(text(), 'Active')]")
    WebElement active;

    @FindBy(xpath = "//input[@name='submit_course']")
    WebElement submit;

    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Add course')]")
    WebElement addCourse;

    @FindBy(xpath = "//span[@class='tl-formatted-course-name']")
    WebElement editCourse;

    @FindBy(xpath = "(//i[@alt='Delete'])[3]")
    public WebElement deleteCourse;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    WebElement deleteCourseMain;

    @FindBy(xpath = "//tr[3]")
    WebElement deleteElement;

    public CoursesPage addCourses(Courses courses, String course) {
        webElementActions.click(addCourse);
        webElementActions.sendKeys(this.courseName, course);
        webElementActions.click(category);
        webElementActions.sendKeys(this.categoryAdd, courses.getCategory());
        webElementActions.click(categoryClick.get(1));
        webElementActions.sendKeys(this.description, courses.getDescription());
        webElementActions.click(active);
        webElementActions.click(submit);
        return new CoursesPage();
    }

    public void findCourseByName(String courseName) {
        WebElement course = driver.findElement(By.xpath("//table[@id='tl-courses-grid']//tbody//span[@title='" + courseName + "']"));
        course.click();
    }

    public void updateCourseName(String name) {
        WebElement editName = driver.findElement(By.xpath("//input[@name='name']"));
        editName.clear();
        editName.sendKeys(name);
    }

    public void savesChanges() {
        webElementActions.click(submit);
    }

    public CoursesPage deleteCourse() {
        webElementActions.moveToElement(deleteElement);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webElementActions.click(deleteCourse);
        webElementActions.click(deleteCourseMain);
        return new CoursesPage();
    }

}
