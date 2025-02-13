package talentLMS.page.accountAndSettings;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

import java.util.ArrayList;
import java.util.List;
@Getter

/**
 @author Dastan Aidarov
 */

public class UsersPage extends BasePage {
    @FindBy(xpath = "//label[text()='Default user type']/parent::div/div//a")
    WebElement defaultUserType;

    @FindBy(xpath = "//div[text()='SuperAdmin']/parent::li/parent::ul//div")
    List<WebElement> userTypes;
    @FindBy(xpath = "//input[@name='submit_users']")
    WebElement saveBtn;

    @FindBy(xpath = "//label[text()='User type']/parent::div/div/div/a/span[text()]")
    WebElement userTypeInAddUser;

    @FindBy(xpath = "//a[text()='Visible user format']")
    WebElement visibleUserFormat;

    @FindBy(xpath = "//span[contains(text(),'name') or contains(text(),'Username')]/parent::a")
    WebElement visibleUserFormatCell;

    @FindBy(xpath = "//div[text()='Username']/parent::li/parent::ul//div")
    List<WebElement> userFormats;

    @FindBy(xpath = "//ul[@class='nav pull-right']/li/a/span")
    WebElement actualUserFormat;

    private List<String> userTypesList = new ArrayList<>();
    private List<String> userFormatsList = new ArrayList<>();

    @Step("Получение списка типов пользователей по умолчанию")
    /**
     * Получает список доступных типов пользователей по умолчанию.
     *
     * Метод открывает выпадающий список типов пользователей и
     * собирает их названия в список.
     *
     * @return Список строк с названиями типов пользователей.
     */
    public List <String> getDefaultUserTypes() {
        webElementActions.click(this.defaultUserType);

        for (WebElement userType : this.userTypes) {
            this.userTypesList.add(userType.getText());
        }

        return userTypesList;
    }

    @Step("Выбор типа пользователя по индексу {index} и сохранение изменений")
    /**
     * Выбирает заданный тип пользователя по индексу и сохраняет изменения.
     *
     * Метод сначала открывает список типов пользователей, затем выбирает
     * указанный элемент по индексу и нажимает кнопку сохранения.
     *
     * @param index Индекс типа пользователя в списке.
     * @return Объект UsersPage после сохранения изменений.
     */

    public UsersPage selectDefaultUserType (int index) {

        if (index > 0) {
            webElementActions.click(this.defaultUserType)
                            .click(this.userTypes.get(index))
                            .click(this.saveBtn);
        } else {
            webElementActions.click(this.userTypes.get(index))
                            .click(this.saveBtn);
        }

        return new UsersPage();
    }

    @Step("Получение списка доступных форматов отображения пользователей")
    /**
     * Получает список доступных форматов отображения пользователей.
     *
     * Метод открывает выпадающий список форматов и собирает их
     * названия в список.
     *
     * @return Список строк с названиями доступных форматов отображения пользователей.
     */
    public List <String> getVisibleUserFormats() {
        webElementActions.click(this.visibleUserFormat)
                        .click(this.visibleUserFormatCell);

        for (WebElement userFormat : userFormats) {
            this.userFormatsList.add(userFormat.getText());
        }

        return userFormatsList;
    }

    @Step("Выбор формата отображения пользователей по индексу {index} и сохранение изменений")
    /**
     * Выбирает заданный формат отображения пользователей по индексу и сохраняет изменения.
     *
     * Метод сначала открывает список доступных форматов, затем выбирает
     * указанный элемент по индексу и нажимает кнопку сохранения.
     *
     * @param index Индекс формата отображения пользователей в списке.
     * @return Объект UsersPage после сохранения изменений.
     */
    public UsersPage selectVisibleUserFormat (int index) {

        if (index > 0) {
            webElementActions.click(this.visibleUserFormat)
                    .click(this.visibleUserFormatCell)
                    .click(this.userFormats.get(index))
                    .click(this.saveBtn);
        } else {
            webElementActions.click(this.userFormats.get(index))
                    .click(this.saveBtn);
        }
        return new UsersPage();
    }
}
