package talentLMS.page.userRole;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс InstructorUserRole представляет страницу для роли "Instructor" в системе TalentLMS.
 * Instructor имеет доступ к управлению курсами, группами, конференциями и событиями,
 * а также видит определенные курсы.
 */
public class InstructorUserRole extends UserRole {

    // Элементы, доступные ТОЛЬКО для роли "Instructor" (Кнопки: Add course, Add group и.т.д)
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

    // Курсы доступны Instructor
    @FindBy(xpath = "//span[@title='[Edit me] Guide for Learners (001)']")
    public WebElement guideForLearnersCourse;

    @FindBy(xpath = "//span[@title='What is TalentLibrary? (002)']")
    public WebElement talentLibraryCourse;
}
