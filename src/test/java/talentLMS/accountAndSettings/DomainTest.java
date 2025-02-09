package talentLMS.accountAndSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.enums.AdminSection;
import talentLMS.enums.accountAndSettings.AccountAndSettings;
import talentLMS.fileUtils.ConfigReader;

/**
 @author Dastan Aidarov
 */

public class DomainTest extends BaseTest {
    BasicSettingsTest basicSettingsTest = new BasicSettingsTest();

    /**
     * Метод выполняется перед каждым тестом, чтобы настроить тестовую среду.
     * 1. Открывает страницу настроек аккаунта.
     * 2. Переходит в раздел настроек домена.
    */
    @BeforeMethod
    public void beforeMethod() {
        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.ACCOUNT_SETTINGS);
        basicSettingsPage.selectSettingsSection(AccountAndSettings.SECTION_DOMAIN);
    }

    /**
     * Проверяет, что отображается ошибка при попытке изменить доменное имя на пустое.
     *
     * Шаги:
     * 1. Пытается изменить доменное имя на пустое значение.
     * 2. Проверяет, что цвет текста ошибки соответствует ожидаемому.
     * 3. Проверяет, что текст ошибки соответствует ожидаемому сообщению "Domain is required".
     */
    @Test(groups = "Regression", description = "verify that domain name is required if a user try to change domain name with empty name", priority = 1)
    public void emptyDomainNameTest() {
        domainPage.changeDomainName(AccountAndSettings.DOMAIN_NAME_EMPTY);
        basicSettingsTest.assertErrorTextColor(domainPage.getDomainName(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorTextColor(domainPage.getErrorText(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorTextColor(domainPage.getTalentLMSCom(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorText(domainPage.getErrorText().getText(), AccountAndSettings.DOMAIN_IS_REQUIRED_ERROR_TEXT);
    }

    /**
     * Проверяет, что при попытке изменить доменное имя на уже существующее отображается ошибка,
     * и доменное имя не изменяется.
     *
     * Шаги:
     * 1. Пытается изменить доменное имя на уже существующее.
     * 2. Проверяет, что цвет текста ошибки соответствует ожидаемому.
     * 3. Проверяет, что текст ошибки соответствует ожидаемому сообщению "Domain name already exists".
     */
    @Test(groups = "Regression", description = "verify that the error text is displayed and the domain name is not changed if a user try to change domain name with existing name", priority = 2)
    public void existingDomainNameTest() {
        domainPage.changeDomainName(AccountAndSettings.DOMAIN_NAME_EXISTING);
        basicSettingsTest.assertErrorTextColor(domainPage.getDomainName(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorTextColor(domainPage.getErrorText(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorTextColor(domainPage.getTalentLMSCom(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorText(domainPage.getErrorText().getText(),AccountAndSettings.DOMAIN_EXISTING_ERROR_TEXT);
    }

    /**
     * Проверяет, что при попытке изменить доменное имя на некорректное отображается ошибка,
     * и доменное имя не изменяется.
     *
     * Шаги:
     * 1. Пытается изменить доменное имя на некорректное.
     * 2. Проверяет, что цвет текста ошибки соответствует ожидаемому.
     * 3. Проверяет, что текст ошибки соответствует ожидаемому сообщению "Domain name is not valid".
     */
    @Test(groups = "Regression", description = "verify that the error text is displayed and the domain name is not changed if a user try to change domain name with invalid name", priority = 3)
    public void invalidDomainNameTest() {
        domainPage.changeDomainName(AccountAndSettings.DOMAIN_NAME_INVALID);
        basicSettingsTest.assertErrorTextColor(domainPage.getDomainName(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorTextColor(domainPage.getErrorText(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorTextColor(domainPage.getTalentLMSCom(), AccountAndSettings.SETTINGS_ERROR_TEXT_COLOR)
                        .assertErrorText(domainPage.getErrorText().getText(),AccountAndSettings.DOMAIN_IS_NOT_VALID_ERROR_TEXT);
    }

    /**
     * Проверяет, что доменное имя изменяется корректно и после изменения
     * осуществляется переход по новому адресу.
     *
     * Шаги:
     * 1. Изменяет доменное имя на новое.
     * 2. Проверяет, что текущий URL соответствует ожидаемому.
     * 3. Выполняет вход в систему и переключается на старый интерфейс.
     * 4. Возвращает доменное имя обратно к текущему значению.
     */
    @Test(groups = "E2E", description = "verify that domain name is required if a user click change domain name with blank domain name", priority = 4)
    public void changeDomainNameTest() {
        domainPage.changeDomainName(AccountAndSettings.DOMAIN_NAME_NEW);

        String actualURL = driver.getCurrentUrl();
        String expectedURL = AccountAndSettings.NEW_URL.getString();
        Assert.assertEquals(actualURL, expectedURL, "URLs are different");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).switchToLegacyInterface();
        beforeMethod();
        domainPage.changeDomainName(AccountAndSettings.DOMAIN_NAME_CURRENT);
        beforeClass();
    }

    /**
     * Проверяет, что кнопка "Cancel" перенаправляет пользователя обратно на страницу Dashboard.
     *
     * Шаги:
     * 1. Нажимает на кнопку "Cancel" в настройках.
     * 2. Получает текущий URL страницы после нажатия.
     * 3. Сравнивает фактический URL с ожидаемым URL Dashboard.
     */
    @Test(groups = "Smoke", description = "verify that button Cancel directs a user back to the page Dashboard", priority = 5)
    public void cancelTest() {
        webElementActions.click(basicSettingsPage.getCancelBtn());

        String actualURL = driver.getCurrentUrl();
        String expectedURL = AccountAndSettings.DASHBOARD_URL.getString();
        Assert.assertEquals(actualURL, expectedURL, "URLs after cancellation are different.");
    }
}
