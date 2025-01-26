import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.page.userRole.LearnerUserRole;

public class LearnerUserRoleTest extends BaseTest{

    @Test
    public void learnerUserRoleTest() {
        LearnerUserRole learnerUserRole = new LearnerUserRole();
        learnerUserRole.moveUserRole();
        learnerUserRole.clickLearnerUserRole();

        // Проверка некликабельных элементов (они должны отображаться, но не быть активными)
        Assert.assertTrue(learnerUserRole.coursesInProgress.isDisplayed(), "Courses in progress не отображается!");
        Assert.assertTrue(learnerUserRole.completedCourses.isDisplayed(), "Completed courses не отображается!");
        Assert.assertTrue(learnerUserRole.trainingTime.isDisplayed(), "Training time не отображается!");
        Assert.assertTrue(learnerUserRole.badges.isDisplayed(), "Badges не отображается!");
        Assert.assertTrue(learnerUserRole.points.isDisplayed(), "Points leaderboard не отображается!");

        //  Проверка доступных курсов
        Assert.assertTrue(learnerUserRole.guideForLearnersCourse.isDisplayed(), "Курс '[Edit me] Guide for Learners' отсутствует!");
        Assert.assertTrue(learnerUserRole.talentLibraryCourse.isDisplayed(), "Курс 'What is TalentLibrary?' отсутствует!");


    }

}
