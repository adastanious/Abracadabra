package talentLMS;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;

import java.util.ArrayList;
import java.util.List;

/**
 @author Dastan Aidarov
 */

public class BasicSettingsTest extends BaseTest {
    private String randomName = randomSettingsGenerator.randomSiteName();
    private String randomDescription = randomSettingsGenerator.randomSiteDescription();
    private String randomIncorrectName = randomSettingsGenerator.randomSiteNameOver40Chars();
    private String randomIncorrectDescription = randomSettingsGenerator.randomSiteDescriptionOver255Chars();

    @BeforeClass
    public void beforeClass() {
       // driver.get("https://aidas.talentlms.com/index");
        driver.get("https://aidas.talentlms.com/dashboard");
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(AdminSection.ACCOUNT_SETTINGS);
    }

    @BeforeTest
    public void beforeTest() {
        driver.get("https://aidas.talentlms.com/account/basic_index");
    }

    @Test(description = "verify that it is impossible to save a site name longer than 40 characters", priority = 1)
    public void siteNameOverTheLimitTest() {
        basicSettingsPage.fillSiteName(this.randomIncorrectName);
        webElementActions.click(basicSettingsPage.getSaveBtn());

        String siteNameColor = basicSettingsPage.getSiteName().getCssValue("color");
        Color color1 = Color.fromString(siteNameColor);
        String nameColor = color1.asHex();
        Assert.assertEquals(nameColor,"#b94a48","Colors are not the same");

        try {
            String siteNameErrorText = basicSettingsPage.getSiteNameErrorText().getText();
            Assert.assertEquals(siteNameErrorText,"'Site name' cannot exceed 40 characters");
        } catch (NoSuchElementException e) {
            System.err.println("The site name error text is not appeared.");
        }

        String errorTextColor = basicSettingsPage.getSiteNameErrorText().getCssValue("color");
        Color color2 = Color.fromString(errorTextColor);
        String textColor = color2.asHex();
        Assert.assertEquals(textColor,"#b94a48","Colors are not the same");

        basicSettingsPage.getSiteNameCell().clear();
    }

    @Test(description = "verify that it is impossible to save a site description longer than 255 characters", priority = 2)
    public void siteDescriptionOverTheLimitTest() {
        basicSettingsPage.fillSiteDescription(this.randomIncorrectDescription);
        webElementActions.click(basicSettingsPage.getSaveBtn());

        String siteDescriptionColor = basicSettingsPage.getSiteDescription().getCssValue("color");
        Color color1 = Color.fromString(siteDescriptionColor);
        String descriptionColor = color1.asHex();
        Assert.assertEquals(descriptionColor, "#b94a48","Site description colors are not the same");
        try {
            String siteDescriptionErrorText = basicSettingsPage.getSiteDescriptionErrorText().getText();
            Assert.assertEquals(siteDescriptionErrorText, "'Site description' cannot exceed 255 characters");
        } catch (NoSuchElementException e) {
            System.err.println("The site description error text is not appeared.");
        }

        String errorTextColor = basicSettingsPage.getSiteDescriptionErrorText().getCssValue("color");
        Color color2 = Color.fromString(errorTextColor);
        String textColor = color2.asHex();
        Assert.assertEquals(textColor, "#b94a48","Error text colors are not the same");

        basicSettingsPage.getSiteDescriptionCell().clear();
    }

    @Test(description = "verify that it is possible to save a site name and description", priority = 3)
    public void siteNameAndDescriptionTest() {
        basicSettingsPage.fillSiteName(this.randomName).fillSiteDescription(this.randomDescription);
        webElementActions.click(basicSettingsPage.getSaveBtn());

        try {
            Assert.assertEquals(basicSettingsPage.getSuccessfullySavedPop_UpMessage().getText(), "Basic settings updated successfully");
        } catch (NoSuchElementException e) {
            System.err.println("The pop-up message is not appeared.");
        }

        String titleText = driver.getTitle();
        Assert.assertEquals(titleText, this.randomName + " | Account & Settings");
    }


    @Test(description = "verify that every language option is clickable", priority = 4)
    public void defaultLanguageOptionsTest() {
        basicSettingsPage.selectDropDownLanguageOption();
        List<String> expectedLanguagesList = new ArrayList<>(List.of("English", "Español (Spanish)", "Deutsch (German)",
                "Français (French)", "Ελληνικά (Greek)",
                "Italiano (Italian)", "Pусский (Russian)", "Suomi (Finnish)", "Српски (Serbian)", "Українська (Ukrainian)",
                "Català (Catalan)", "Latviešu (Latvian)", "Čeština (Czech)", "Slovenščina (Slovenian)",
                "Slovenský (Slovak)", "Türkçe (Turkish)", "Azərbaycan (Azerbaijani)", "हिंदी (Hindi)",
                "Português (Portuguese)", "Melayu (Malay - Malaysia)", "简体字 (Chinese Simplified)",
                "繁體字 (Chinese Traditional)", "한국어 (Korean)", "Nederlands (Dutch)", "日本語 (Japanese)",
                "Tiếng Việt (Vietnamese)", "Hrvatski (Croatian)", "ქართული (Georgian)",
                "босански (Bosnian - Bosnia and Herzegovina)", "עברית (Hebrew)",
                "العربية (Arabic - United Arab Emirates)", "Lietuvių kalba (Lithuanian)", "Eesti [beta] (Estonian)",
                "فارسی [beta] (Persian - Iran)", "Magyar nyelv [beta] (Hungarian)", "Svenska [beta] (Swedish)",
                "Norsk [beta] (Norwegian)", "հայերէն [beta] (Armenian)", "монгол хэл [beta] (Mongolian)",
                "Dansk [beta] (Danish)", "Polski [beta] (Polish)", "Bahasa Indonesia [beta] (Indonesian)",
                "ภาษาไทย [beta] (Thai)", "Português Brasileira [beta] (Portuguese Brazil)",
                "Română [beta] (Romanian)"));

        Assert.assertEquals(basicSettingsPage.getLanguagesList(), expectedLanguagesList, "LanguagesLists are different!");
    }

    @Test(description = "verify that a user can type and select the language and check its implementation", priority = 5)
    public void defaultLanguageTest() {
        try {
            basicSettingsPage.selectLanguage("Russian");
        } catch (NoSuchElementException e) {
            System.err.println("The language is not found");
        }

        try {
            Assert.assertEquals(basicSettingsPage.getSuccessfullySavedPop_UpMessage().getText(), "Basic settings updated successfully");
        } catch (NoSuchElementException e) {
            System.err.println("The pop-up message is not appeared.");
        }

        webElementActions.click(basicSettingsPage.getLogOutBtn());
        String expectedText = "ПАРОЛЬ";
        Assert.assertEquals(basicSettingsPage.getPasswordTextInRussian().getText(), expectedText, "Texts are different!");
    }
}
