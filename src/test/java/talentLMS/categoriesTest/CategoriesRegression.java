package talentLMS.categoriesTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import talentLMS.enums.ErrorMessage;

import java.util.ArrayList;

import static org.testng.AssertJUnit.fail;
import static talentLMS.enums.Role.*;

public class CategoriesRegression extends BaseTest {
    /**
     @author Turan
     */
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
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
    @Test(priority = 0, groups = "Regression", description = "Тест для проверки удаления всех категорий в разделе Категории")
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

    @Test(priority = 1, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories(){
        categoriesPage.addCategory(category.getIncorrectCategoryName(), category.getIncorrectPrice());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_MORE80.getMessage();
        String actualText = categoriesPage.getIncorrectAssertText().getText();
        Assert.assertEquals(actualText,expectedText, "Система позволяет создавать категории с названиями длинной более 80 символов.");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.getMessage();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории c не корректным прайсом.");
    }
    @Test(priority = 2, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories1(){
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectCategoryName());
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.getMessage();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории прайсом из букв.");
    }

    @Test(priority = 3, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories2(){
        categoriesPage.addCategory(category.getIncorrectCategoryName(), category.getCorrectPrice());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_MORE80.getMessage();
        String actualText = categoriesPage.getIncorrectAssertText().getText();
        Assert.assertEquals(actualText, expectedText, "Система позволяет создавать категории с названиями длинной более 80 символов.");
    }

    @Test(priority = 4, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories3(){
        categoriesPage.addCategory(category.getCorrectCategoryName(), category.getIncorrectPrice());
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.getMessage();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории c не корректным прайсом.");
    }

    @Test(priority = 5, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories4(){
        categoriesPage.addCategory(category.getIncorrectCategoryName(), category.getCorrectCategoryName());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_MORE80.getMessage();
        String actualText = categoriesPage.getIncorrectAssertText().getText();
        Assert.assertEquals(actualText, expectedText, "Система позволяет создавать категории с названиями длинной более 80 символов.");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.getMessage();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории прайсом из букв.");
    }

    @Test(priority = 6, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories5(){
        categoriesPage.addCategory("", category.getCorrectPrice());
        String expectedText = ErrorMessage.CATEGORIES_ADD_NAME_LENGTH_LESS1.getMessage();
        String actualText = categoriesPage.getIncorrectAssertText2().getText();
        Assert.assertEquals(actualText, expectedText, "Система позволяет создавать категории с названиями длинной менее 1 символа.");
    }

    @Test(priority = 7, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories6(){
        categoriesPage.addCategory(category.getCorrectCategoryName(), "-500");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.getMessage();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории с отрицательным прайсом.");
    }

    @Test(priority = 8, groups = "Regression", description = "Тест на создание категорий с некоректными данными")
    public void addIncorrectCategories8(){
        categoriesPage.addCategory(category.getCorrectCategoryName(), "XII");
        String expectedTextPrice = ErrorMessage.CATEGORIES_PRICE_MESSAGE.getMessage();
        String actualTextPrice = categoriesPage.getIncorrectAssertPriceText().getText();
        Assert.assertEquals(actualTextPrice, expectedTextPrice, "Система позволяет создавать категории прайсом из римских цифр.");
    }

    /**
     * Тесты для проверки прав доступа инструктора к функционалу управления категориями.
     * Эти тесты проверяют, что система не позволяет инструктору:
     * 1. Создавать новую категорию.
     * 2. Удалять существующие категории.
     * 3. Изменять информацию о категории.
     *
     * В каждом тесте:
     * - Устанавливается роль пользователя как "Инструктор".
     * - Пытаемся выполнить операцию (добавить, удалить или изменить категорию).
     * - Если операция выполняется без ошибки, то тест завершается с ошибкой с сообщением, что операция запрещена для инструктора.
     * - Если при попытке выполнить операцию возникает исключение, то тест проходит успешно, так как это ожидаемое поведение.
     */
    @Test(priority = 9, groups = "Regression", description = "Тесты для проверки прав доступа инструктора к функционалу управления категориями.(создание категорий)")
    public void instructorCategoriesTest(){
        component.selectRole(INSTRUCTOR);
        try {
            // Пытаемся добавить категорию
            categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
            fail("система дает инструктору создать категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
            }
    }
    @Test(priority = 10, groups = "Regression", description = "Тесты для проверки прав доступа инструктора к функционалу управления категориями.(удаление категорий)")
    public void instructorCategoriesTest2(){
        component.selectRole(INSTRUCTOR);
        try {
            categoriesPage.deleteCategory();
            fail("система дает инструктору удалить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }

    @Test(priority = 11, groups = "Regression", description = "Тесты для проверки прав доступа инструктора к функционалу управления категориями.(изменение категорий)")
    public void instructorCategoriesTest3(){
        component.selectRole(INSTRUCTOR);
        try {
            // Пытаемся добавить категорию
            categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
            fail("система дает инструктору создать категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }



    /**
     * Тесты для проверки прав доступа ученику к функционалу управления категориями.
     * Эти тесты проверяют, что система не позволяет ученику:
     * 1. Создавать новую категорию.
     * 2. Удалять существующие категории.
     * 3. Изменять информацию о категории.
     *
     * В каждом тесте:
     * - Устанавливается роль пользователя как "Ученик".
     * - Пытаемся выполнить операцию (добавить, удалить или изменить категорию).
     * - Если операция выполняется без ошибки, то тест завершается с ошибкой с сообщением, что операция запрещена для инструктора.
     * - Если при попытке выполнить операцию возникает исключение, то тест проходит успешно, так как это ожидаемое поведение.
     */
    @Test(priority = 12, groups = "Regression", description = "Тесты для проверки прав доступа ученику к функционалу управления категориями.(создание категорий)")
    public void learnerCategoriesTest(){
        component.selectRole(LEARNER);
        try {
            categoriesPage.addCategory(category.getCorrectCategoryName(), category.getCorrectPrice());
            fail("система дает ученику изменить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }

    @Test(priority = 13, groups = "Regression", description = "Тесты для проверки прав доступа ученику к функционалу управления категориями.(удаление категорий)")
    public void learnerCategoriesTest2(){
        component.selectRole(LEARNER);
        try {
            categoriesPage.deleteCategory();
            fail("система дает ученику удалить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }

    @Test(priority = 14, groups = "Regression", description = "Тесты для проверки прав доступа ученику к функционалу управления категориями.(изменение категорий)")
    public void learnerCategoriesTest3(){
        component.selectRole(LEARNER);
        try {
            categoriesPage.changeCategory(category.getCorrectCategoryName2(), category.getCorrectPrice2());
            fail("система дает ученику изменить категорию");
        } catch (Exception e) {
            // Если исключение возникло, тест проходит успешно
        }
    }
}
