package talentLMS.accountAndSettings;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.enums.AdminSection;
import talentLMS.enums.Role;
import talentLMS.enums.accountAndSettings.AccountAndSettings;
import talentLMS.fileUtils.ConfigReader;

import java.util.List;

/**
 * @author Dastan Aidarov
 */

public class BasicSettingsTest extends BaseTest {

    /**
     * Этот метод выполняется один раз перед всеми тестами в классе.
     * Он открывает страницу настроек аккаунта и выбирает раздел базовых настроек.
     */
    @BeforeMethod
    public void beforeMethod() {
        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.ACCOUNT_SETTINGS);
        basicSettingsPage.selectSettingsSection(AccountAndSettings.SECTION_BASIC_SETTINGS);
    }

    /**
     * Проверяет, что появилось всплывающее сообщение об успешном сохранении.
     *
     * @param expectedMessage ожидаемое сообщение.
     */
    public BasicSettingsTest assertSuccessPopUpMessage(AccountAndSettings expectedMessage) {
        try {
            String actualSuccessMessage = basicSettingsPage.getSuccessfullySavedPop_UpMessage().getText();
            String expectedSuccessMessage = expectedMessage.getString();
            Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Success pop-up messages are not the same.");
        } catch (NoSuchElementException e) {
            System.err.println("The pop-up message is not appeared.");
        }
        return new BasicSettingsTest();
    }

    /**
     * Проверяет цвет текста ошибки.
     *
     * @param element       веб-элемент с текстом ошибки.
     * @param expectedColor ожидаемый цвет текста ошибки в формате HEX.
     */
    public BasicSettingsTest assertErrorTextColor(WebElement element, AccountAndSettings expectedColor) {
        String actualNameColor = Color.fromString(element.getCssValue("color")).asHex();
        String expectedNameColor = expectedColor.getString();
        Assert.assertEquals(actualNameColor, expectedNameColor, "Colors are not the same");
        return new BasicSettingsTest();
    }

    /**
     * Проверяет текст ошибки.
     *
     * @param actualText   фактический текст ошибки.
     * @param expectedText ожидаемый текст ошибки.
     */
    public BasicSettingsTest assertErrorText(String actualText, AccountAndSettings expectedText) {
        try {
            String actualErrorText = actualText;
            String expectedErrorText = expectedText.getString();
            Assert.assertEquals(actualErrorText, expectedErrorText, "The error texts are not the same");
        } catch (NoSuchElementException e) {
            System.err.println("The error text was not appeared.");
        }
        return new BasicSettingsTest();
    }

    /**
     * Проверяет, что сообщение "No matches found" корректно отображается.
     *
     * @param actualText   фактический текст сообщения.
     * @param expectedText ожидаемый текст сообщения.
     */
    public BasicSettingsTest assertNoMatchesFound(String actualText, AccountAndSettings expectedText) {
        String actualNoMatchesText = actualText;
        String expectedNoMatchesText = expectedText.getString();
        Assert.assertEquals(actualNoMatchesText, expectedNoMatchesText, "The matches texts are different");
        return new BasicSettingsTest();
    }

    public BasicSettingsTest assertAnnouncementText(String expectedText) {
        String actualAnnouncementText = basicSettingsPage.getAnnouncementText().getText();
        Assert.assertEquals(actualAnnouncementText, expectedText, "Announcement texts are different.");

        return new BasicSettingsTest();
    }

    /**
     * Тест проверяет, что невозможно сохранить название сайта длиннее 40 символов.
     */
    @Test(groups = "Regression", description = "verify that it is impossible to save a site name longer than 40 characters", priority = 1)
    public void siteNameOverTheLimitTest() {
        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteNameCell(), basicSettingsPage.getRandomIncorrectName());

        assertErrorTextColor(basicSettingsPage.getSiteName(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)

                .assertErrorText(basicSettingsPage.getSiteNameErrorText().getText(), AccountAndSettings.SITE_NAME_ERROR_TEXT)

                .assertErrorTextColor(basicSettingsPage.getSiteNameErrorText(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR);

        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteNameCell(), AccountAndSettings.SITE_NAME.getString());
    }

    /**
     * Тест проверяет, что невозможно сохранить описание сайта длиннее 255 символов.
     */
    @Test(groups = "Regression", description = "verify that it is impossible to save a site description longer than 255 characters", priority = 2)
    public void siteDescriptionOverTheLimitTest() {
        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteDescriptionCell(), basicSettingsPage.getRandomIncorrectDescription());

        assertErrorTextColor(basicSettingsPage.getSiteDescription(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)

                .assertErrorText(basicSettingsPage.getSiteDescriptionErrorText().getText(), AccountAndSettings.SITE_DESCRIPTION_ERROR_TEXT)

                .assertErrorTextColor(basicSettingsPage.getSiteDescriptionErrorText(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR);

        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteDescriptionCell(), "");
    }

    @Test(groups = "Regression", description = "verify that it is impossible to save a site description longer than 255 characters", priority = 3)
    public void emptySiteNameAndDescriptionTest() {
        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteNameCell(), "")
                .fillSiteOrDescriptionsName(basicSettingsPage.getSiteDescriptionCell(), "");

        String titleText = driver.getTitle();
        Assert.assertEquals(titleText, "TalentLMS | Account & Settings", "Site title is incorrect.");

        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteNameCell(), AccountAndSettings.SITE_NAME.getString());
    }

    /**
     * Тест проверяет, что можно сохранить название и описание сайта.
     */
    @Test(groups = "Smoke", description = "verify that it is possible to save a site name and description", priority = 4)
    public void siteNameAndDescriptionTest() {
        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteNameCell(), basicSettingsPage.getRandomName())
                .fillSiteOrDescriptionsName(basicSettingsPage.getSiteDescriptionCell(), basicSettingsPage.getRandomDescription());

        assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY);

        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteNameCell(), AccountAndSettings.SITE_NAME.getString());
        basicSettingsPage.fillSiteOrDescriptionsName(basicSettingsPage.getSiteDescriptionCell(), "");
        String titleText = driver.getTitle();
        Assert.assertEquals(titleText, AccountAndSettings.SITE_NAME.getString() + " | Account & Settings", "Site title is incorrect.");
    }

    /**
     * Тест проверяет, что каждая опция языка доступна для выбора.
     */
    @Test(groups = "Smoke", description = "verify that every language option is clickable", priority = 5)
    public void LanguageOptionsTest() {
        basicSettingsPage.selectDropDownLanguageOption();
        List<String> expectedLanguagesList = AccountAndSettings.LANGUAGES.getList();

        Assert.assertEquals(basicSettingsPage.getLanguagesList(), expectedLanguagesList, "LanguagesLists are different!");
    }

    /**
     * Тест проверяет возможность смены языка на странице.
     */
    @Test(groups = "Regression", description = "verify that a user can type then select the language and check its implementation", priority = 6)
    public void changeLanguageTest() {
        basicSettingsPage.selectLanguage("Russian");

        assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY);

        webElementActions.click(basicSettingsPage.getLogOutBtn());
        String actualText = basicSettingsPage.getPasswordTextInRussian().getText();
        String expectedText = "ПАРОЛЬ";
        Assert.assertEquals(actualText, expectedText, "Texts are different!");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface().selectSection(AdminSection.ACCOUNT_SETTINGS);
        basicSettingsPage.selectLanguage("English");
    }

    /**
     * Тест проверяет невозможность смены языка на Кыргызский язык на странице.
     */
    @Test(groups = "Regression", description = "verify that unavailable language typed is not found and not selected", priority = 7)
    public void unavailableLanguageTest() {
        try {
            basicSettingsPage.selectLanguage("Kyrgyz");
        } catch (ElementClickInterceptedException e) {
            assertNoMatchesFound(basicSettingsPage.getNoMatchesFoundText().getText(), AccountAndSettings.NO_MATCHES_FOUND);
        }
    }

    /**
     * Тест проверяет, что каждая опция часового пояса доступна для выбора.
     */
    @Test(groups = "Smoke", description = "verify that every time zone option is clickable", priority = 8)
    public void timeZoneOptionsTest() {
        basicSettingsPage.selectDropDownTimeZoneOption();
        List<String> expectedTimeZonesList = AccountAndSettings.TIME_ZONES.getList();

        Assert.assertEquals(basicSettingsPage.getTimeZonesList(), expectedTimeZonesList, "TimeZonesLists are different!");
    }

    /**
     * Тест проверяет возможность смены часового пояса на странице.
     */
    @Test(groups = "E2E", description = "verify that a user can type then select the time zone and check its implementation", priority = 9)
    public void changeTimeZoneTest() {
        try {
            basicSettingsPage.selectTimeZone("Rome");
        } catch (NoSuchElementException e) {
            System.err.println("The time zone is not found");
        }

        assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY);

        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.USERS).webElementActions.click(userPage.addUser);
        String defaultTimeZone = basicSettingsPage.getUserTimeZone().getText();
        Assert.assertEquals(defaultTimeZone, "(GMT +01:00) Rome, Italy", "Time zones are different");
    }

    /**
     * Тест проверяет невозможность смены часового пояса на Бишкек на странице.
     */
    @Test(groups = "Regression", description = "verify that unavailable time zone typed is not found and not selected", priority = 10)
    public void unavailableTimeZoneTest() {
        try {
            basicSettingsPage.selectTimeZone("Bishkek");
        } catch (ElementClickInterceptedException e) {
            assertNoMatchesFound(basicSettingsPage.getNoMatchesFoundText().getText(), AccountAndSettings.NO_MATCHES_FOUND);
        }
    }

    /**
     * Проверяет, что пользователь может выбрать каждый доступный формат даты и убедиться в его корректности.
     * <p>
     * Шаги:
     * 1. Открывает выпадающий список с форматами даты.
     * 2. Перебирает все доступные варианты форматов даты.
     * 3. Выбирает каждый формат, сохраняет изменения и проверяет, что появился успешный pop-up.
     * 4. Переходит на страницу Dashboard и проверяет, что выбранный формат даты корректно отображается в таймлайне.
     * 5. Возвращается в настройки аккаунта и переходит к следующему формату.
     * 6. В конце теста сравнивает список полученных форматов с ожидаемыми.
     * <p>
     * Если список форматов даты пуст, тест завершится с исключением RuntimeException.
     */
    @Test(groups = "Regression", description = "verify that a user can select every date format and check their implementation", priority = 11)
    public void dateFormatOptionsTest() {
        basicSettingsPage.selectDropDownDateFormatOption();

        if (!basicSettingsPage.getDateFormatOptions().isEmpty()) {
            for (int i = 0; i < basicSettingsPage.getDateFormatOptions().size(); i++) {
                webElementActions.click(basicSettingsPage.getDateFormatOptions().get(i))
                        .click(basicSettingsPage.getSaveBtn());

                assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY);

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
        List<String> expectedDateFormatsList = AccountAndSettings.DATE_FORMATS.getList();
        Assert.assertEquals(actualDateFormatsList, expectedDateFormatsList, "Date formats are different");
    }

    /**
     * Проверяет, что пользователь может выбрать каждый доступный формат времени и убедиться в его корректности.
     * <p>
     * Шаги:
     * 1. Открывает выпадающий список с форматами времени.
     * 2. Собирает все доступные варианты форматов времени и сохраняет их в список.
     * 3. Перебирает каждый формат:
     * - Выбирает формат и сохраняет изменения.
     * - Проверяет, что появилось уведомление об успешном сохранении.
     * - Переходит в раздел отчетов и проверяет, что формат времени применился корректно.
     * - Возвращается в настройки аккаунта для выбора следующего формата.
     * 4. В конце теста сравнивает список полученных форматов с ожидаемыми.
     * <p>
     * Исключения:
     * - Если список форматов времени пуст, выбрасывается RuntimeException.
     * - Если элемент не найден, выводится ошибка в консоль.
     */
    @Test(groups = "Regression", description = "verify that a user can select every time format and check their implementation", priority = 12)
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

                    assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY);

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
        List<String> expectedTimeFormatsList = AccountAndSettings.TIME_FORMATS.getList();
        Assert.assertEquals(actualTimeFormatsList, expectedTimeFormatsList, "Time formats are different");
    }

    /**
     * Тест проверяет, что каждая опция валюты доступна для выбора.
     */
    @Test(groups = "Smoke", description = "verify that every currency option is clickable", priority = 13)
    public void currencyOptionsTest() {
        basicSettingsPage.selectDropDownCurrencyOption();

        List<String> actualCurrenciesList = basicSettingsPage.getCurrenciesList();
        List<String> expectedCurrenciesList = AccountAndSettings.CURRENCIES.getList();

        Assert.assertEquals(actualCurrenciesList, expectedCurrenciesList, "CurrenciesLists are different!");
    }

    /**
     * Тест проверяет возможность смены часового пояса на странице.
     */
    @Test(groups = "Regression", description = "verify that a user can select currency Euro and check its implementation", priority = 14)
    public void currencyTest() {
        try {
            basicSettingsPage.selectCurrency("Euro");
        } catch (NoSuchElementException e) {
            System.err.println("The currency is not found");
        }

        assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY);

        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.COURSES).webElementActions.click(coursesPage.getAddCourse()).click(basicSettingsPage.getPriceBtnInCourses());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String actualPriceCurrency = basicSettingsPage.getPriceCurrencyInCourses().getText();
        Assert.assertEquals(actualPriceCurrency, "€", "Course currencies are different");
    }

    /**
     * Тест проверяет невозможность смены валюты на сом на странице.
     */
    @Test(groups = "Regression", description = "verify that unavailable currency typed is not found and not selected", priority = 15)
    public void unavailableCurrencyTest() {
        try {
            basicSettingsPage.selectCurrency("Som");
        } catch (ElementClickInterceptedException e) {
            assertNoMatchesFound(basicSettingsPage.getNoMatchesFoundText().getText(), AccountAndSettings.NO_MATCHES_FOUND);
        }
    }

    /**
     * Проверяет, что внутреннее объявление отображается на панели управления
     * для различных ролей пользователей (администратор, инструктор и ученик).
     * <p>
     * Шаги теста:
     * 1. Создание объявления.
     * 2. Проверка отображения объявления для администратора.
     * 3. Переключение на роль "инструктор" и проверка отображения объявления.
     * 3. Переключение на роль "ученик" и проверка отображения объявления.
     * 4. Возвращение к роли администратора.
     *
     * @throws InterruptedException если поток был прерван во время ожидания обновления UI
     */
    @Test(groups = "E2E", description = "verify that an internal announcement is displayed on the dashboard when a learner or an instructor logs in", priority = 16)
    public void internalAnnouncementTest() throws InterruptedException {
        String announcementText = "Hello, world!";
        basicSettingsPage.makeAnAnnouncement(announcementText);

        assertSuccessPopUpMessage(AccountAndSettings.BASIC_SETTINGS_SAVED_SUCCESSFULLY)

                .assertAnnouncementText(announcementText);

        Thread.sleep(10000);
        component.selectRole(Role.INSTRUCTOR);
        assertAnnouncementText(announcementText);

        component.selectRole(Role.LEARNER);
        assertAnnouncementText(announcementText);

        Thread.sleep(5000);
        component.selectRole(Role.ADMINISTRATOR);
    }

    /**
     * Проверяет, что кнопка "Cancel" перенаправляет пользователя обратно на страницу Dashboard.
     * <p>
     * Шаги:
     * 1. Нажимает на кнопку "Cancel" в настройках.
     * 2. Получает текущий URL страницы после нажатия.
     * 3. Сравнивает фактический URL с ожидаемым URL Dashboard.
     */
    @Test(groups = "Smoke", description = "verify that button Cancel directs a user back to the page Dashboard", priority = 17)
    public void cancelTest() {
        webElementActions.click(basicSettingsPage.getCancelBtn());

        String actualURL = driver.getCurrentUrl();
        String expectedURL = AccountAndSettings.DASHBOARD_URL.getString();
        Assert.assertEquals(actualURL, expectedURL, "URLs after cancellation are different.");
    }
}
