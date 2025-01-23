package talentLMS.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//input[@name ='username' or @name ='login']")
    WebElement username;
    @FindBy(xpath = "//input[@name= 'password']")
    WebElement password;
    @FindBy(xpath = "//button[@type ='submit']")
    WebElement submit;

    public DashboardPage login(String login, String password){
        this.username.sendKeys(login);
        this.password.sendKeys(password);
        submit.click();
        return new DashboardPage();
    }
}
