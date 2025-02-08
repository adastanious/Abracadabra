package talentLMS;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.fileUtils.ConfigReader;
import java.util.List;


public class GroupTest extends BaseTest {

    @Test(priority = 1)
    public void addNewGroups() {
        driver.get("https://abracadabra.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).selectSection(sections.getGroups());
        groups.addNewGroup(randomGroup);
        String successMessage = groups.getSuccessOrErrorMessage();
        Assert.assertEquals(successMessage, "Success! New group created.", "invalid success message text.");
    }

    @Test(priority = 2)
    public void addUserToGroupTest() {
        groups.addUserToGroup(randomUser);
    }

    @Test(priority = 3)
    public void editGroupTest() {
        driver.get("https://abracadabra.talentlms.com/group/index");
        groups.editGroup(randomGroupGenerator.randomGroup());
        String successMessage = groups.getSuccessOrErrorMessage();
        Assert.assertEquals(successMessage, "Success! Group updated.", "invalid success message text.");
    }

    @Test(priority = 4)
    public void deleteGroupTest() {
        driver.get("https://abracadabra.talentlms.com/group/index");
        groups.deleteGroup(randomGroupGenerator.randomGroup());

    }

    @Test(priority = 5)
    public void verifyGroupDeletedInTable() throws InterruptedException {
        String randomGroupName = "GroupToBeDeleted";  // Имя удалённой группы

        // Обновляем страницу перед проверкой
        driver.navigate().refresh();
        Thread.sleep(2000);  // Ждём обновления данных

        // Получаем список групп
        List<String> groupNamesAfterDelete = groups.getAllGroupNames();

        // Проверяем, что список не пустой
        Assert.assertFalse(groupNamesAfterDelete.isEmpty(), "Список групп пуст, хотя группы должны быть!");

        // Проверяем, что удалённой группы больше нет
        Assert.assertTrue(groups.isGroupDeleted(groupNamesAfterDelete, randomGroupName), "Группа все ещё в списке!");

        // Выводим оставшиеся группы
        groups.printRemainingGroups(groupNamesAfterDelete);
    }

    @Test(priority = 6)
    public void cancelTest() {
        driver.get("https://abracadabra.talentlms.com/group/index");
        groups.deleteGroupCancel(randomGroupGenerator.randomGroup());
    }
        @Test(priority = 7)
    public void testAssignCourseToGroup() {
        driver.get("https://abracadabra.talentlms.com/group/index");
        groups.assignCourseToGroup(randomGroupGenerator.randomGroup());
    }

}


