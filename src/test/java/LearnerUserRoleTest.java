import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.page.userRole.LearnerUserRole;

/**
 * Тест-класс LearnerUserRoleTest проверяет функциональность роли "Learner" (Обучающийся) в системе TalentLMS.
 * Тест включает проверки доступности интерфейса, некликабельных элементов и курсов.
 */
public class LearnerUserRoleTest extends BaseTest{

    @Test
    public void learnerUserRoleTest() {
        LearnerUserRole learnerUserRole = new LearnerUserRole();
        learnerUserRole.moveUserRole();
        switchRolePage.clickUserRole("Learner"); // Выбираем роль "Learner"

        // Позитивные проверки: проверяем, что некликабельные элементы отображаются
        Assert.assertTrue(learnerUserRole.coursesInProgress.isDisplayed(),
                "Courses in progress не отображается!");
        Assert.assertTrue(learnerUserRole.completedCourses.isDisplayed(),
                "Completed courses не отображается!");
        Assert.assertTrue(learnerUserRole.trainingTime.isDisplayed(),
                "Training time не отображается!");
        Assert.assertTrue(learnerUserRole.badges.isDisplayed(),
                "Badges не отображается!");
        Assert.assertTrue(learnerUserRole.points.isDisplayed(),
                "Points leaderboard не отображается!");

        // 🔹 Проверяем, что курсы, доступные Learner, действительно отображаются
        Assert.assertTrue(learnerUserRole.guideForLearnersCourse.isDisplayed(),
                "Курс '[Edit me] Guide for Learners' отсутствует!");
        Assert.assertTrue(learnerUserRole.talentLibraryCourse.isDisplayed(),
                "Курс 'What is TalentLibrary?' отсутствует!");
    }
}
