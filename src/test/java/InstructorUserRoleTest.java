import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.page.userRole.InstructorUserRole;
import talentLMS.page.userRole.WebElementUtils;
import java.time.Duration;

/**
 * Тест-класс проверяет функциональность роли "Instructor" в системе TalentLMS.
 */
public class InstructorUserRoleTest extends BaseTest {

    @Test
    public void instructorUserRoleTest() {

        InstructorUserRole instructorUserRole = new InstructorUserRole();
        instructorUserRole.moveUserRole();
        switchRolePage.clickUserRole("Instructor");

        // Ожидаем загрузки страницы (ждём до 30 секунд, пока курсы станут видимыми)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Ожидаем, пока курсы появятся на экране
        WebElement guideCourse = wait.until(ExpectedConditions.visibilityOf(instructorUserRole.guideForLearnersCourse));
        WebElement talentLibraryCourse = wait.until(ExpectedConditions.visibilityOf(instructorUserRole.talentLibraryCourse));

        // Позитивные проверки: проверяем, что курсы действительно отображаются
        Assert.assertTrue(guideCourse.isDisplayed(), "Курс '[Edit me] Guide for Learners' отсутствует!");
        Assert.assertTrue(talentLibraryCourse.isDisplayed(), "Курс 'What is TalentLibrary?' отсутствует!");

        // Проверяем, может ли "Instructor" нажимать кнопки (Add course, Add group и т.д.)
        Assert.assertTrue(WebElementUtils.isElementClickable(instructorUserRole.addCourse, wait),
                "Instructor должен иметь возможность добавить курс!");
        Assert.assertTrue(WebElementUtils.isElementClickable(instructorUserRole.addGroup, wait),
                "Instructor должен иметь возможность добавить группу!");
        Assert.assertTrue(WebElementUtils.isElementClickable(instructorUserRole.addConference, wait),
                "Instructor должен иметь возможность добавить конференцию!");
        Assert.assertTrue(WebElementUtils.isElementClickable(instructorUserRole.addDiscussion, wait),
                "Instructor должен иметь возможность добавить обсуждение!");
        Assert.assertTrue(WebElementUtils.isElementClickable(instructorUserRole.addEvent, wait),
                "Instructor должен иметь возможность добавить событие!");
    }
}
