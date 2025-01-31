package talentLMS.page.userTypesPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.UserType;
import talentLMS.page.BasePage;

/**
  @author Aizada, Agema, Adinai, Nasyikat, Aiperi
 */
public class UserTypes extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Add user type')]")
    public WebElement addUserType;

    @FindBy(xpath = "//input[@id='user_type_name']")
    public WebElement name;

    @FindBy(xpath = "//span[@class='select2-arrow']")
    public WebElement arrow;

    @FindBy(xpath = "//div[@class='select2-result-label' and contains(text(),'Administrator')]")
    public WebElement clickAdministrator;

    @FindBy(xpath = "//div[@class='select2-result-label' and contains(text(),'Instructor')]")
    public WebElement clickInstructor;

    @FindBy(xpath = "//div[@class='select2-result-label' and contains(text(),'Learner')]")
    public WebElement clickLearner;

    @FindBy(xpath = "//a[@class='dynatree-title' and text()='Administrator']/preceding-sibling::span[@class='dynatree-checkbox']")
    public WebElement checkboxAdministrator;

    @FindBy(xpath = "//a[@class='dynatree-title' and text()='Instructor']/preceding-sibling::span[@class='dynatree-checkbox']")
    public WebElement checkboxInstructor;

    @FindBy(xpath = "//a[@class='dynatree-title' and text()='Learner']/preceding-sibling::span[@class='dynatree-checkbox']")
    public WebElement checkboxLearner;

    @FindBy(xpath = "//a[@class='dynatree-title' and text()='General']/preceding-sibling::span[@class='dynatree-checkbox']")
    public WebElement checkboxGeneral;

    @FindBy(xpath = "//input[@id='user-type-save-button']")
    public WebElement save;

    @FindBy(xpath = "//i[contains(@class, 'icon-pencil')]")
    public WebElement edit;

    @FindBy(xpath = "//td[@class=' tl-align-center hidden-phone']")
    public WebElement checkbox;

    @FindBy(xpath = "//input[@id='user_type_name']")
    public WebElement editName;

    @FindBy(xpath = "//i[@class='icon-ellipsis-h tl-table-operations-icon']")
    public WebElement operationIcon;

    @FindBy(xpath = "//a[@id='confirm_del']")
    public WebElement delete;

    @FindBy(xpath = "//option[@value='4' and text()='Learner-Type']")
    public WebElement learnerType;

    /**
     * Добавляет новый тип пользователя.
     *
     * @param userType  Объект UserType, содержащий данные нового типа пользователя.
     * @param userRole  WebElement, представляющий роль пользователя (администратор, инструктор, ученик).
     * @param checkbox  WebElement, чекбокс, связанный с выбранной ролью.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserType(UserType userType, WebElement userRole, WebElement checkbox) {
        webElementActions.click(this.addUserType)
                .sendKeys(this.name, userType.getName())
                .click(arrow)
                .click(userRole)
                .click(checkbox)
                .click(save);
        return this;
    }

    /**
     * Добавляет пользователя с ролью Администратор.
     *
     * @param userType Объект UserType с данными нового администратора.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserTypeAdministrator(UserType userType) {
        return addUserType(userType, clickAdministrator, checkboxAdministrator);
    }

    /**
     * Добавляет пользователя с ролью Инструктор.
     *
     * @param userType Объект UserType с данными нового инструктора.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserTypeInstructor(UserType userType) {
        return addUserType(userType, clickInstructor, checkboxInstructor);
    }

    /**
     * Добавляет пользователя с ролью Ученик.
     *
     * @param userType Объект UserType с данными нового ученика.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserTypeLearner(UserType userType) {
        return addUserType(userType, clickLearner, checkboxLearner);
    }

    /**
     * Редактирует существующий тип пользователя.
     *
     * @param userType Объект UserType с обновленными данными.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes edit(UserType userType) {
        webElementActions.click(checkbox)
                .click(edit)
                .click(editName)
                .click(save);
        return this;
    }

    /**
     * Удаляет существующий тип пользователя.
     *
     * @param userType Объект UserType, который нужно удалить.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes delete(UserType userType) {
        webElementActions.click(operationIcon)
                .click(learnerType)
                .click(delete);
        return this;
    }
}