package talentLMS.page.groupsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;
import talentLMS.entity.Group;
import talentLMS.page.BasePage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * @author Aiperi, Nasyikat, Aiza, Agema, Adinai
 */
public class Groups extends BasePage {


    @FindBy(xpath = "//a[text()='Add group']")
    public WebElement addGroup;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement name;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement description;

    @FindBy(xpath = "//input[@name='submit_group']")
    public WebElement submit;

    @FindBy(xpath = "//tr[@class='odd']/td[@class=' tl-align-left hidden-phone']")
    public WebElement clickDescription;

    @FindBy(xpath = "(//span[starts-with(text(),'Group')])[1]")
    public WebElement addUserToGroupBtn;

    @FindBy(xpath = "//a[text()='Users']")
    public WebElement clickUsers;

    @FindBy(xpath = "//i[@alt='Add to group' and @data-userid='1']")
    public WebElement addUser;

    @FindBy(xpath = "//i[@class='icon-plus icon-grid tl-toggle-user' and @data-userid='123']")
    public WebElement addUser2;

    @FindBy(xpath = "//i[@title='Edit']")
    public WebElement edit;

    @FindBy(xpath = "//input[@name='submit_group']")
    public WebElement updateGroup;

    @FindBy(xpath = "//i[@class='icon-ellipsis-h tl-table-operations-icon']")
    public WebElement options;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement clickDelete;

    @FindBy(xpath = "//*[@id=\"tl-confirm\"]/div/a[@class='btn']")
    public WebElement cancel;

    @FindBy(xpath = "//a[@role='button']")
    public WebElement massActions;

    @FindBy(xpath = "//a[@data-mode='add' and @role='menuitem' and text()='Add a course to all groups']")
    public WebElement addGroupsToAllGroups;

    @FindBy(xpath = "//a[@id='submit-add-remove-course']")
    public WebElement btnAdd;


    /**
     * Добавляет новую группу.
     *
     * @param group объект группы с именем и описанием.
     * @return текущий объект Groups для цепочки вызовов.
     */
    public Groups addNewGroup(Group group) {
        webElementActions.click(addGroup)
                .sendKeys(this.name, group.getName())
                .sendKeys(this.description, group.getDescription())
                .click(submit);
        return this;
    }

    /**
     * Получает текст сообщения об успешном выполнении или ошибке.
     *
     * @return текст сообщения (например, "Success! New group created.").
     */
    public String getSuccessOrErrorMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='toast-message']")));

        // Сохраняем текст через JavaScript (даже если элемент исчезнет)
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", messageElement);
    }

    /**
     * Выполняет клики для добавления пользователя в группу.
     *
     * Этот метод выполняет серию кликов для инициации процесса добавления пользователя в группу.
     * Сначала кликается кнопка 'Добавить пользователя в группу', затем выбираются соответствующие опции
     * для добавления пользователей в группу.
     *
     * @return текущий экземпляр объекта Groups, что позволяет использовать цепочку вызовов методов.
     */
    public Groups clickAddUserToGroupBtn() {
        webElementActions.click(addUserToGroupBtn)
                .click(clickUsers)
                .click(addUser)
                .click(addUser2);
        return this;
    }

    /**
     * Редактирует существующую группу.
     * <p>
     * Этот метод выбирает группу для редактирования, заполняет новые значения
     * для имени и описания и отправляет форму для обновления группы.
     *
     * @param group Объект группы с обновленными данными (имя и описание).
     * @return Текущий экземпляр класса Groups для продолжения цепочки действий.
     */
    public Groups editGroup(Group group) {
        webElementActions.click(clickDescription)
                .click(edit)
                .sendKeys(this.name, group.getName())
                .sendKeys(this.description, group.getDescription())
                .click(updateGroup);
        return this;
    }

    /**
     * Удаляет группу.
     * <p>
     * Этот метод открывает меню опций для группы и подтверждает её удаление.
     *
     * @param group Объект группы, которую необходимо удалить.
     * @return Текущий экземпляр класса Groups для продолжения цепочки действий.
     */
    public Groups deleteGroup(Group group) {
        webElementActions
                .click(options) // клик по кнопке с опциями группы
                .click(clickDelete); // клик по кнопке удаления
        return this;
    }


    /**
     * Получает список всех названий групп из таблицы на странице.
     * <p>
     * Метод ожидает загрузки таблицы в течение 5 секунд и затем извлекает названия всех групп.
     * Если таблица не загрузится за это время, в консоль выводится сообщение об ошибке.
     *
     * @return список строк с названиями групп.
     */
    public List<String> getAllGroupNamesTable() {
        List<String> groupNames = new ArrayList<>();

        // Ждём загрузки таблицы
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table#tl-groups-grid tbody tr")));
        } catch (TimeoutException e) {
            System.out.println("Группы не загрузились!");
        }

        // Находим все строки с группами
        List<WebElement> groups = driver.findElements(By.cssSelector("table#tl-groups-grid tbody tr"));

        System.out.println("Найдено групп: " + groups.size());

        for (WebElement group : groups) {
            String groupName = group.getText().trim();
            System.out.println("Группа в таблице: " + groupName);
            groupNames.add(groupName);
        }
        return groupNames;
    }

    /**
     * Проверяет, была ли удалена группа (отсутствует ли она в списке).
     *
     * @param groupNames   список названий групп, полученный из таблицы.
     * @param groupToCheck название группы, которую нужно проверить.
     * @return true, если группы нет в списке (она была удалена), иначе false.
     */
    public boolean isGroupDeleted(List<String> groupNames, String groupToCheck) {
        return !groupNames.contains(groupToCheck);
    }

    /**
     * Выводит список оставшихся групп в консоль.
     * <p>
     * Если групп нет, выводится соответствующее сообщение.
     *
     * @param groupNames список оставшихся групп.
     */
    public void printRemainingGroups(List<String> groupNames) {
        if (groupNames.isEmpty()) {
            System.out.println("Нет оставшихся групп!");
        } else {
            System.out.println("Оставшиеся группы:");
            for (String groupName : groupNames) {
                System.out.println(groupName);
            }
        }
    }
    /**
     * Отменяет удаление группы.
     *
     * Этот метод выполняет серию кликов для отмены операции удаления группы. Сначала кликается
     * опция в меню, затем выбирается кнопка отмены.
     *
     * @param group объект группы, для которой выполняется операция. Параметр передается для
     *              контекста, хотя не используется непосредственно в методе.
     * @return текущий экземпляр объекта Groups, что позволяет использовать цепочку вызовов методов.
     */
    public Groups deleteGroupCancel(Group group) {
        webElementActions
                .click(options)
                .click(cancel);
        return this;

    }

    /**
     * Назначает курс указанной группе.
     * Метод выполняет последовательные клики по веб-элементам:
     * 1. massActions - открывает меню массовых действий.
     * 2. addGroupsToAllGroups - выбирает опцию добавления групп.
     * 3. btnAdd - подтверждает выбор.
     *
     * @param group Группа, которой назначается курс.
     */
    public void assignCourseToGroup(Group group) {
        webElementActions.click(massActions)
                .click(addGroupsToAllGroups)
                .click(btnAdd);
    }
}







