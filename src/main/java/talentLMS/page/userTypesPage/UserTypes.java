package talentLMS.page.userTypesPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.driver.Driver;
import talentLMS.entity.UserTypeEntity;
import talentLMS.page.BasePage;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author Aizada
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

    @Step("Добавляет новый тип пользователя")
    public UserTypes addUserTypeBtn(UserTypeEntity userType, WebElement userRole, WebElement checkbox) {
        webElementActions.click(this.addUserType)
                .sendKeys(this.name, userType.getName())
                .click(arrow)
                .click(userRole)
                .click(checkbox)
                .click(save);
        return this;
    }

    @Step(" Добавляет пользователя с ролью Администратор.")
    public UserTypes addUserTypeAdministrator(UserTypeEntity userType) {
        return addUserTypeBtn(userType, clickAdministrator, checkboxAdministrator);
    }

    @Step ("Добавляет пользователя с ролью Инструктор.")
    public UserTypes addUserTypeInstructor(UserTypeEntity userType) {
        return addUserTypeBtn(userType, clickInstructor, checkboxInstructor);
    }

    @Step("Добавляет пользователя с ролью Ученик")
    public UserTypes addUserTypeLearner(UserTypeEntity userType) {
        return addUserTypeBtn(userType, clickLearner, checkboxLearner);
    }

    @Step("Удаляет существующий тип пользователя")
    public UserTypes deleteType(UserTypeEntity userType) {
        webElementActions
                .click(operationIcon)
                .click(arrow)
                .click(learnerType)
                .click(delete);
        return this;
    }


    @Step("Удаляет тип пользователя в негативном сценарии")
    public UserTypes deleteNegative(UserTypeEntity userType) {
        webElementActions.click(userTypesBtn)
                .click(operationIcon)
                .click(delete);
        return this;
    }

    @Step("Добавляет администратора с правами доступа для пользователей")
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

    @Step("Добавляет администратора с правами доступа для курсов")
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

    @Step("Добавляет администратора с правами доступа для групп")
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

    @Step("Добавляет администратора с правами доступа для категорий")
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

    @Step("Добавляет администратора с правами доступа для филиалов (branches)")
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

    @Step("Добавляет администратора с правами доступа для уведомлений событий")
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

    @Step("Добавляет администратора с правами доступа для автоматизаций событий.")
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

    @Step("Добавляет администратора с правами доступа для импорта и экспорта данных")
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

    @Step("Добавляет администратора с правами доступа для типов пользователей")
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

    @Step("Добавляет администратора с правами доступа для настроек аккаунта")
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

    @Step("Добавляет администратора с правами доступа для отчетов")
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

    @Step("Добавляет администратора с правами доступа для файлов")
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

    @Step("Добавляет администратора с правами доступа для навыков")
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

    @Step("Добавляет администратора с правами доступа для искусственного интеллекта (AI)")
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


    @Step("Добавляет новый тип пользователя с ролью Instructor и назначает права на курсы и пользователей.")
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

    @Step("Извлекает данные о типах пользователей из таблицы на веб-странице и создает список объектов")
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

    @Step("Проверяет, что система не позволяет создать пользователя с некорректной комбинацией ролей")
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