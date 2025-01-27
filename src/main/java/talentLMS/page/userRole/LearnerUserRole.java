package talentLMS.page.userRole;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс LearnerUserRole представляет страницу для роли "Learner" (Обучающийся) в системе TalentLMS.
 * Learner может просматривать свои курсы и статистику, но не имеет доступа к административным функциям.
 */
public class LearnerUserRole extends UserRole{

    // Элементы интерфейса, которые доступны Learner, но НЕ являются кликабельными
    @FindBy(xpath = "//div[@title='Show courses in progress']")
    public WebElement coursesInProgress;

    @FindBy(xpath = "//div[@class='item-caption' and text()='Completed courses']")
    public WebElement completedCourses;

    @FindBy(xpath = "//div[@class='item-caption' and text()='Training time']")
    public WebElement trainingTime;

    @FindBy(xpath = "//div[@title='Badges']")
    public WebElement badges;

    @FindBy(xpath = "//div[@title='Points leaderboard']")
    public WebElement points;

    // Курсы, доступные для Learner
    @FindBy(xpath = "//span[@title='[Edit me] Guide for Learners (001)']")
    public WebElement guideForLearnersCourse;

    @FindBy(xpath = "//span[@title='What is TalentLibrary? (002)']")
    public WebElement talentLibraryCourse;
}
