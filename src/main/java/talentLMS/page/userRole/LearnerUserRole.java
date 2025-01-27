package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LearnerUserRole extends UserRole{
    /**
     @author Turan, Agema, Nazik
     */
    @FindBy(id = "tl-learner-option")
    public WebElement learnerUserRole;

    public void clickLearnerUserRole(){
        webElementActions.click(learnerUserRole);
    }

    @FindBy(xpath = "//span[@class ='tl-formatted-course-name']")
    public List<WebElement> courses;
}
