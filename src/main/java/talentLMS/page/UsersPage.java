package talentLMS.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.User;

public class UsersPage extends BasePage {

    // xpath для поля firstName
    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    // xpath для поля lastName
    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    // xpath для поля email
    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    // xpath для поля login
    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    // xpath для поля password
    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    // xpath для поля Bio
    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement bioDescription;

    // xpath для  addUserButton
    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    // xPath для клика users
    @FindBy(xpath = "(//span[@class='tl-box-title-options']/a)[2]")
    public WebElement users;

    //xpath поля name в разделе редактирования
    @FindBy(xpath = "//td[@class=' tl-align-left hidden-phone']/span[@title='Learner-Type']")
    public WebElement learnerType;

    // xpath кнопки edit
    @FindBy(xpath = "(//div/i[@class='icon-pencil icon-grid' and @alt='Edit'])[2]")
    public WebElement edit;

    // xpath кнопки поля first name в разделе edit
    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement clickEditName;

    //xpath кнопки submit
    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement submit;

    // xPath значок troetochie
    @FindBy(xpath = "((//td[@class=' tl-align-center tl-table-operations-cell']/div[@class='tl-table-operations-trigger touchable'])[2])")
    public WebElement operationIcon;

    // xPath удалить пользователя
    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteClick;

    // метод для добавления нового пользователя
    public UsersPage addNewUser(User user) {
        webElementActions.waitElementToBeDisplayed(this.firstName);
        webElementActions.sendKeys(this.firstName, user.getFirstname())
                .sendKeys(this.lastName, user.getLastname())
                .sendKeys(this.email, user.getEmail())
                .sendKeys(this.login, user.getUsername())
                .sendKeys(this.password, user.getPassword())
                .sendKeys(this.bioDescription, user.getBio())
                .click(addUserButton);
        return this;
    }
    // проверка негативного кейса(email)
    public UsersPage addNewUserNoCorrect(String firstName, String lastName, String email, String login, String password, String bioDescription) {
        webElementActions.waitElementToBeDisplayed(this.firstName);
        webElementActions.sendKeys(this.firstName, firstName)
                .sendKeys(this.lastName, lastName)
                .sendKeys(this.email, email)
                .sendKeys(this.login, login)
                .sendKeys(this.password, password)
                .sendKeys(this.bioDescription, bioDescription)
                .click(addUserButton);
        return this;
    }

    // метод редактирования пользователя
    public UsersPage editUserName(User user) {
        webElementActions.click(learnerType)
                .click(edit)
                .click(clickEditName)
                .sendKeys(clickEditName, user.getFirstname())
                .click(submit);
        return this;
    }
    // метод удаления пользователя
    public UsersPage deleteUsers(User user) {
        webElementActions.click(operationIcon)
                .click(deleteClick);
        return this;
    }
}









