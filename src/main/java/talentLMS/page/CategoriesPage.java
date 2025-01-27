package talentLMS.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoriesPage extends BasePage {
    @FindBy(xpath = "//div[@class= 'hidden-phone']/a[normalize-space(text())='Add category']")
    public WebElement addCategoryBtn;

    @FindBy(xpath = "//div[@class= 'tl-bold-link']/a[normalize-space(text())='Categories']")
    public WebElement categoriesBtn;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameCategoryTB;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    public WebElement parentCategoryBtn;

    @FindBy(xpath = "//div[@class='controls form-inline']/a[normalize-space(text())='Price']")
    public WebElement priceBtn;

    @FindBy(xpath = "//input[@name='price']")
    public WebElement priceTB;

    @FindBy(xpath = "//td[@class=' tl-align-left']")
    public List<WebElement> category;

    @FindBy(xpath = "//div[@class='tl-table-operations touchable']")
    public WebElement deleteBtn;

    @FindBy(id = "tl-confirm-submit")
    public WebElement deleteControlBtn;



}
