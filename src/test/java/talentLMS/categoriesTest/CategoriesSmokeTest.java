package talentLMS.categoriesTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import talentLMS.enums.SuccessMessage;
import talentLMS.fileUtils.ConfigReader;

import java.util.ArrayList;

public class CategoriesSmokeTest extends BaseTest {
    /**
     @author Turan
     */

    @BeforeMethod
    public void beforeMethod(){
        driver.get(ConfigReader.getProperty("dashboardURL"));
    }

    /**
     * Тест для проверки удаления всех категорий в разделе "Категории".
     * Этот тест выполняет следующие действия:
     * 1. Переход в раздел "Категории" на странице панели управления.
     * 2. Попытка удаления всех категорий на странице, пока таблица категорий не станет пустой.
     * 3. Проверка, что после выполнения операций удаления таблица категорий пуста.
     *
     * @throws Exception Если при удалении категорий произойдет ошибка, она будет поймана, но не вызовет сбой теста.
     */
    @Test(priority = 0, groups = "Smoke", description = "Тест для проверки удаления всех категорий в разделе Категорий.")
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

    @Test(priority = 1, groups = "Smoke",description = "тест на создание категорий корректными названием")
    public void addCategories() {
        categoriesPage.addCategory( category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText = SuccessMessage.CATEGORIES_ADD_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    @Test(priority = 2, dependsOnMethods = {"addCategories"}, groups = "Smoke", description = "Проверка системы на создание категории с дублирующимт названием")
    public void addCategoriesDoubleName(){
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertNotEquals(actualText, expectedText, "Система позволяет создавать категории с одинаковыми названиями.");
    }
    @Test(priority = 3, dependsOnMethods = {"addCategories"}, groups = "Smoke", description = "тест на создание категорий корректными названием и с указанием родительской категории")
    public void addCategoryParentTest(){
        categoriesPage.addCategoryParent("SpaceX",category.getCorrectPrice());
        String expectedText =SuccessMessage.CATEGORIES_ADD_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertEquals(expectedText, actualText, "Администратор не может создать категорию с корректным названием.");
    }

    @Test(priority = 4, groups = "Smoke", description = "проверка удаления категорий")
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

    @Test(priority = 5, groups = "Smoke", description = "тест на изменение категорий")
    public void changeCategory(){
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
        String expectedText = SuccessMessage.CATEGORIES_CHANGE_MESSAGE.getMessage();
        String actualText  = categoriesPage.getAssertText().getText();
        Assert.assertEquals(expectedText, actualText, "Система не позволяет изменять категории администратору.");
    }

    /**
     * Тест для проверки соответствия количества категорий в разделе "Категории" и разделе "Курсы".
     * Этот тест выполняет следующие действия:
     * 1. Переход в раздел "Категории" на странице панели управления.
     * 2. Получение списка всех категорий в разделе "Категории".
     * 3. Переход в раздел "Курсы" на странице панели управления.
     * 4. Открытие окна для добавления курса и выбор категории.
     * 5. Сравнение количества категорий в разделе "Категории" и количестве категорий, доступных для выбора в разделе "Курсы".
     *
     * @throws AssertionError Если количество категорий не совпадает, тест не пройдет.
     */
    @Test(priority = 6, groups = "Smoke", description = "Тест для проверки соответствия количества категорий в разделе Категории и разделе Курсы.")
    public void categoryCursesTest(){
        dashboardPage.selectSection(AdminSection.CATEGORIES);
        ArrayList<Category> categoriesTable = categoriesPage.getCategoryFormTable();

        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.COURSES);
        webElementActions.click(coursesPage.getAddCourse())
                .click(coursesPage.getCategory());

        ArrayList<WebElement> categoryCursesTable = new ArrayList<>(coursesPage.getCategoryClick());
        Assert.assertEquals(categoriesTable.size(), categoryCursesTable.size(),"не прошла проверка по категориям в разделе категории и в по категориям в разделе курсы");
    }
}
