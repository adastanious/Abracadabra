package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

import java.util.List;

public class LearnerUserRole extends BasePage {
    /**
     @author Turan, Agema, Nazik
     */

    @FindBy(xpath = "//span[@class ='tl-formatted-course-name']")
    public List<WebElement> courses;

}
