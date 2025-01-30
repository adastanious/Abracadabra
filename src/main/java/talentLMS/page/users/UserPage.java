package talentLMS.page.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.User;
import talentLMS.page.BasePage;

/**
 @author Nasyikat Aiperi Aizada
 */

public class UserPage extends BasePage {

    @FindBy(xpath = "//div[@class='tl-header-tools pull-left']//a[contains(text(),'Add user')]")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//td[@class=' tl-align-left hidden-phone']/span[@title='Learner-Type']")
    public WebElement learnerType;

    @FindBy(xpath = "(//div/i[@class='icon-pencil icon-grid' and @alt='Edit'])[1]")
    public WebElement edit;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement clickEditName;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement submit;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteClick;

    /**
     * Общий метод для ввода данных пользователя.
     *
     * @param user объект класса User, содержащий данные пользователя (имя, фамилия, email, логин, пароль).
     */
    private void enterUserData(User user) {
        webElementActions
                .sendKeys(this.firstName, user.getFirstname())
                .sendKeys(this.lastName, user.getLastname())
                .sendKeys(this.email, user.getEmail())
                .sendKeys(this.login, user.getUsername())
                .sendKeys(this.password, user.getPassword());
    }

    /**
     * Метод добавления нового пользователя.
     *
     * @param user объект класса User с данными нового пользователя.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage addNewUser(User user) {
        webElementActions
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(this.addUserButton);
        return new UserPage();
    }

    /**
     * Метод добавления пользователя с некорректным email.
     * Используется для проверки обработки ошибок при вводе данных.
     *
     * @param user объект класса User с некорректным email.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage userWithIncorrectEmail(User user) {
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(addUserButton);
        return new UserPage();
    }

    /**
     * Метод редактирования данных существующего пользователя.
     *
     * @param user объект класса User с обновленными данными (например, имя).
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage editUser(User user) {
        webElementActions
                .click(learnerType)
                .click(edit)
                .click(clickEditName)
                .sendKeys(clickEditName, user.getFirstname())
                .click(submit);
        return new UserPage();
    }

    /**
     * Метод удаления пользователя по email.
     *
     * @param email строка, содержащая email пользователя, которого необходимо удалить.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage deleteUser(String email) {
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]"));
        WebElement xBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]/parent::*/following-sibling::*[4]"));
        webElementActions
                .moveToElement(createdUser)
                .click(xBtn)
                .click(deleteClick);
        return new UserPage();
    }

    /**
     * Метод добавления пользователя с уникальным email.
     * Используется для проверки уникальности email при добавлении нового пользователя.
     *
     * @param user объект класса User с уникальным email.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage uniqueEmail(User user) {
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(addUserButton);
        return new UserPage();
    }
}