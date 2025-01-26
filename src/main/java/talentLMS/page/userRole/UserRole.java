package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

public class UserRole extends BasePage {
    @FindBy(xpath = "//a[@class = 'dropdown-toggle tl-nabvar-roles-button']")
    public WebElement userRole;


    public void moveUserRole(){
        webElementActions.moveToElement(userRole);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
