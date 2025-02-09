package talentLMS.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import talentLMS.driver.Driver;

import talentLMS.helper.WebElementActions;
import talentLMS.utils.randomEntityUtils.RandomSettingsGenerator;


public abstract class BasePage {
    public WebElementActions webElementActions = new WebElementActions();
    public WebDriver driver = Driver.getDriver();

    public RandomSettingsGenerator randomSettingsGenerator = new RandomSettingsGenerator();



    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}