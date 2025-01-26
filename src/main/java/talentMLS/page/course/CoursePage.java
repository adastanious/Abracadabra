package talentMLS.page.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentMLS.entity.Course;
import talentMLS.helper.BasePage;
import talentMLS.page.dashboard.Dashboard;

import java.util.Random;

public class CoursePage extends BasePage {
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

    @FindBy(xpath = "//label[@class='checkbox inline' and contains(text(), 'Active')]/input")
    WebElement active;

    @FindBy(xpath = "//input[@name='submit_course']")
    WebElement submit;

    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Add course')]")
    WebElement addCourse;


    Dashboard dashboard = new Dashboard();
    Course course = new Course();
    public WebElement createdCourse = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + course.getCourseName() + "')]"));
    public WebElement editBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + course.getCourseName() + "')]/parent::*/following-sibling::*[4]//i[@title='Edit']"));

    public CoursePage addCourses(){
        webElementActions
                .click(dashboard.selectSection(sections.getCourses()))
                .click(this.addCourse)
                .sendKeys(this.courseName, course.getCourseName())
                .click(this.category)
                .sendKeys(this.categoryAdd, course.getCategory())
                .click(this.categoryClick)
                .sendKeys(this.description, course.getDescription())
                .click(this.active)
                .click(this.submit);
        return new CoursePage();
    }

    public CoursePage addCourseWithEmptyName() {
        webElementActions
                .click(this.addCourse)
                .sendKeys(this.courseName, "")
                .click(this.category)
                .sendKeys(this.categoryAdd, course.getCategory())
                .click(this.categoryClick)
                .sendKeys(this.description, course.getDescription())
                .click(this.active)
                .click(this.submit);
        return new CoursePage();
    }


    public CoursePage addCourseWithNameOverLimit() {
        webElementActions
                .click(this.addCourse)
                .sendKeys(this.courseName, generateRandomString(101))
                .click(this.category)
                .sendKeys(this.categoryAdd, course.getCategory())
                .click(this.categoryClick)
                .sendKeys(this.description, course.getDescription())
                .click(this.active)
                .click(this.submit);
        return new CoursePage();
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

    public void updateCourse(){
        webElementActions
                .moveToElement(createdCourse)
                .click(editBtn)
                .clearAndSendKeys(this.courseName,course.getUpdatedCourseName())
                .clearAndSendKeys(this.category,course.getUpdatedCategory())
                .clearAndSendKeys(this.description, course.getUpdatedDescription());
    }

    public void savesChanges(){
        webElementActions.click(submit);
    }
}
