package talentLMS.page.groupsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;
import talentLMS.entity.Group;
import talentLMS.entity.User;
import talentLMS.page.BasePage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    @FindBy(xpath = "//i[@title='Edit']")
    public WebElement edit;

    @FindBy(xpath = "//input[@name='submit_group']")
    public WebElement updateGroup;

    @FindBy(xpath = "//i[@class='icon-ellipsis-h tl-table-operations-icon']")
    public WebElement options;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement clickDelete;

    @FindBy(xpath = "//i[@class='icon-plus icon-grid tl-toggle-user' and @data-userid='1']")
    public WebElement addUser;

    @FindBy(xpath = "//i[@class='icon-plus icon-grid tl-toggle-user' and @data-userid='123']")
    public WebElement addUser2;

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
     * <p>
     * Этот метод нажимает кнопку "Add group", затем заполняет поля
     * для имени и описания группы и отправляет форму для создания новой группы.
     *
     * @param group Объект группы, содержащий имя и описание.
     * @return Текущий экземпляр класса Groups для продолжения цепочки действий.
     */
    public Groups addNewGroup(Group group) {
        webElementActions.click(addGroup)
                .sendKeys(this.name, group.getName())
                .sendKeys(this.description, group.getDescription())
                .click(submit);
        return this;
    }

    public String getSuccessOrErrorMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='toast-message']")));

        // Сохраняем текст через JavaScript (даже если элемент исчезнет)
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String messageText = (String) js.executeScript("return arguments[0].innerText;", messageElement);
        return messageText;
    }

    public Groups addUserToGroup(User user) {
        webElementActions.click(addUser)
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

//        public boolean isGroupDeleted(Group group) {
//        try {
//            driver.findElement(By.xpath("//td[text()='" + group.getName() + "']"));
//            return false; // Если элемент найден, значит группа не удалена
//        } catch (NoSuchElementException e) {
//            return true; // Если элемент не найден, значит группа успешно удалена
//        }
// Получаем список всех названий групп
public List<String> getAllGroupNames() {
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

    // Проверяем, что группа удалена (её нет в списке)
    public boolean isGroupDeleted(List<String> groupNames, String groupToCheck) {
        return !groupNames.contains(groupToCheck);
    }

    // Вывод оставшихся групп
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

    public Groups deleteGroupCancel(Group group) {
        webElementActions
                .click(options)
                .click(cancel);
        return this;

    }

    // Назначение курса группе
    public void assignCourseToGroup(Group group) {
        webElementActions.click(massActions)
                .click(addGroupsToAllGroups)
                .click(btnAdd);
    }

    // Проверка назначения курса
    public boolean isCourseAssigned(String groupName, String courseName) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + groupName + "')]"));
        return groupElement.getText().contains(courseName);
    }

}





