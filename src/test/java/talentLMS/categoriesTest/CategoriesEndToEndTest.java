package talentLMS.categoriesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import talentLMS.enums.ErrorMessage;

import java.util.ArrayList;

import static org.testng.AssertJUnit.fail;
import static talentLMS.enums.Role.*;

public class CategoriesEndToEndTest extends BaseTest {
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

    @Test(priority = 1)
    public void addIncorrectCategories(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getIncorrectCategoryName(), category.getIncorrectPrice());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_MORE80.toString();
        String actualText = categoriesPage.getIncorrectAssertText().getText();
        Assert.assertEquals(actualText,expectedText, "Система позволяет создавать категории с названиями длинной более 80 символов.");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.toString();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории c не корректным прайсом.");
    }
    @Test(priority = 2)
    public void addIncorrectCategories1(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectCategoryName());
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.toString();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории прайсом из букв.");
    }

    @Test(priority = 3)
    public void addIncorrectCategories2(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getIncorrectCategoryName(), category.getCorrectPrice());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_MORE80.toString();
        String actualText = categoriesPage.getIncorrectAssertText().getText();
        Assert.assertEquals(actualText, expectedText, "Система позволяет создавать категории с названиями длинной более 80 символов.");
    }

    @Test(priority = 4)
    public void addIncorrectCategories3(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getIncorrectPrice());
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.toString();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории c не корректным прайсом.");
    }

    @Test(priority = 5)
    public void addIncorrectCategories4(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getIncorrectCategoryName(), category.getCorrectCategoryName());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_MORE80.toString();
        String actualText = categoriesPage.getIncorrectAssertText().getText();
        Assert.assertEquals(actualText, expectedText, "Система позволяет создавать категории с названиями длинной более 80 символов.");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.toString();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории прайсом из букв.");
    }

    @Test(priority = 6)
    public void addIncorrectCategories5(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory("", category.getCorrectPrice());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_LESS1.toString();
        String actualText = categoriesPage.getIncorrectAssertText2().getText();
        Assert.assertEquals(actualText, expectedText, "Система позволяет создавать категории с названиями длинной менее 1 символа.");
    }

    @Test(priority = 7)
    public void addIncorrectCategories6(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        categoriesPage.addCategory(category.getCorrectCategoryName(), "-500");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.toString();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории с отрицательным прайсом.");
    }

    @Test(priority = 8)
    public void instructorCategoriesTest(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        component.selectRole(INSTRUCTOR);
        try {
            // Пытаемся добавить категорию
            categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
            fail("система дает инструктору создать категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
            }
    }
    @Test(priority = 9)
    public void instructorCategoriesTest2(){
        try {
            categoriesPage.deleteCategory();
            fail("система дает инструктору удалить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }

    @Test(priority = 10)
    public void instructorCategoriesTest3(){
        try {
            // Пытаемся добавить категорию
            categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
            fail("система дает инструктору создать категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }



    @Test(priority = 11)
    public void learnerCategoriesTest(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
        component.selectRole(LEARNER);
        try {
            categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
            fail("система дает ученику изменить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }
    @Test(priority = 12)
    public void learnerCategoriesTest2(){
        try {
            categoriesPage.deleteCategory();
            fail("система дает ученику удалить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }

    @Test(priority = 13)
    public void learnerCategoriesTest3(){
        try {
            categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
            fail("система дает ученику изменить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }
}
