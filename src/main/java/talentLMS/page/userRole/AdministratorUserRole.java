package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

public class AdministratorUserRole extends BasePage {
    /**
     @author Turan, Agema, Nazik
     */

    @FindBy(xpath = "//div[@class='hidden-phone']/a[normalize-space(text())='Add user']")
    public WebElement addUser;

    @FindBy(xpath ="//a[contains(text(),'Add course')]")
    public WebElement addCourse;

    @FindBy(xpath = "//a[contains(text(),'Add category')]")
    public WebElement addCategories;

    @FindBy(xpath = "//a[contains(text(),'Add group')]")
    public WebElement addGroups;

    @FindBy(xpath = "//a[contains(text(),'Add branch')]")
    public WebElement addBranch;

    @FindBy(xpath = "//a[contains(text(),'Add notification')]")
    public WebElement addNotification;

    @FindBy(xpath = "//a[contains(text(),'Add automation')]")
    public WebElement addAutomaition;

    @FindBy(xpath = "//a[contains(text(),'Add user type')]")
    public WebElement addUserType;

    @FindBy(xpath = " //a[contains(text(),'Import - Export')]")
    public WebElement importExport;

}
