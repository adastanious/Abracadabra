package talentLMS.accountAndSettings;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.enums.AdminSection;
import talentLMS.enums.BasicSettings;
import talentLMS.fileUtils.ConfigReader;

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
        driver.get(ConfigReader.getProperty("dashboardURL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(AdminSection.ACCOUNT_SETTINGS);
    }

    @BeforeTest
    public void beforeTest() {
        driver.get(ConfigReader.getProperty("basicSettingsURL"));
    }

    public void assertSuccessPopUpMessage() {
        try {
            String actualSuccessMessage = basicSettingsPage.getSuccessfullySavedPop_UpMessage().getText();
            String expectedSuccessMessage = BasicSettings.SETTINGS_SAVED_SUCCESSFULLY.getString();
            Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Success pop-up messages are not the same.");
        } catch (NoSuchElementException e) {
            System.err.println("The pop-up message is not appeared.");
        }
    }

    public void assertErrorTextColor(WebElement element) {
        String actualNameColor = Color.fromString(element.getCssValue("color")).asHex();
        String expectedNameColor = BasicSettings.ERROR_TEXT_COLOR.getString();
        Assert.assertEquals(actualNameColor, expectedNameColor,"Colors are not the same");
    }

    public void assertErrorText(String actualText, String expectedText) {
        try {
            String actualErrorText = actualText;
            String expectedErrorText = expectedText;
            Assert.assertEquals(actualErrorText,expectedErrorText, "The error texts are not the same");
        } catch (NoSuchElementException e) {
            System.err.println("The error text was not appeared.");
        }
    }

    public void assertNoMatchesFound(String actualText, String expectedText) {
        String actualNoMatchesText = actualText;
        String expectedNoMatchesText = expectedText;
        Assert.assertEquals(actualNoMatchesText,expectedNoMatchesText, "The matches texts are different");
    }

    @Test(description = "verify that it is impossible to save a site name longer than 40 characters", priority = 1)
    public void siteNameOverTheLimitTest() {
        basicSettingsPage.fillSiteName(this.randomIncorrectName);
        webElementActions.click(basicSettingsPage.getSaveBtn());

        assertErrorTextColor(basicSettingsPage.getSiteName());

        assertErrorText(basicSettingsPage.getSiteNameErrorText().getText(), BasicSettings.SITE_NAME_ERROR_TEXT.getString());

        assertErrorTextColor(basicSettingsPage.getSiteNameErrorText());

        basicSettingsPage.getSiteNameCell().clear();
    }

    @Test(description = "verify that it is impossible to save a site description longer than 255 characters", priority = 2)
    public void siteDescriptionOverTheLimitTest() {
        basicSettingsPage.fillSiteDescription(this.randomIncorrectDescription);
        webElementActions.click(basicSettingsPage.getSaveBtn());

        assertErrorTextColor(basicSettingsPage.getSiteDescription());

        assertErrorText(basicSettingsPage.getSiteDescriptionErrorText().getText(), BasicSettings.SITE_DESCRIPTION_ERROR_TEXT.getString());

        assertErrorTextColor(basicSettingsPage.getSiteDescriptionErrorText());
    }

    @Test(description = "verify that it is possible to save a site name and description", priority = 3)
    public void siteNameAndDescriptionTest() {
        basicSettingsPage.fillSiteName(this.randomName).fillSiteDescription(this.randomDescription);
        webElementActions.click(basicSettingsPage.getSaveBtn());

        assertSuccessPopUpMessage();

        String titleText = driver.getTitle();
        Assert.assertEquals(titleText, this.randomName + " | Account & Settings");
    }


    @Test(description = "verify that every language option is clickable", priority = 4)
    public void LanguageOptionsTest() {
        basicSettingsPage.selectDropDownLanguageOption();
        List<String> expectedLanguagesList = BasicSettings.LANGUAGES.getList();

        Assert.assertEquals(basicSettingsPage.getLanguagesList(), expectedLanguagesList, "LanguagesLists are different!");
    }

    @Test(description = "verify that a user can type then select the language and check its implementation", priority = 5)
    public void changeLanguageTest() {
        basicSettingsPage.selectLanguage("Russian");

        assertSuccessPopUpMessage();

        webElementActions.click(basicSettingsPage.getLogOutBtn());
        String actualText = basicSettingsPage.getPasswordTextInRussian().getText();
        String expectedText = "ПАРОЛЬ";
        Assert.assertEquals(actualText, expectedText, "Texts are different!");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(AdminSection.ACCOUNT_SETTINGS);
        basicSettingsPage.selectLanguage("English");
    }

    @Test(description = "verify that unavailable language typed is not found and not selected", priority = 6)
    public void unavailableLanguageTest() {
        try {
            basicSettingsPage.selectLanguage("Kyrgyz");
        } catch (ElementClickInterceptedException e) {
            assertNoMatchesFound(basicSettingsPage.getNoMatchesFoundText().getText(), BasicSettings.NO_MATCHES_FOUND.getString());
        }
    }

    @Test(description = "verify that every time zone option is clickable", priority = 7)
    public void timeZoneOptionsTest() {
        basicSettingsPage.selectDropDownTimeZoneOption();
        List<String> expectedTimeZonesList = BasicSettings.TIME_ZONES.getList();

        Assert.assertEquals(basicSettingsPage.getTimeZonesList(), expectedTimeZonesList, "TimeZonesLists are different!");
    }

    @Test(description = "verify that a user can type then select the time zone and check its implementation", priority = 8)
    public void changeTimeZoneTest() {
        try {
            basicSettingsPage.selectTimeZone("Rome");
        } catch (NoSuchElementException e) {
            System.err.println("The time zone is not found");
        }

        assertSuccessPopUpMessage();

        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.USERS).webElementActions.click(userPage.addUser);
        String defaultTimeZone = basicSettingsPage.getUserTimeZone().getText();
        Assert.assertEquals(defaultTimeZone,"(GMT +01:00) Rome, Italy", "Time zones are different");
    }

    @Test(description = "verify that unavailable time zone typed is not found and not selected", priority = 9)
    public void unavailableTimeZoneTest() {
        try {
            basicSettingsPage.selectTimeZone("Bishkek");
        } catch (ElementClickInterceptedException e) {
            assertNoMatchesFound(basicSettingsPage.getNoMatchesFoundText().getText(), BasicSettings.NO_MATCHES_FOUND.getString());
        }
    }

    @Test(description = "verify that a user can select every date format and check their implementation", priority = 10)
    public void dateFormatOptionsTest() {
        basicSettingsPage.selectDropDownDateFormatOption();

            if (!basicSettingsPage.getDateFormatOptions().isEmpty()) {
                for (int i = 0; i < basicSettingsPage.getDateFormatOptions().size(); i++) {
                    webElementActions.click(basicSettingsPage.getDateFormatOptions().get(i))
                                    .click(basicSettingsPage.getSaveBtn());

                    assertSuccessPopUpMessage();

                    driver.get(ConfigReader.getProperty("dashboardURL"));
                    webElementActions.click(basicSettingsPage.getTimeLineBtn())
                            .click(basicSettingsPage.getExtendedTimeLineBtn());

                    String actualDateFormat = basicSettingsPage.getTimelineFromCell().getDomAttribute("placeholder");
                    String expectedDateFormat = basicSettingsPage.getDateFormatsList().get(i);
                    Assert.assertEquals(actualDateFormat, expectedDateFormat, "Date formats are different");
                    driver.get(ConfigReader.getProperty("dashboardURL"));
                    dashboardPage.selectSection(AdminSection.ACCOUNT_SETTINGS);
                    webElementActions.click(basicSettingsPage.getDateFormatZoneCell());
                }
            } else {
                throw new RuntimeException("No date format is found in dropdown list!");
            }

        List<String> actualDateFormatsList = basicSettingsPage.getDateFormatsList();
        List<String> expectedDateFormatsList = BasicSettings.DATE_FORMATS.getList();
        Assert.assertEquals(actualDateFormatsList, expectedDateFormatsList, "Date formats are different");
    }

    @Test(description = "verify that a user can select every time format and check their implementation", priority = 11)
    public void timeFormatOptionsTest() {
        try {
            webElementActions.click(basicSettingsPage.getTimeFormatCell());

            for (WebElement timeFormat : basicSettingsPage.getTimeFormatOptions()) {
                basicSettingsPage.getTimeFormatsList().add(timeFormat.getText());
            }

            if (!basicSettingsPage.getTimeFormatOptions().isEmpty()) {
                for (int i = 0; i < basicSettingsPage.getTimeFormatOptions().size(); i++) {
                    webElementActions.click(basicSettingsPage.getTimeFormatOptions().get(i))
                            .click(basicSettingsPage.getSaveBtn());

                    assertSuccessPopUpMessage();

                    driver.get(ConfigReader.getProperty("dashboardURL"));
                    dashboardPage.selectSection(AdminSection.REPORTS);
                    String actualTimeFormat = basicSettingsPage.getTimeFormatInReports().getText();
                    String expectedTimeFormat = "";
                    switch (basicSettingsPage.getTimeFormatsList().get(i)) {
                        case "24-hour":
                            expectedTimeFormat = "0:00";
                            break;
                        case "12-hour":
                            expectedTimeFormat = "0:00 AM";
                        default:
                            System.err.println("Incorrect time format!");
                    }
                    Assert.assertEquals(actualTimeFormat, expectedTimeFormat, "Time formats are different");
                    driver.get(ConfigReader.getProperty("dashboardURL"));
                    dashboardPage.selectSection(AdminSection.ACCOUNT_SETTINGS);
                    webElementActions.click(basicSettingsPage.getTimeFormatCell());
                }
            } else {
                throw new RuntimeException("No time format is found in dropdown list!");
            }
        } catch (NoSuchElementException e) {
            System.err.println("The date format is not found");
        }

        List<String> actualTimeFormatsList = basicSettingsPage.getTimeFormatsList();
        List<String> expectedTimeFormatsList = BasicSettings.TIME_FORMATS.getList();
        Assert.assertEquals(actualTimeFormatsList, expectedTimeFormatsList, "Time formats are different");
    }

    @Test(description = "verify that every currency option is clickable", priority = 12)
    public void currencyOptionsTest() {
        basicSettingsPage.selectDropDownCurrencyOption();

        List<String> actualCurrenciesList = basicSettingsPage.getCurrenciesList();
        List<String> expectedCurrenciesList = BasicSettings.CURRENCIES.getList();

        Assert.assertEquals(actualCurrenciesList, expectedCurrenciesList, "CurrenciesLists are different!");
    }

    @Test(description = "verify that a user can select currency Euro and check its implementation", priority = 13)
    public void currencyTest() {
        try {
            basicSettingsPage.selectCurrency("Euro");
        } catch (NoSuchElementException e) {
            System.err.println("The currency is not found");
        }

        assertSuccessPopUpMessage();

        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.COURSES).webElementActions.click(coursesPage.getAddCourse()).click(basicSettingsPage.getPriceBtnInCourses());
        String actualPriceCurrency = basicSettingsPage.getPriceCurrencyInCourses().getText();
        Assert.assertEquals(actualPriceCurrency,"€", "Course currencies are different");
    }

    @Test(description = "verify that unavailable currency typed is not found and not selected", priority = 14)
    public void unavailableCurrencyTest() {
        try {
            basicSettingsPage.selectCurrency("Som");
        } catch (ElementClickInterceptedException e) {
            assertNoMatchesFound(basicSettingsPage.getNoMatchesFoundText().getText(), BasicSettings.NO_MATCHES_FOUND.getString());
        }
    }

//    @Test(description = "verify that an internal announcement is displayed on the dashboard when a user logs in", priority = 14)
//    public void internalAnnouncementTest() {
//        driver.get(ConfigReader.getProperty("dashboardURL"));
//        dashboardPage.selectSection(AdminSection.USERS);
//        userPage.addNewUser(randomUser);
//    }





}
