package talentMLS.page.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentMLS.page.BasePage;
import talentMLS.page.login.LoginPage;

import java.util.*;

public class Dashboard extends BasePage {

    @FindBy(xpath = "//span[@class='arrow-down']")
    WebElement subMenu;
    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    WebElement legacyMenuItem;


    public WebElement selectSection(String section) {
        List<WebElement> sections = driver.findElements(By.xpath("//div[@class='tl-bold-link']/a"));
        List<String> sectionNames = new ArrayList<>();
        for (WebElement sectionName : sections) {
            sectionNames.add(sectionName.getText());
        }
        WebElement selectedSection = driver.findElement(By.xpath("//div[@class='tl-bold-link']/a[contains(text(),'" + section + "')]"));
        return selectedSection;
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
