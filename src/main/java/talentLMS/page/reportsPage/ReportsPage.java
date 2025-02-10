package talentLMS.page.reportsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;
import talentLMS.helper.WebElementActions;
import talentLMS.page.BasePage;

import java.time.Duration;

public class ReportsPage extends BasePage {

        @FindBy(xpath = "//a[contains(text(), 'Reports')]")
        public WebElement Reports;

        @FindBy(xpath = "//a[@title='User reports']")
        public WebElement userReports;

        @FindBy(xpath = "(//i[@class='icon-signal icon-grid'])[2]")
        public WebElement reportsButton;

        @FindBy(xpath = "//span[@title='Learner-Type']")
        public WebElement learnerType;

        public ReportsPage goUserReports(){
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='User reports']")));
            webElementActions.click(element);
            return this;
        }

        public ReportsPage reportBtn(){
            webElementActions
                    .click(learnerType)
                    .click(reportsButton);
            return this;
        }
}
