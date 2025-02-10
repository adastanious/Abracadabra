package talentLMS;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.entity.Group;
import talentLMS.enums.AdminSection;
import java.util.List;


public class GroupTest extends BaseTest {


    private Group randomGroup;

    @BeforeMethod
    public void beforeMethod() {
        // Инициализация данных перед каждым тестом
        String groupName = "Group" + System.currentTimeMillis();  // Уникальное имя для каждой группы
        randomGroup = new Group(groupName, "Description for " + groupName);

        // Переход на страницу
        driver.get("https://abracadabra.talentlms.com/dashboard");
        dashboardPage.selectSection(AdminSection.GROUPS);
    }

    @Test(priority = 1)
    public void addNewGroups() {
        groups.addNewGroup(randomGroup);
        String successMessage = groups.getSuccessOrErrorMessage();
        Assert.assertEquals(successMessage, "Success! New group created.", "Invalid success message text.");
    }

    @Test(priority = 2)
    public void testClickAddUserToGroupBtn() {
        groups.clickAddUserToGroupBtn();
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(priority = 3)
    public void editGroupTest() {
        groups.editGroup(randomGroup);
        String successMessage = groups.getSuccessOrErrorMessage();
        Assert.assertEquals(successMessage, "Success! Group updated.", "Invalid success message text.");
    }

    @Test(priority = 4)
    public void deleteGroupTest() {
        groups.deleteGroup(randomGroup);
    }

    @Test(priority = 5)
    public void verifyGroupDeletedInTable() {
        List<String> groupNamesBeforeDelete = groups.getAllGroupNamesTable();

        if (!groupNamesBeforeDelete.contains(randomGroup.getName())) {
            System.out.println("Удаленная группа уже отсутствует в таблице!");
            return;
        }

        groups.deleteGroup(randomGroup);
        List<String> groupNamesAfterDelete = groups.getAllGroupNamesTable();

        // Теперь используем метод isGroupDeleted
        Assert.assertTrue(groups.isGroupDeleted(groupNamesAfterDelete, randomGroup.getName()), "Ошибка: группа не была удалена!");

        groups.printRemainingGroups(groupNamesAfterDelete);
    }

    @Test(priority = 6)
    public void cancelTest() {
        groups.deleteGroupCancel(randomGroup);
    }

    @Test(priority = 7)
    public void testAssignCourseToGroup() {
        groups.assignCourseToGroup(randomGroupGenerator.randomGroup());
    }
}









