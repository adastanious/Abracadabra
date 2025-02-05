package talentLMS.page.accountAndSettings;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

import java.util.ArrayList;
import java.util.List;

@Getter

/**
 @author Dastan Aidarov
 */

public class BasicSettingsPage extends BasePage {

    @FindBy(xpath = "//label[@class='control-label' and contains(text(),'Site name')]")
    private WebElement siteName;
    @FindBy(xpath = "//input[@name='site_name']")
    WebElement siteNameCell;

    @FindBy(xpath = "//label[contains(text(),'Site name')]/parent::div//span[@class='help-inline']")
    private WebElement siteNameErrorText;

    @FindBy(xpath = "//label[@class='control-label' and contains(text(),'Site description')]")
    private WebElement siteDescription;

    @FindBy(xpath = "//label[contains(text(),'Site description')]/parent::div//span[@class='help-inline']")
    private WebElement siteDescriptionErrorText;
    @FindBy(xpath = "//input[@name='site_description']")
    WebElement siteDescriptionCell;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement successfullySavedPop_UpMessage;

    @FindBy(xpath = "//div[@id='s2id_default_language']/a[@class='select2-choice']")
    private WebElement defaultLanguageCell;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    WebElement defaultLanguageSearchCell;

    @FindBy(xpath = "//ul[@class='select2-results']//span/parent::div")
    List <WebElement> languageOptions;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement saveBtn;

    @FindBy(xpath = "//i[@title='Log out']")
    private WebElement logOutBtn;

    @FindBy(xpath = "//input[@name= 'password']/parent::div/parent::div/label")
    private WebElement passwordTextInRussian;

    private List<String> languagesList = new ArrayList<>();


    /**
     * Заполняет поле "Название сайта" указанным значением.
     * Предварительно очищает поле, затем вводит переданный текст.
     * @param siteName название сайта, которое нужно ввести
     * @return новый экземпляр страницы BasicSettingsPage
     */
    public BasicSettingsPage fillSiteName(String siteName) {
        webElementActions.clearAndSendKeys(this.siteNameCell, siteName);
        return new BasicSettingsPage();
    }

    /**
     * Заполняет поле "Описание сайта" указанным значением.
     * Предварительно очищает поле, затем вводит переданный текст.
     * @param siteDescription описание сайта, которое нужно ввести
     * @return новый экземпляр страницы BasicSettingsPage
     */
    public BasicSettingsPage fillSiteDescription(String siteDescription) {
        webElementActions.clearAndSendKeys(this.siteDescriptionCell, siteDescription);
        return new BasicSettingsPage();
    }

    public BasicSettingsPage selectLanguage(String language) {
        webElementActions.sendKeysWithEnter(this.defaultLanguageSearchCell, language)
                .click(saveBtn);
        return new BasicSettingsPage();
    }

    public BasicSettingsPage selectDropDownLanguageOption() {
        webElementActions.click(this.defaultLanguageCell);

        for (WebElement language : languageOptions) {
            languagesList.add(language.getText());
        }

        if (!languageOptions.isEmpty()) {
            for (int i = 0; i < languageOptions.size(); i++) {
                webElementActions.click(languageOptions.get(i))
                                .click(this.defaultLanguageCell);
            }
        } else {
            throw new RuntimeException("No languages found in dropdown!");
        }
        return new BasicSettingsPage();
    }

}
