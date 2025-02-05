package talentLMS.page.categoriesPage;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.entity.Category;
import talentLMS.enums.AdminSection;
import talentLMS.page.BasePage;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoriesPage extends BasePage {
    /**
     @author Turan
     */

    @FindBy(xpath = "//a[contains(text(),'Add category')]")
    WebElement addCategory;

    @FindBy(xpath = "//input[@name='submit_category']")
    WebElement addCategoryBtn;

    @FindBy(xpath = "//input[@name='name']")
    WebElement categoryNameInput;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    WebElement parentCategoryBtn;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    List<WebElement> parentCategory1Btn;

    @FindBy(xpath = "//a[@class='tl-bold-link']")
    WebElement priceBtn;

    @FindBy(xpath = "//input[@name='price']")
    WebElement priceInput;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement assertText;

    @FindBy(xpath ="//td[@class=\" tl-align-center tl-table-operations-cell\"]")
    WebElement moveElement;

    @FindBy(xpath = "//i[@class='icon-remove icon-grid']")
    WebElement deleteBtn;

    @FindBy(xpath = "//i[@class ='icon-pencil icon-grid']")
    WebElement changeBtn;

    @FindBy(id = "tl-confirm-submit")
    WebElement deleteControlBtn;

    @FindBy(xpath = "//input[@name = 'name']")
    WebElement changeNameCategoryInput;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"'Name' cannot exceed 80 characters\"]")
    WebElement incorrectAssertText;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"'Name' is required\"]")
    WebElement incorrectAssertText2;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"This is not a valid 'Price'\"]")
    WebElement incorrectAssertPriceText;

    /**
     * Метод для добавления новой категории на страницу.
     * Этот метод выполняет действия по добавлению категории с заранее заданным названием и ценой.
     * 1. Кликает по элементу для добавления категории.
     * 2. Вводит название категории в соответствующее поле.
     * 3. Открывает список родительских категорий и выбирает первую доступную категорию.
     * 4. Кликает по кнопке для ввода цены и задает цену категории.
     * 5. Нажимает кнопку для подтверждения добавления категории.
     *
     * @return Возвращает текущую страницу категорий (CategoriesPage), что позволяет использовать метод в цепочке вызовов.
     */
    public CategoriesPage addCategory( String name, String price) {
        webElementActions.click(addCategory)
                .sendKeys(categoryNameInput, name)
                .click(priceBtn)
                .sendKeys(priceInput, price)
                .click(addCategoryBtn);
        if (webElementActions.isElementClickable(assertText)) {
            webElementActions.moveToElement(assertText);
        }
        return this;
    }

    /**
     * Метод для получения списка категорий из таблицы на странице.
     * Этот метод извлекает все строки таблицы, где находятся данные о категориях,
     * извлекает название каждой категории и создает соответствующий объект CategoryEntity.
     * 1. Находит все элементы таблицы, представляющие категории, с помощью XPath.
     * 2. Извлекает текст каждого элемента (название категории).
     * 3. Создает новый объект `CategoryEntity` для каждой категории с ее названием.
     * 4. Возвращает список объектов `CategoryEntity`, представляющих все категории.
     *
     * @return Список объектов CategoryEntity, представляющих категории.
     */
    public ArrayList<Category> getCategoryFormTable() {
        List<WebElement> rows = driver.findElements(By.xpath("//td[@class=' tl-align-left']"));
        ArrayList<Category> categoryEntities = new ArrayList<>();
        for (int i =0; i < rows.size(); i++) {
            String name = rows.get(i).getText();
            categoryEntities.add(new Category(name));
        }
        return categoryEntities;
    }

    /**
     * Метод для удаления категории.
     * Этот метод выполняет действия по удалению категории. Он:
     * 1. Перемещает указатель мыши на элемент, указанный в `moveElement`.
     * 2. Кликает по кнопке удаления категории.
     * 3. Подтверждает удаление, кликая по кнопке подтверждения удаления.
     *
     * Метод возвращает текущую страницу категорий, что позволяет использовать метод в цепочке вызовов.
     * @return Возвращает текущую страницу категорий (CategoriesPage) для продолжения взаимодействия с интерфейсом.
     */
    public CategoriesPage deleteCategory(){
        sections.getCategories();
        webElementActions.moveToElement(moveElement)
                .click(deleteBtn)
                .click(deleteControlBtn);
        return this;
    }

    /**
     * Метод для изменения существующей категории.
     * Этот метод выполняет действия по изменению категории, обновляя её название и цену:
     * 1. Перемещает указатель мыши на элемент, связанный с изменением категории.
     * 2. Кликает по кнопке изменения категории.
     * 3. Очищает текущее название категории и вводит новое, заданное в объекте `category`.
     * 4. Очищает текущую цену категории и вводит новую, заданную в объекте `category`.
     * 5. Нажимает кнопку для подтверждения изменений.
     *
     * Метод возвращает текущую страницу категорий, что позволяет использовать метод в цепочке вызовов.
     * @return Возвращает текущую страницу категорий (CategoriesPage) для продолжения взаимодействия с интерфейсом.
     */    public CategoriesPage changeCategory(String changeName, String changePrice){
        webElementActions.moveToElement(moveElement)
                .click(changeBtn)
                .clearAndSendKeys(changeNameCategoryInput, changeName)
                .click(priceBtn)
                .clearAndSendKeys(priceInput, changePrice)
                .click(addCategoryBtn)
                .moveToElement(assertText);
        return this;
    }
}
