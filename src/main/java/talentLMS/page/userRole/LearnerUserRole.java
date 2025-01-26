package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LearnerUserRole extends UserRole{
    @FindBy(xpath = "(//i[@class='tl-select-icon' ])[2]")
    public WebElement learnerUserRole;

    public void clickLearnerUserRole(){
        webElementActions.click(learnerUserRole);
    }
}
