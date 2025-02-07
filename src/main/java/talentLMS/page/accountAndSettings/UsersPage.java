package talentLMS.page.accountAndSettings;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

import java.util.List;
@Getter
public class UsersPage extends BasePage {
    @FindBy(xpath = "//label[text()='Default user type']/parent::div//a")
    WebElement defaultUserType;

    @FindBy(xpath = "//div[text()='SuperAdmin']/parent::li/parent::ul//div")
    List<WebElement> userTypes;
    @FindBy(xpath = "//input[@name='submit_users']")
    WebElement saveBtn;

    @FindBy(xpath = "//label[text()='User type']/parent::div/div/div/a/span[text()]")
    WebElement userTypeInAddUser;



    public UsersPage selectDefaultUserType (int index) {
        webElementActions.click(this.defaultUserType)
                    .click(this.userTypes.get(index))
                    .click(this.saveBtn);
        return new UsersPage();
    }
}
