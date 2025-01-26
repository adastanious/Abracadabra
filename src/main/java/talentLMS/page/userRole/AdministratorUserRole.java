package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdministratorUserRole extends UserRole{

    @FindBy(xpath = "//i[@class ='tl-select-icon-selected']")
    public WebElement administratorUserRole;

    public void clickAdministratorUserRole(){
        webElementActions.click(administratorUserRole);
    }

}
