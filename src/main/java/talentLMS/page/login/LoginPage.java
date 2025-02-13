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

    public DashboardPage doLogin(String username, String password){
        webElementActions.sendKeys(this.username, username)
                .sendKeys(this.password, password)
                .click(submit);
        return new DashboardPage();
    }
}
