package talentLMS.page.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.enums.AdminSection;
import talentLMS.page.BasePage;
import talentLMS.page.login.LoginPage;

import java.util.NoSuchElementException;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//span[@class='arrow-down']")
    WebElement subMenu;
    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    WebElement legacyMenuItem;

    @FindBy(xpath = "//div[@class='tl-bold-link']")
    WebElement sectionsList;


    public LoginPage selectSection(AdminSection section) {
//        switchToLegacyInterface();
        webElementActions.click(sectionsList.findElement(By.xpath("//a[contains(text(),'" + section + "')]")));
        return new LoginPage();
    }

    public LoginPage switchToLegacyInterface () {
        try {
            webElementActions.click(subMenu)
                    .click(legacyMenuItem);
            return new LoginPage();
        } catch (NoSuchElementException e) {
            return new LoginPage();
        }
    }
}
