package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InstructorUserRole extends UserRole {

    @FindBy(xpath = "(//i[@class='tl-select-icon' ])[1]")
    public WebElement instructorUserRole;

    // Instructor имеет доступ, а Learner не должен видеть или использовать эти элементы
    @FindBy(xpath = "//a[text()='Add course']")
    public WebElement addCourse;

    @FindBy(xpath = "//a[text()='Add group']")
    public WebElement addGroup;

    @FindBy(xpath = "//a[text()='Add conference']")
    public WebElement addConference;

    @FindBy(xpath = "//a[text()='Add discussion']")
    public WebElement addDiscussion;

    @FindBy(xpath = "//a[text()='Add event']")
    public WebElement addEvent;

    // курсы доступны Instructor
    @FindBy(xpath = "//span[@title='[Edit me] Guide for Learners (001)']")
    public WebElement guideForLearnersCourse;

    @FindBy(xpath = "//span[@title='What is TalentLibrary? (002)']")
    public WebElement talentLibraryCourse;


    public void clickInstructorUserRole(){
        webElementActions.click(instructorUserRole);
    }

}
