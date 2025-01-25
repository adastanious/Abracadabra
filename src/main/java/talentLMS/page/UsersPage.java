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


    public UsersPage addNewUser(User user){
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

    }

