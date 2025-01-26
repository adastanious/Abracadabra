package talentLMS.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewDashboardPage extends BasePage{
    @FindBy(xpath = "//span[@class='arrow-down']")
    WebElement subMenu;
    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    WebElement legacyMenuItem;

    public DashboardPage switchToLegacyInterface () {
        try {
            if (driver.findElement(By.xpath("//strong[contains(text(), 'New interface')]")).isDisplayed()) {
                System.out.println("You are already using legacy interface");
                return new DashboardPage();
            }

        } catch (NoSuchElementException e) {
            webElementActions.click(subMenu);
            webElementActions.click(legacyMenuItem);
            return new DashboardPage();
        }
        return new DashboardPage();
    }
}
