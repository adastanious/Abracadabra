package talentLMS;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.driver.Driver;
import talentLMS.entity.UserTypeEntity;
import talentLMS.enums.AdminSection;
import talentLMS.enums.ErrorMessage;
import java.time.Duration;
import java.util.List;

/**
 @author Aizada
 */

public class UserTypesTest extends BaseTest{

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(groups = {"Smoke"}, description = "Добавляет пользователя с ролью Администратор", priority = 1)
    public void addNewUserTypeAdminTest() {
        userTypes.addUserTypeAdministrator(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет пользователя с ролью Инструктор", priority = 2)
    public void addNewUserTypeInstructorTest() {
        userTypes.addUserTypeInstructor(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет пользователя с ролью Ученик", priority = 3)
    public void addNewUserTypeLearnerTest() {
        userTypes.addUserTypeLearner(randomUserTypeGenerator.randomUserType());
    }


    @Test(groups = {"Regression"}, description = "Удаляет существующий тип пользователя", priority = 4)
    public void deleteTest() throws InterruptedException {
        userTypes.deleteType(randomUserTypeGenerator.randomUserType());
        Thread.sleep(5000);
    }

    @Test(groups = {"Regression"}, description = "Удаляет тип пользователя в негативном сценарии", priority = 5)
    public void deleteNegativeTest(){
        userTypes.deleteNegative(randomUserTypeGenerator.randomUserType());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(userTypes.getAssertText1)); // Ждём появления ошибки
        String expectedText = ErrorMessage.USER_TYPES_DELETE.getMessage(); // Ожидаемое сообщение ошибки
        String actualText = userTypes.getAssertText1.getText(); // Получаем текст ошибки
        Assert.assertEquals(actualText, expectedText, "не выбрал тип пользователя");
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для пользователей", priority = 6)
    public void  addAdministratorUsersTest(){
        userTypes.addAdministratorUsers(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для курсов", priority = 7)
    public void  addAdministratorCoursesTest(){
        userTypes.addAdministratorCourses(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = " Добавляет администратора с правами доступа для групп", priority = 8)
    public void  addAdministratorCroupsTest(){
        userTypes.addAdministratorCroups(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для категорий", priority = 9)
    public void  addAdministratorCategoriesTest(){
        userTypes.addAdministratorCategories(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для филиалов (branches)", priority = 10)
    public void  addAdministratorBranchesTest(){
        userTypes.addAdministratorBranches(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для уведомлений событий", priority = 11)
    public void  addAdministratorEventsNotificationsTest(){
        userTypes.addAdministratorEventsNotifications(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для автоматизаций событий", priority = 12)
    public void  addAdministratorAutomationsTest(){
        userTypes.addAdministratorEventsAutomations(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для импорта и экспорта данных", priority = 13)
    public void  addAdministratorImportExportTest(){
        userTypes.addAdministratorImportExport(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = " Добавляет администратора с правами доступа для типов пользователей", priority = 14)
    public void  addAdministratorUserTypesTest(){
        userTypes.addAdministratorUserTypes(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для настроек аккаунта", priority = 15)
    public void  addAdministratorAccountSettingsTest(){
        userTypes.addAdministratorAccountSettings(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для отчетов", priority = 16)
    public void  addAdministratorReportsTest(){
        userTypes.addAdministratorReports(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для файлов", priority = 17)
    public void  addAdministratorFilesTest(){
        userTypes.addAdministratorFiles(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для навыков", priority = 18)
    public void  addAdministratorSkillsTest(){
        userTypes.addAdministratorSkills(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет администратора с правами доступа для искусственного интеллекта (AI)", priority = 19)
    public void  addAdministratorAITest(){
        userTypes.addAdministratorAI(randomUserTypeGenerator.randomUserType());
    }

    @Test(groups = {"Smoke"}, description = "Добавляет новый тип пользователя с ролью Instructor и назначает права на курсы и пользователей", priority = 20)
    public void  addInstructorCoursesTest(){
        userTypes.addInstructorCourses(randomUserTypeGenerator.randomUserType());
    }


    @Test(groups = {"Regression"}, description = "Извлекает данные о типах пользователей из таблицы на веб-странице и создает список объектов", priority = 21)
    public void getListOfUsersTest() throws InterruptedException {
        List<UserTypeEntity> userTypeEntities = userTypes.getUserTypeFromTable();
        for (UserTypeEntity userTypeEntity : userTypeEntities){
            System.out.println(userTypeEntity);
        }
        Thread.sleep(5000);
    }

    @Test(groups = {"Regression"}, description = "Проверяет, что система не позволяет создать пользователя с некорректной комбинацией ролей", priority = 22)
    public void notCorrectUserTypeTest() {
        dashboardPage.selectSection(AdminSection.USER_TYPES);
        userTypes.notCorrectUserType(randomUserTypeGenerator.randomUserType());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(userTypes.AssertText2)); // Ждём появления ошибки
        String expectedText = ErrorMessage.USER_TYPES_NOT_CORRECT.getMessage(); // Ожидаемое сообщение ошибки
        String actualText = userTypes.AssertText2.getText(); // Получаем текст ошибки
        Assert.assertEquals(actualText, expectedText, "The assigned default role is not available for this user type");
    }
}
