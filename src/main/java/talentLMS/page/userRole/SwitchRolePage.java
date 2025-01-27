package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

public class SwitchRolePage extends BasePage {
    @FindBy(xpath = "//a[normalize-space()='Learner']")
    public WebElement learnerUserRole;


    @FindBy(xpath = "//a[normalize-space()='Instructor']")
    public WebElement instructorUserRole;


//    public void clickLearnerUserRole(){
//        webElementActions.click(learnerUserRole);
//    }
//
//    public void clickInstructorUserRole(){
//        webElementActions.click(instructorUserRole);
//    }

//    public void clickRole(String role){
//        String folr = String.format("//b[text()='%s']", role);
//        @FindBy( xpath = String.format("//b[text()='%s']", role))
//        webElementActions.click();
//    }




        public void clickUserRole(String role) {
            switch (role.toLowerCase()) {
                case "instructor":
                    webElementActions.click(instructorUserRole);
                    break;
                case "learner":
                    webElementActions.click(learnerUserRole);
                    break;
                default:
                    throw new IllegalArgumentException("Неверная роль пользователя: " + role);
            }
        }
    }


