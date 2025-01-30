package talentLMS.page.coursePage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.entity.Courses;
import talentLMS.page.BasePage;
import java.time.Duration;
import java.util.List;

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

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    WebElement deleteCourseMain;

    @FindBy(xpath = "//li//a[@data-toggle='dropdown']//span")
    WebElement switchAdmin;

    @FindBy(xpath = "//li[@id='tl-trainer-option' and normalize-space(.)='Instructor']")
    WebElement instructorButton;

    @FindBy(xpath = "//li[@id='tl-learner-option' and normalize-space(.)='Learner']")
    WebElement learnerButton;


    public CoursesPage addCourses(Courses courses, String course) {
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

    public CoursesPage deleteCourse(String courseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Находим строку с курсом
        WebElement courseRow = driver.findElement(By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]"));
        webElementActions.moveToElement(courseRow);

        // Ждем появления кнопки удаления
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]//i[@alt='Delete']")
        ));
        deleteButton.click();
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(deleteCourseMain));
        confirmButton.click();

        // Ждем, пока курс исчезнет из списка
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]")
        ));
        webElementActions.moveToElement(switchAdmin).click(instructorButton);
        webElementActions.moveToElement(switchAdmin).click(learnerButton);

        return new CoursesPage();
    }
    public boolean isCoursePresent(String courseName) {
        List<WebElement> courses = driver.findElements(By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]"));
        return !courses.isEmpty();
    }

    public CoursesPage switchInstructor(){
        webElementActions.moveToElement(switchAdmin).click(instructorButton);
        return new CoursesPage();
    }
    public CoursesPage switchLearner(){
        webElementActions.moveToElement(switchAdmin).click(learnerButton);
        return new CoursesPage();
    }





}
