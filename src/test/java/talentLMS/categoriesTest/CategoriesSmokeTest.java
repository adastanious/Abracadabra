package talentLMS.categoriesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import java.util.ArrayList;
import static talentLMS.enums.Role.ADMINISTRATOR;

public class CategoriesSmokeTest extends BaseTest {
    /**
     @author Turan
     */

    //проверка создания категорий
    @Test(priority = 1)
    public void addCategories() {
        component.selectRole(ADMINISTRATOR);
        categoriesPage.addCategory( category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText ="Success! New category created.";
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    // Проверка создания категорий с дублирующими названиями
    @Test(priority = 8)
    public void addCategoriesDoubleName(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText ="Success! New category created.";
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertNotEquals(actualText, expectedText, "Система позволяет создавать категории с одинаковыми названиями.");
    }

    //проверка удаления категорий[2]
    @Test(priority = 9)
    public void deleteCategory()  {
        driver.get("https://abracadabra.talentlms.com/dashboard");
        dashboardPage.selectSection(AdminSection.CATEGORIES);
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
    @Test(priority = 10)
    public void changeCategory(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
        String expectedText ="Category updated successfully";
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Система не позволяет изменять категории администратору.");
    }

}
