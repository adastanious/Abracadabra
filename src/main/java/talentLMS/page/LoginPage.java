package talentLMS.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//input[@name ='username' or @name ='login']")
    WebElement username;
    @FindBy(xpath = "//input[@name= 'password']")
    WebElement password;
    @FindBy(xpath = "//button[@type ='submit']")
    WebElement submit;

    public NewDashboardPage login(String login, String password){
        webElementActions.sendKeys(this.username,login);
        webElementActions.sendKeys(this.password, password);
        webElementActions.click(submit);
        return new NewDashboardPage();
    }
}
