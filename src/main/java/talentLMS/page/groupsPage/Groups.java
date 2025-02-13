package talentLMS.page.groupsPage;

import io.qameta.allure.Step;
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



     @Step("Добавляет новую группу")
    public Groups addNewGroup(Group group) {
        webElementActions.click(addGroup)
                .sendKeys(this.name, group.getName())
                .sendKeys(this.description, group.getDescription())
                .click(submit);
        return this;
    }


     @Step("Получает текст сообщения об успешном выполнении или ошибке")
    public String getSuccessOrErrorMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='toast-message']")));

        // Сохраняем текст через JavaScript (даже если элемент исчезнет)
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", messageElement);
    }


     @Step("Выполняет клики для добавления пользователя в группу")
    public Groups clickAddUserToGroupBtn() {
        webElementActions.click(addUserToGroupBtn)
                .click(clickUsers)
                .click(addUser)
                .click(addUser2);
        return this;
    }


     @Step("Редактирует существующую группу")
    public Groups editGroup(Group group) {
        webElementActions.click(clickDescription)
                .click(edit)
                .sendKeys(this.name, group.getName())
                .sendKeys(this.description, group.getDescription())
                .click(updateGroup);
        return this;
    }


    @Step("Удаляет группу")
    public Groups deleteGroup(Group group) {
        webElementActions
                .click(options) // клик по кнопке с опциями группы
                .click(clickDelete); // клик по кнопке удаления
        return this;
    }



    @Step("Получает список всех названий групп из таблицы на странице")
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


   @Step("Проверяет, была ли удалена группа (отсутствует ли она в списке)")
    public boolean isGroupDeleted(List<String> groupNames, String groupToCheck) {
        return !groupNames.contains(groupToCheck);
    }


     @Step("Выводит список оставшихся групп в консоль")
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

     @Step("Отменяет удаление группы")
     public Groups deleteGroupCancel(Group group) {
        webElementActions
                .click(options)
                .click(cancel);
        return this;

    }


     @Step("Назначает курс указанной группе")
     public void assignCourseToGroup(Group group) {
        webElementActions.click(massActions)
                .click(addGroupsToAllGroups)
                .click(btnAdd);
    }
}







