package talentLMS;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.entity.Group;
import talentLMS.enums.AdminSection;
import talentLMS.enums.SuccessMessage;

import java.util.List;


public class GroupTest extends BaseTest {


    private Group randomGroup;

    @BeforeMethod
    public void beforeMethod() {
        // Инициализация данных перед каждым тестом
        String groupName = "Group" + System.currentTimeMillis();  // Уникальное имя для каждой группы
        randomGroup = new Group(groupName, "Description for " + groupName);

        driver.get("https://abracadabra.talentlms.com/dashboard");
        dashboardPage.selectSection(AdminSection.GROUPS);
    }

    @Test(groups = {"Smoke"}, description = "Добавляет новые группы", priority = 1)
    public void addNewGroups() {
        groups.addNewGroup(randomGroup);
        String successMessage = groups.getSuccessOrErrorMessage();
        Assert.assertEquals(successMessage, "Success! New group created.", "Invalid success message text.");
    }

    @Test(groups = {"Smoke"}, description = "Добавляет пользователей в группу", priority = 2)
    public void testClickAddUserToGroupBtn() {
        groups.clickAddUserToGroupBtn();
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(groups = {"Smoke"}, description = "Редактирует группу", priority = 3)
    public void editGroupTest() {
        groups.editGroup(randomGroup);
        String successMessage = groups.getSuccessOrErrorMessage();
        Assert.assertEquals(successMessage, "Success! Group updated.", "Invalid success message text.");
    }

    @Test(groups = {"Smoke"}, description = "Удаляет группу", priority = 4)
    public void deleteGroupTest() {
        groups.deleteGroup(randomGroup);
    }

    @Test(groups = {"Smoke"}, description = "Выводит список в виде таблицы", priority = 5)
    public void verifyGroupDeletedInTable() {
        List<String> groupNamesBeforeDelete = groups.getAllGroupNamesTable();

        if (!groupNamesBeforeDelete.contains(randomGroup.getName())) {
            System.out.println("Удаленная группа уже отсутствует в таблице!");
            return;
        }

        groups.deleteGroup(randomGroup);
        List<String> groupNamesAfterDelete = groups.getAllGroupNamesTable();
        Assert.assertTrue(groups.isGroupDeleted(groupNamesAfterDelete, randomGroup.getName()), "Ошибка: группа не была удалена!");

        groups.printRemainingGroups(groupNamesAfterDelete);
    }

    @Test(groups = {"Smoke"}, description = "Cancel", priority = 6)
    public void cancelTest() {
        groups.deleteGroupCancel(randomGroup);
    }

    @Test(groups = {
            "Smoke"}, description = "Добавляет пользователей в курс", priority = 7)
    public void testAssignCourseToGroup() {
        groups.assignCourseToGroup(randomGroupGenerator.randomGroup());
    }
}









