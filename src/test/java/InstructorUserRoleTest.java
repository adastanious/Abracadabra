import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.page.userRole.InstructorUserRole;
import java.time.Duration;

public class InstructorUserRoleTest extends BaseTest {

    @Test
    public void instructorUserRoleTest() {

        InstructorUserRole instructorUserRole = new InstructorUserRole();
        instructorUserRole.moveUserRole();
        instructorUserRole.clickInstructorUserRole();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Ждём, пока курсы станут видимыми:
        // Проверяем, что курсы отображаются
        WebElement guideCourse = wait.until(ExpectedConditions.visibilityOf(instructorUserRole.guideForLearnersCourse));
        WebElement talentLibraryCourse = wait.until(ExpectedConditions.visibilityOf(instructorUserRole.talentLibraryCourse));

        //  Проверяем, отображаются ли курсы: Если хотя бы один курс не найден (false) → тест падает с ошибкой.
        Assert.assertTrue(guideCourse.isDisplayed(), "Курс '[Edit me] Guide for Learners' отсутствует!");
        Assert.assertTrue(talentLibraryCourse.isDisplayed(), "Курс 'What is TalentLibrary?' отсутствует!");

        //  Проверяет может ли Instructor нажимать кнопки (Add course, Add group и т.д.).
        Assert.assertTrue(isElementClickable(instructorUserRole.addCourse), "Instructor должен иметь возможность добавить курс!");
        Assert.assertTrue(isElementClickable(instructorUserRole.addGroup), "Instructor должен иметь возможность добавить группу!");
        Assert.assertTrue(isElementClickable(instructorUserRole.addConference), "Instructor должен иметь возможность добавить конференцию!");
        Assert.assertTrue(isElementClickable(instructorUserRole.addDiscussion), "Instructor должен иметь возможность добавить обсуждение!");
        Assert.assertTrue(isElementClickable(instructorUserRole.addEvent), "Instructor должен иметь возможность добавить событие!");
    }

    // метод проверяет, можно ли нажать на элемент:
    private boolean isElementClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ждёт 10 секунд, пока элемент станет кликабельным.
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return element.isDisplayed() && element.isEnabled();  // Возвращает true, если элемент видим и активен.
        } catch (Exception e) {
            return false; // Если кнопка не кликабельна, возвращает false (тест падает).
        }
    }
}