package talentLMS.categoriesTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import talentLMS.enums.SuccessMessage;
import java.util.ArrayList;

public class CategoriesSmokeTest extends BaseTest {
    /**
     @author Turan
     */

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

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
        categoriesPage.addCategory( category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText = SuccessMessage.CATEGORIES_ADD_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    // Проверка создания категорий с дублирующими названиями
    @Test(priority = 2, dependsOnMethods = {"addCategories"})
    public void addCategoriesDoubleName(){
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertNotEquals(actualText, expectedText, "Система позволяет создавать категории с одинаковыми названиями.");
    }
    @Test(priority = 3)
    public void addCategoryParentTest(){
        categoriesPage.addCategoryParent("SpaceX",category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    //проверка удаления категорий[2]
    @Test(priority = 4)
    public void deleteCategory()  {
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
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
        String expectedText = SuccessMessage.CATEGORIES_CHANGE_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertEquals(expectedText, actualText, "Система не позволяет изменять категории администратору.");
    }

    @Test(priority = 6)
    public void categoryCursesTest(){
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        ArrayList<Category> categoriesTable = categoriesPage.getCategoryFormTable();

        driver.get("https://abracadabra.talentlms.com/dashboard");
        dashboardPage.selectSection(AdminSection.COURSES);
        webElementActions.click(coursesPage.getAddCourse())
                .click(coursesPage.getCategory());

        ArrayList<WebElement> categoryCursesTable = new ArrayList<>(coursesPage.getCategoryClick());
        Assert.assertEquals(categoriesTable.size(), categoryCursesTable.size(),"не прошла проверка по категориям в разделе категории и в по категориям в разделе курсы");
    }
}
