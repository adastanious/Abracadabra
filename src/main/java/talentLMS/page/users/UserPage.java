package talentLMS.page.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.User;
import talentLMS.page.BasePage;

public class UserPage extends BasePage {
    @FindBy(xpath = "//div[@class='tl-header-tools pull-left']//a[contains(text(),'Add user')]")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;
    @FindBy(xpath = "//div[@class='span8']/child::*[3]//span[@class='help-block']")
    public WebElement errorMessage;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;


    // xpath кнопки поля first name в разделе edit
    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement clickEditName;

    //xpath кнопки submit
    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement submit;

    // xPath удалить пользователя
    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteClick;


    public UserPage addNewUser(User user, String email, boolean isNegativeCase) {
        // Заполняем форму для добавления нового пользователя
        webElementActions
                .click(this.addUser) // Кликаем на кнопку "Добавить пользователя"
                .sendKeys(this.firstName, user.getFirstname()) // Вводим имя пользователя
                .sendKeys(this.lastName, user.getLastname()) // Вводим фамилию пользователя
                .sendKeys(this.email, email) // Вводим email
                .sendKeys(this.login, user.getUsername()) // Вводим логин
                .sendKeys(this.password, user.getPassword()) // Вводим пароль
                .click(this.addUserButton); // Нажимаем на кнопку добавления пользователя

        // Проверяем, является ли это негативным кейсом
        if (isNegativeCase) {
            // Если это негативный кейс, проверяем наличие сообщения об ошибке
            if (webElementActions.isVisible(this.errorMessage)) {
                String errorText = webElementActions.getText(this.errorMessage); // Получаем текст ошибки
                System.out.println("Error message displayed: " + errorText); // Выводим текст ошибки
            } else {
                System.out.println("Expected an error, but no error message was displayed."); // Если ошибки нет
            }
            return this; // Возвращаем текущую страницу для дальнейшей проверки
        }

        // Если это позитивный кейс, возвращаем страницу нового пользователя
        return new UserPage();
    }

    // метод редактирования пользователя
    public UserPage editUserName(User user, String email) {
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]"));
        WebElement editBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]/parent::*/following-sibling::*[4]//i[@title='Edit']"));
        webElementActions
                .moveToElement(createdUser)
                .click(editBtn)
                .clearAndSendKeys(clickEditName, user.getFirstname())
                .click(submit);
        return this;
    }
    // метод удаления пользователя
    public UserPage deleteUsers(String email) {
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]"));
        WebElement xBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]/parent::*/following-sibling::*[4]"));
        webElementActions
                .moveToElement(createdUser)
                .click(xBtn)
                .click(deleteClick);
        return this;
    }

}
