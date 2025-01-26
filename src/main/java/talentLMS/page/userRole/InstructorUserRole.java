package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InstructorUserRole extends UserRole {

    @FindBy(xpath = "(//i[@class='tl-select-icon' ])[1]")
    public WebElement instructorUserRole;

    public void clickInstructorUserRole(){
        webElementActions.click(instructorUserRole);
    }

}
