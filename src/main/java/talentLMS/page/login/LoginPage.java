package talentLMS.page.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;
import talentLMS.page.dashboard.DashboardPage;

import java.time.Duration;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name ='login' or @name = 'username']")
    WebElement username;
    @FindBy(xpath = "//input[@name= 'password']")
    WebElement password;
    @FindBy(xpath = "//input[@type ='submit']|//button[@type = 'submit']")
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return new DashboardPage();
    }
}
