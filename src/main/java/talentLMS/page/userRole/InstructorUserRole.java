package talentLMS.page.userRole;

import org.checkerframework.framework.qual.DefaultQualifier;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InstructorUserRole extends UserRole {

    @FindBy(id = "tl-trainer-option")
    public WebElement instructorUserRole;

    public void clickInstructorUserRole(){
        webElementActions.click(instructorUserRole);
    }

    @FindBy(xpath = "//a[text()='Add course']")
    public WebElement addCourseBtn;

    @FindBy(xpath = "//a[text()='Add group']")
    public WebElement addGroupBtn;

    @FindBy(xpath = "//a[text()='Add conference']")
    public WebElement addConferenceBtn;

    @FindBy(xpath = "//a[text()='Add discussion']")
    public WebElement addDiscussionBtn;

    @FindBy(xpath = "//a[text()='Add event']")
    public WebElement addEventBtn;

    @FindBy(xpath = "//span[@class ='tl-formatted-course-name']")
    public List<WebElement> courses;


}
