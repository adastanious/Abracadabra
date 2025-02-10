package talentLMS.page.userTypesPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.driver.Driver;
import talentLMS.entity.UserTypeEntity;
import talentLMS.page.BasePage;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author Aizada, Agema, Adinai, Nasyikat, Aiperi
 *  */

public class UserTypes extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Add user type')]")
    public WebElement addUserType;

    @FindBy(xpath = "//a[contains(text(),'User types')]")
    public WebElement userTypesBtn;

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

    @FindBy(xpath = "//input[@id='user-type-save-button']")
    public WebElement save;

    @FindBy(xpath = "//i[contains(@class, 'icon-pencil')]")
    public WebElement edit;

    @FindBy(xpath = "//td[@class=' tl-align-center hidden-phone']")
    public WebElement checkbox;

    @FindBy(xpath = "//input[@id='user_type_name']")
    public WebElement editName;

    @FindBy(xpath = "//i[contains(@class, 'icon-ellipsis-h tl-table-operations-icon') and contains(@class, 'tl-table')]")
    public WebElement operationIcon;

    @FindBy(xpath = "//a[@class='btn btn-danger']")
    public WebElement delete;

    @FindBy(xpath = "//div[@class='select2-result-label' and text()='SuperAdmin']")
    public WebElement learnerType;

    @FindBy(xpath = "(//a[contains(@class, 'dynatree-title') and text()='Skills']/preceding-sibling::span[contains(@class, 'dynatree-expander')])[last()-1]")
    public WebElement arrowSkillsAI;

    @FindBy(xpath = "//span[@class='help-block' and  contains(text(),'You must specify')]")
    public WebElement getAssertText1;

    @FindBy(xpath = "//span[@class='help-inline' and contains(text(),'The assigned default')]")
    public WebElement AssertText2;

    // универсальный локатор arrow
    public WebElement getArrowElement(String elementName) {
        String xpath = "//a[text()='" + elementName + "']/preceding-sibling::span[contains(@class,'dynatree-expander')]";
        return driver.findElement(By.xpath(xpath));
    }

    // универсальный локатор checkbox
    public WebElement getCheckboxElement(String elementName) {
        String xpath = "//a[text()='" + elementName + "']/preceding-sibling::span[contains(@class,'dynatree-checkbox')]";
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getExpanderArrow(String title) {
        String dynamicXpath = "(//a[contains(@class, 'dynatree-title') and text()='" + title + "']/preceding-sibling::span[contains(@class, 'dynatree-expander')])[last()]";
        return Driver.getDriver().findElement(By.xpath(dynamicXpath));
    }

    public String getSuccessMessage() {
        return driver.findElement(By.id("successMessage")).getText(); // Укажи правильный локатор
    }

    /**
     * Добавляет новый тип пользователя.
     * @param userType Объект UserType, содержащий данные нового типа пользователя.
     * @param userRole WebElement, представляющий роль пользователя (администратор, инструктор, ученик).
     * @param checkbox WebElement, чекбокс, связанный с выбранной ролью.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserTypeBtn(UserTypeEntity userType, WebElement userRole, WebElement checkbox) {
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
     * @param userType Объект UserType с данными нового администратора.
     * @return Объект UserTypes после выполнения операции.
     */

    public UserTypes addUserTypeAdministrator(UserTypeEntity userType) {
        return addUserTypeBtn(userType, clickAdministrator, checkboxAdministrator);
    }

    /**
     * Добавляет пользователя с ролью Инструктор.
     * @param userType Объект UserType с данными нового инструктора.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserTypeInstructor(UserTypeEntity userType) {
        return addUserTypeBtn(userType, clickInstructor, checkboxInstructor);
    }

    /**
     * Добавляет пользователя с ролью Ученик.
     * @param userType Объект UserType с данными нового ученика.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes addUserTypeLearner(UserTypeEntity userType) {
        return addUserTypeBtn(userType, clickLearner, checkboxLearner);
    }

    /**
     * Редактирует существующий тип пользователя.
     * @param userType Объект UserType с обновленными данными.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes edit(UserTypeEntity userType) {
        webElementActions
                .click(checkbox)
                .click(edit)
                .click(editName)
                .click(save);
        return this;
    }

    /**
     * Удаляет существующий тип пользователя.
     * @param userType Объект UserType, который нужно удалить.
     * @return Объект UserTypes после выполнения операции.
     */
    public UserTypes deleteType(UserTypeEntity userType) {
        webElementActions.click(userTypesBtn)
                .click(operationIcon)
                .click(arrow)
                .click(learnerType)
                .click(delete);
        return this;
    }

    /**
     * Удаляет тип пользователя в негативном сценарии.
     * @param userType Тип пользователя для удаления.
     * @return Текущий объект UserTypes.
     */
    public UserTypes deleteNegative(UserTypeEntity userType) {
        webElementActions.click(userTypesBtn)
                .click(operationIcon)
                .click(delete);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для пользователей.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления пользователей.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorUsers(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Users"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для курсов.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления курсов.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorCourses(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Courses"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для групп.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления групп.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorCroups(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Groups"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для категорий.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления категорий.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorCategories(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Categories"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для филиалов (branches).
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления филиалов.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorBranches(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Branches"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для уведомлений событий.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления уведомлений событий.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorEventsNotifications(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Events engine"))
                .click(getArrowElement("Notifications"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для автоматизаций событий.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления автоматизаций событий.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorEventsAutomations(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Events engine"))
                .click(getArrowElement("Automations"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для импорта и экспорта данных.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами импорта и экспорта данных.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorImportExport(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Import - Export"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("Import"))
                .click(getCheckboxElement("Export"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для типов пользователей.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления типов пользователей.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorUserTypes(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("User types"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для настроек аккаунта.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами доступа к просмотру и обновлению настроек аккаунта, а также правами доступа к различным функциональным областям,
     * таким как геймификация, навыки, электронная коммерция, домен, подписка и другие.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorAccountSettings(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Account & Settings"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Gamification"))
                .click(getCheckboxElement("Skills"))
                .click(getCheckboxElement("E-commerce"))
                .click(getCheckboxElement("Domain"))
                .click(getCheckboxElement("Subscription"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для отчетов.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами доступа к различным отчетам, включая пользователей, курсы, группы, филиалы,
     * тесты, опросы, задания, ILT и кастомные отчеты.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorReports(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Reports"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("Users"))
                .click(getCheckboxElement("Courses"))
                .click(getCheckboxElement("Groups"))
                .click(getCheckboxElement("Branches"))
                .click(getCheckboxElement("Tests"))
                .click(getCheckboxElement("Surveys"))
                .click(getCheckboxElement("Assignments"))
                .click(getCheckboxElement("ILTs"))
                .click(getCheckboxElement("Custom"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для файлов.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами просмотра, создания, обновления и удаления файлов.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorFiles(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Files"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для навыков.
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами доступа для просмотра, создания, обновления, назначения/удаления, одобрения/отклонения
     * запросов и удаления навыков.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorSkills(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("Skills"))
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Assign/Unassign"))
                .click(getCheckboxElement("Approve/Reject requests"))
                .click(getCheckboxElement("Delete"))
                .click(save);
        return this;
    }

    /**
     * Добавляет администратора с правами доступа для искусственного интеллекта (AI).
     * Метод выполняет последовательные действия на веб-странице для создания типа пользователя
     * с правами доступа к функционалу искусственного интеллекта, включая создание навыков,
     * генерацию изображений, создание вопросов для навыков, предложению курсов и ресурсов, а также доступу к пулу талантов.
     * @param userType объект, содержащий данные о типе пользователя, который будет добавлен.
     * @return текущий экземпляр {@link UserTypes}, позволяя использовать метод в цепочке вызовов.
     */
    public UserTypes addAdministratorAI(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getArrowElement("AI"))
                .click(arrowSkillsAI)
                .click(arrow)
                .click(clickAdministrator)
                .click(getCheckboxElement("Create skills"))
                .click(getCheckboxElement("Generate skill images"))
                .click(getCheckboxElement("Generate skill questions"))
                .click(getCheckboxElement("Assign related courses to skills"))
                .click(getCheckboxElement("Suggest skill resources"))
                .click(getCheckboxElement("Talent pool"))
                .click(save);
        return this;
    }

    /**
     * Добавляет новый тип пользователя с ролью "Instructor" и назначает права на курсы и пользователей.
     * @param userType данные пользователя ({@link UserTypeEntity})
     * @return {@link UserTypes} для цепочечных вызовов
     */
    public UserTypes addInstructorCourses(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(getExpanderArrow("Courses"))
                .click(arrow)
                .click(clickInstructor)
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Create"))
                .click(getCheckboxElement("Update"))
                .click(getCheckboxElement("Share"))
                .click(getCheckboxElement("Delete"))
                .click(getExpanderArrow("Users"))
                .click(getCheckboxElement("View"))
                .click(getCheckboxElement("Add"))
                .click(getCheckboxElement("Reset"))
                .click(getCheckboxElement("Remove"))
                .click(save);
        return this;
    }

    /**
     * Извлекает данные о типах пользователей из таблицы на веб-странице и создает список объектов {@link UserTypeEntity}.
     * Метод находит все строки в таблице с типами пользователей и извлекает из каждой строки имя пользователя.
     * Если в строке меньше 5 ячеек, она пропускается. Полученные данные используются для создания объектов
     * {@link UserTypeEntity}, которые добавляются в список.
     * @return список объектов {@link UserTypeEntity}, содержащих имена типов пользователей.
     */
    public ArrayList<UserTypeEntity> getUserTypeFromTable() {
        List<WebElement> rows = Driver.getDriver().findElements(By.cssSelector("table#tl-list-user-types tbody tr"));
        ArrayList<UserTypeEntity> userTypeEntities = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));

            // Проверяем, что в строке есть как минимум 5 ячеек
            if (cells.size() < 5) {
                System.out.println("Skipping row due to missing cells: " + cells.size());
                continue;
            }

            String name = cells.get(0).getText().trim();  // Исправлено с 1 на 0
            userTypeEntities.add(new UserTypeEntity(name));
        }
        return userTypeEntities;
    }

    /**
     * Проверяет, что система не позволяет создать пользователя с некорректной комбинацией ролей.
     * Назначает одновременно роли "Administrator" и "Instructor" и пытается сохранить.
     * @param userType данные нового пользователя ({@link UserTypeEntity})
     * @return текущий объект {@link UserTypes} для цепочечных вызовов
     */
    public UserTypes notCorrectUserType(UserTypeEntity userType) {
        webElementActions.click(addUserType)
                .sendKeys(name, userType.getName())
                .click(arrow)
                .click(clickAdministrator)
                .click(checkboxInstructor)
                .click(save);
        return this;
    }
}