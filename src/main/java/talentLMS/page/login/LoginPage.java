package talentLMS.page.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;
import talentLMS.page.dashboard.DashboardPage;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name ='login']")
    WebElement username;
    @FindBy(xpath = "//input[@name= 'password']")
    WebElement password;
    @FindBy(xpath = "//input[@type ='submit']")
    WebElement submit;

    //error messages
    @FindBy(xpath = "//div/span/span[contains(text(),'Username')]|//div/div[contains(text(),'This is a required field') and @data-testid ='username-error']")
    public WebElement emptyUsernameMessage;
    @FindBy(xpath = "//div/span/span[contains(text(),'Password')]|//div/div[contains(text(),'This is a required field')]")
    public WebElement emptyPasswordMessage;
    @FindBy(xpath = "//div/div[contains(text(), 'Your username or password is incorrect')]|//p[contains(text(), 'Your username or password is incorrect')]")
    public WebElement wrongLoginDataMessage;

    public DashboardPage doLogin(String username, String password){
        webElementActions.sendKeys(this.username, username)
                .sendKeys(this.password, password)
                .click(submit);
        return new DashboardPage();
    }
}
