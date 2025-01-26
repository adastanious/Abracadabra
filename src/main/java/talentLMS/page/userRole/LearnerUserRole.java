package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LearnerUserRole extends UserRole{
    @FindBy(xpath = "(//i[@class='tl-select-icon' ])[2]")
    public WebElement learnerUserRole;

    // Поля для некликабельных элементов
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


    public void clickLearnerUserRole(){
        webElementActions.click(learnerUserRole);
    }
}
