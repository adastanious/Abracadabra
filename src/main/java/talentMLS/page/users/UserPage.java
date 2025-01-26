package talentMLS.page.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentMLS.entity.User;
import talentMLS.helper.BasePage;
import talentMLS.page.dashboard.Dashboard;

public class UserPage extends BasePage {
    @FindBy(xpath = "//a[contains(text(),'Add user')]")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

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
    Dashboard dashboard = new Dashboard();


    public UserPage addNewUser(User user, String email){
        webElementActions.click(dashboard.selectSection(sections.getUsers()))
                .click(this.addUser)
                .sendKeys(this.firstName, user.getFirstname())
                .sendKeys(this.lastName, user.getLastname())
                .sendKeys(this.email, email)
                .sendKeys(this.login, user.getUsername())
                .sendKeys(this.password, user.getPassword())
                .click(this.addUserButton);
        return new UserPage();
    }

    public UserPage addNewUserNoCorrect(User user, String email) {
        webElementActions.sendKeys(this.firstName, user.getFirstname())
                .sendKeys(this.lastName, user.getLastname())
                .sendKeys(this.email, email)
                .sendKeys(this.login, user.getUsername())
                .sendKeys(this.password, user.getPassword())
                .click(addUserButton);
        return this;
    }

    // метод редактирования пользователя
    public UserPage editUserName(User user, String email) {
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]"));
        WebElement editBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]/parent::*/following-sibling::*[4]//i[@title='Edit']"));
        webElementActions.moveToElement(createdUser)
                .click(editBtn)
                .clearAndSendKeys(clickEditName, user.getFirstname())
                .click(submit);
        return this;
    }
    // метод удаления пользователя
    public UserPage deleteUsers(String email) {
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]"));
        WebElement xBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]/parent::*/following-sibling::*[4]"));
        webElementActions.moveToElement(createdUser)
                .click(xBtn)
                .click(deleteClick);
        return this;
    }

}
