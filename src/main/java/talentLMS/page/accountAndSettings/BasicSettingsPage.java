package talentLMS.page.accountAndSettings;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.enums.accountAndSettings.AccountAndSettings;
import talentLMS.page.BasePage;
import talentLMS.page.dashboard.DashboardPage;

import java.util.ArrayList;
import java.util.List;

@Getter

/**
 @author Dastan Aidarov
 */

public class BasicSettingsPage extends BasePage {

    @FindBy(xpath = "//label[@class='control-label' and contains(text(),'Site name')]")
    WebElement siteName;

    @FindBy(xpath = "//input[@name='site_name']")
    WebElement siteNameCell;

    @FindBy(xpath = "//label[contains(text(),'Site name')]/parent::div//span[@class='help-inline']")
    WebElement siteNameErrorText;

    @FindBy(xpath = "//label[@class='control-label' and contains(text(),'Site description')]")
    WebElement siteDescription;

    @FindBy(xpath = "//label[contains(text(),'Site description')]/parent::div//span[@class='help-inline']")
    WebElement siteDescriptionErrorText;

    @FindBy(xpath = "//input[@name='site_description']")
    WebElement siteDescriptionCell;

    @FindBy(xpath = "//div[@class='toast-message']")
    WebElement successfullySavedPop_UpMessage;

    @FindBy(xpath = "//div[@id='s2id_default_language']/a[@class='select2-choice']")
    WebElement defaultLanguageCell;

    @FindBy(xpath = "//select[@name='default_language']/parent::div//input")
    WebElement defaultLanguageSearchCell;

    @FindBy(xpath = "//ul[@class='select2-results']//span/parent::div")
    List <WebElement> languageOptions;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement saveBtn;

    @FindBy(xpath = "//i[@title='Log out']")
    WebElement logOutBtn;

    @FindBy(xpath = "//input[@name= 'password']/parent::div/parent::div/label")
    WebElement passwordTextInRussian;

    @FindBy(xpath = "//li[@class='select2-no-results']")
    WebElement noMatchesFoundText;

    @FindBy(xpath = "//div[@id='s2id_default_timezone']/a[@class='select2-choice']")
    WebElement defaultTimeZoneCell;

    @FindBy(xpath = "//li/div[@class='select2-result-label']")
    List <WebElement> timeZoneOptions;

    @FindBy(xpath = "//select[@name='default_timezone']/parent::div//input")
    WebElement defaultTimeZoneSearchCell;

    @FindBy(xpath = "//select[@name='timezone']/preceding-sibling::div/a")
    WebElement userTimeZone;

    @FindBy(xpath = "//select[@name='date_format']/preceding-sibling::div/a")
    WebElement dateFormatZoneCell;

    @FindBy(xpath = "//div//ul[@class='select2-results']/li/div")
    List <WebElement> dateFormatOptions;

    @FindBy(xpath = "//button[@id='tl-admin-right-sidebar-show-timeline']")
    WebElement timeLineBtn;

    @FindBy(xpath = "//li[@id='extended-timeline']/a")
    WebElement extendedTimeLineBtn;

    @FindBy(xpath = "//input[@id='tl-filter-timeline-from']")
    WebElement timelineFromCell;

    @FindBy(xpath = "//*[local-name()='svg']/*[local-name()='g' and @class='highcharts-axis-labels highcharts-xaxis-labels']/child::*[1]")
    WebElement timeFormatInReports;

    @FindBy(xpath = "//label[text()='Time format']//parent::div//a")
    WebElement timeFormatCell;

    @FindBy(xpath = "//div//ul[@class='select2-results']/li/div")
    List <WebElement> timeFormatOptions;

    @FindBy(xpath = "//label[text()='Currency']//parent::div//a")
    WebElement currencyCell;
    @FindBy(xpath = "//select[@name='currency']/parent::div//input")
    WebElement currencySearchCell;

    @FindBy(xpath = "//div//ul[@class='select2-results']/li/div")
    List <WebElement> currencyOptions;

    @FindBy(xpath = "//a[@id='show-price']")
    WebElement priceBtnInCourses;

    @FindBy(xpath = "//label[text()='Price']//parent::div/child::div/div/span")
    WebElement priceCurrencyInCourses;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']")
    WebElement settingsSectionsList;

    @FindBy (xpath = "//a[text()='cancel']")
    WebElement cancelBtn;

    @FindBy (xpath = "//textarea[@name='announcement']")
    WebElement internalAnnouncementCell;

    @FindBy (xpath = "//div[@id='tl-internal-announcement']/p")
    WebElement announcementText;

    private String randomName = randomSettingsGenerator.randomSiteName();
    private String randomDescription = randomSettingsGenerator.randomSiteDescription();
    private String randomIncorrectName = randomSettingsGenerator.randomSiteNameOver40Chars();
    private String randomIncorrectDescription = randomSettingsGenerator.randomSiteDescriptionOver255Chars();

    private List<String> languagesList = new ArrayList<>();
    private List<String> timeZonesList = new ArrayList<>();
    private List<String> dateFormatsList = new ArrayList<>();
    private List<String> timeFormatsList = new ArrayList<>();
    private List<String> currenciesList = new ArrayList<>();

    /**
     * Выбирает указанный раздел настроек на странице.
     *
     * Метод ищет элемент списка настроек по тексту переданного раздела
     * и выполняет клик по нему.
     *
     * @param section Раздел настроек, который необходимо выбрать (объект AccountAndSettings).
     * @return Объект DashboardPage после выбора раздела.
     */
    public DashboardPage selectSettingsSection(AccountAndSettings section) {
        webElementActions.click(settingsSectionsList.findElement(By.xpath("//a[contains(text(),'" + section + "')]")));
        return new DashboardPage();
    }

    @Step("Заполнение имени сайта или описания: {text}")
    /**
     * Заполняет поле "Название или описание сайта" указанным значением.
     * Предварительно очищает поле, затем вводит переданный текст.
     * @param element вебэлемент в который нужно ввести текст.
     * @param text название или описание сайта, которое нужно ввести.
     * @return новый экземпляр страницы BasicSettingsPage.
     */
    public BasicSettingsPage fillSiteOrDescriptionsName(WebElement element, String text) {
        webElementActions.sendKeysWithEnter(element, text)
                .click(saveBtn);
        return new BasicSettingsPage();
    }

    @Step("Выбор языка из выпадающего списка: {language}")
    /**
     * Выбирает язык из выпадающего списка и сохраняет изменения.
     *
     * @param language Название языка, который нужно выбрать.
     * @return Новый экземпляр BasicSettingsPage.
     */
    public BasicSettingsPage selectLanguage(String language) {
        try {
            webElementActions.sendKeysWithEnter(this.defaultLanguageSearchCell, language)
                    .click(saveBtn);
        } catch (NoSuchElementException e) {
            System.err.println("The language is not found");
        }
        return new BasicSettingsPage();
    }

    @Step("Выбор всех доступных языков из выпадающего списка")
    /**
     * Открывает выпадающий список языков и добавляет все доступные языки в список languagesList.
     * Затем перебирает доступные языковые опции, кликая по каждой из них.
     *
     * @return Новый экземпляр BasicSettingsPage.
     */
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
            throw new RuntimeException("No language is found in dropdown list!");
        }
        return new BasicSettingsPage();
    }

    @Step("Выбор всех доступных часовых поясов из выпадающего списка")
    /**
     * Открывает выпадающий список часовых поясов и добавляет все доступные часовые пояса в список timeZonesList.
     * Затем перебирает доступные часовые пояса, кликая по каждому из них.
     *
     * @return Новый экземпляр BasicSettingsPage.
     */
    public BasicSettingsPage selectDropDownTimeZoneOption() {
        webElementActions.click(this.defaultTimeZoneCell);

        for (WebElement timeZone : timeZoneOptions) {
            timeZonesList.add(timeZone.getText());
        }

        if (!timeZoneOptions.isEmpty()) {
            for (int i = 0; i < timeZoneOptions.size(); i++) {
                webElementActions.click(timeZoneOptions.get(i))
                        .click(this.defaultTimeZoneCell);
            }
        } else {
            throw new RuntimeException("No time zone is found in dropdown list!");
        }
        return new BasicSettingsPage();
    }

    @Step("Выбор часового пояса: {timeZone}")
    /**
     * Выбирает часовой пояс из выпадающего списка и сохраняет изменения.
     *
     * @param timeZone Название часового пояса, который нужно выбрать.
     * @return Новый экземпляр BasicSettingsPage.
     */
    public BasicSettingsPage selectTimeZone(String timeZone) {
        webElementActions.sendKeysWithEnter(this.defaultTimeZoneSearchCell, timeZone)
                .click(saveBtn);
        return new BasicSettingsPage();
    }

    @Step("Выбор всех доступных форматов даты из выпадающего списка")
    /**
     * Открывает выпадающий список форматов даты и добавляет все доступные варианты в список dateFormatsList.
     *
     * @return Новый экземпляр BasicSettingsPage.
     */
    public BasicSettingsPage selectDropDownDateFormatOption() {
        try {
            webElementActions.click(this.dateFormatZoneCell);
            for (WebElement dateFormat : dateFormatOptions) {
                dateFormatsList.add(dateFormat.getText());
            }

        } catch (NoSuchElementException e) {
            System.err.println("The date format is not found");
        }
        return new BasicSettingsPage();
    }

    @Step("Выбор всех доступных валют из выпадающего списка")
    /**
     * Открывает выпадающий список валют и добавляет все доступные валюты в список currenciesList.
     * Затем перебирает доступные валютные опции, кликая по каждой из них.
     *
     * @return Новый экземпляр BasicSettingsPage.
     */
    public BasicSettingsPage selectDropDownCurrencyOption() {
        webElementActions.click(this.currencyCell);

        for (WebElement language : currencyOptions) {
            currenciesList.add(language.getText());
        }

        if (!currencyOptions.isEmpty()) {
            for (int i = 0; i < currencyOptions.size(); i++) {
                webElementActions.click(currencyOptions.get(i))
                                .click(this.currencyCell);
            }
        } else {
            throw new RuntimeException("No currency is found in dropdown list!");
        }

        return new BasicSettingsPage();
    }

    @Step("Выбор валюты: {currency}")
    /**
     * Выбирает валюту из выпадающего списка и сохраняет изменения.
     *
     * @param currency Название валюты, которую нужно выбрать.
     * @return Новый экземпляр BasicSettingsPage.
     */
    public BasicSettingsPage selectCurrency(String currency) {
        webElementActions.sendKeysWithEnter(this.currencySearchCell, currency)
                .click(saveBtn);

        return new BasicSettingsPage();
    }

    @Step("Создание внутреннего объявления: {text}")
    /**
     * Создает внутреннее объявление, вводя текст в соответствующее поле
     * и нажимая кнопку "Сохранить".
     *
     * @param text Текст объявления
     * @return Новый экземпляр BasicSettingsPage
     */
    public BasicSettingsPage makeAnAnnouncement(String text) {
        webElementActions.clearAndSendKeys(this.internalAnnouncementCell, text)
                        .click(this.saveBtn);

        return new BasicSettingsPage();
    }
}
