package talentLMS.categoriesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import talentLMS.enums.SuccessMessage;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static talentLMS.enums.Role.ADMINISTRATOR;

public class CategoriesSmokeTest extends BaseTest {
    /**
     @author Turan
     */
    @Test(priority = 0)
    public void CategoriesTestTableZero(){
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        while (true) {
            ArrayList<Category> categoriesTable = categoriesPage.getCategoryFormTable();
            if (categoriesTable.isEmpty()){
                break;
            }else {
                try {
                    categoriesPage.deleteCategory();
                }
                catch (Exception e){
                }
            }
        }
        ArrayList<Category> categoriesTable = categoriesPage.getCategoryFormTable();
        Assert.assertEquals(categoriesTable.size(), 0, "Не прошло удаление всех категорий");
    }

    //проверка создания категорий
    @Test(priority = 1)
    public void addCategories() {
        driver.get("https://abracadabra.talentlms.com/dashboard");
        component.selectRole(ADMINISTRATOR);
        categoriesPage.addCategory( category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.toString();
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    // Проверка создания категорий с дублирующими названиями
    @Test(priority = 2)
    public void addCategoriesDoubleName(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.toString();
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertNotEquals(actualText, expectedText, "Система позволяет создавать категории с одинаковыми названиями.");
    }
    @Test(priority = 3)
    public void addCategoryParentTest(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategoryParent("SpaceX",category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.toString();
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    //проверка удаления категорий[2]
    @Test(priority = 4)
    public void deleteCategory()  {
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory( category.getCorrectCategoryName(), category.getCorrectPrice());
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
    @Test(priority = 5)
    public void changeCategory(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
        String expectedText = SuccessMessage.CATEGORIES_CHANGE_MESSAGE.toString();
        String actualText  = categoriesPage.assertText.getText();
        Assert.assertEquals(expectedText, actualText, "Система не позволяет изменять категории администратору.");
    }

}
