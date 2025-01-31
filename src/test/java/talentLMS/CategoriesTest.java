package talentLMS;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.entity.Category;
import talentLMS.fileUtils.ConfigReader;
import java.time.Duration;
import java.util.ArrayList;
import static talentLMS.enums.Role.ADMINISTRATOR;

public class CategoriesTest extends BaseTest {
    /**
     @author Turan
     */
    //проверка создания категорий
    @Test(priority = 1)
    public void addCategories() {
        driver.get("https://abracadabra.talentlms.com/dashboard");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password"));
        component.selectRole(ADMINISTRATOR);
        categoriesPage.addCategory(category);
        String expectedText ="Success! New category created.";
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Тексты не совпадают");
    }

    // Проверка создания категорий с дублирующими названиями
    @Test(priority = 2)
    public void addCategoriesDoubleName(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category);
        String expectedText ="Success! New category created.";
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Тексты не совпадают");
    }

    //проверка удаления категорий[2]
    @Test(priority = 3)
    public void deleteCategory()  {
        ArrayList<Category> listBeforeDelete = categoriesPage.getCategoryFormTable();
        categoriesPage.deleteCategory();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Category> listAfterDelete =categoriesPage.getCategoryFormTable();
        Assert.assertEquals(listBeforeDelete.size(), listAfterDelete.size()+1, "Проверка удаления пользователя не прошла");
    }

    //изменение категорий[2]
    @Test(priority = 4)
    public void changeCategory(){
        categoriesPage.changeCategory(category);
        String expectedText ="Category updated successfully";
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Тексты не совпадают");
    }
}
