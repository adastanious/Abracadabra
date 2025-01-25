package talentLMS.page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Courses;

public class CoursesPage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    WebElement courseName;

    @FindBy(xpath = "//span[@class='select2-chosen' and contains(text(), 'category')]")
    WebElement category;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    WebElement categoryAdd;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    WebElement categoryClick;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']//textarea[@name='description']")
    WebElement description;

    @FindBy(xpath = "//label[@class='checkbox inline' and contains(text(), 'Active')]")
    WebElement active;

    @FindBy(xpath = "//input[@name='submit_course']")
    WebElement submit;

    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Add course')]")
    WebElement addCourse;

    public CoursesPage addCourses(Courses courses, String course){
        webElementActions.click(addCourse);
        webElementActions.sendKeys(this.courseName, course);
        webElementActions.click(category);
        webElementActions.sendKeys(this.categoryAdd, courses.getCategory());
        webElementActions.click(categoryClick);
        webElementActions.sendKeys(this.description, courses.getDescription());
        webElementActions.click(active);
        webElementActions.click(submit);
        return new CoursesPage();
    }
}
