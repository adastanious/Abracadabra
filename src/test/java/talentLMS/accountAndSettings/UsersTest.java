package talentLMS.accountAndSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.enums.AdminSection;
import talentLMS.enums.accountAndSettings.AccountAndSettings;
import talentLMS.fileUtils.ConfigReader;

import java.util.List;

/**
 @author Dastan Aidarov
 */

public class UsersTest extends BaseTest {

    BasicSettingsTest basicSettingsTest = new BasicSettingsTest();

    /**
     * Метод выполняется перед каждым тестом для подготовки тестовой среды.
     * 1. Открывает страницу настроек аккаунта.
     * 2. Переходит в раздел настроек пользователей.
     */
    @BeforeMethod
    public void beforeMethod() {
        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.ACCOUNT_SETTINGS);
        basicSettingsPage.selectSettingsSection(AccountAndSettings.SECTION_USERS);
    }

    /**
     * Проверяет, что список доступных типов пользователей по умолчанию соответствует ожидаемому.
     *
     * Шаги:
     * 1. Получает фактический список типов пользователей по умолчанию.
     * 2. Получает ожидаемый список типов пользователей из настроек.
     * 3. Сравнивает оба списка.
     * 4. Очищает кэш списка типов пользователей.
     */
//   @Test(groups = "Regression", description = "verify that the default user type options are correct", priority = 1)
//    public void defaultUserTypesTest() {
//        List<String> actualDefaultUserTypes = settingsUsersPage.getDefaultUserTypes();
//        List<String> expectedDefaultUserTypes = AccountAndSettings.USERS_DEFAULT_USER_TYPE.getList();
//        Assert.assertEquals(actualDefaultUserTypes, expectedDefaultUserTypes, "Default user types are different.");
//
//        settingsUsersPage.getUserTypesList().clear();
//    }
 //

    /**
     * Проверяет, что выбранный тип пользователя по умолчанию отображается в форме добавления пользователя.
     *
     * Шаги:
     * 1. Получает список типов пользователей по умолчанию.
     * 2. Поочередно выбирает каждый тип пользователя и сохраняет настройки.
     * 3. Проверяет появление всплывающего сообщения об успешном сохранении.
     * 4. Переходит на страницу добавления пользователя и проверяет, что выбранный тип совпадает с ожидаемым.
     * 5. Возвращается на страницу настроек перед следующей итерацией.
     * 6. Очищает кэш списка типов пользователей.
     */
//    @Test(groups = { "regression" }, description = "verify that the selected default user type is specified in the Add user form", priority = 2)
//    public void selectDefaultUserTypeTest() {
//        List<String> defaultUserTypes = settingsUsersPage.getDefaultUserTypes();
//
//        for (int i = 0; i < defaultUserTypes.size(); i++) {
//            settingsUsersPage.selectDefaultUserType(i);
//
//            basicSettingsTest.assertSuccessPopUpMessage(AccountAndSettings.USERS_SETTINGS_SAVED_SUCCESSFULLY);
//
//            driver.get(ConfigReader.getProperty("dashboardURL"));
//            dashboardPage.selectSection(AdminSection.USERS).webElementActions.click(userPage.addUser);
//            String actualUserType = settingsUsersPage.getUserTypeInAddUser().getText();
//            String expectedUserType = settingsUsersPage.getUserTypesList().get(i);
//            Assert.assertEquals(actualUserType, expectedUserType, "User types are different");
//
//            driver.get(ConfigReader.getProperty("dashboardURL"));
//            beforeMethod();
//        }
//        settingsUsersPage.getUserTypesList().clear();
//    }

    /**
     * Проверяет, что список отображаемых форматов пользователей соответствует ожидаемому.
     *
     * Шаги:
     * 1. Получает фактический список форматов отображения пользователей.
     * 2. Получает ожидаемый список форматов отображения пользователей.
     * 3. Сравнивает оба списка.
     */
    @Test(groups = "Regression", description = "verify that the visible user format options are correct", priority = 3)
    public void visibleUserFormatTest() {
        List<String> actualDefaultUserTypes = settingsUsersPage.getVisibleUserFormats();
        List<String> expectedDefaultUserTypes = AccountAndSettings.USERS_VISIBLE_USER_FORMATS.getList();
        Assert.assertEquals(actualDefaultUserTypes, expectedDefaultUserTypes, "Default user types are different.");
    }

    /**
     * Проверяет, что выбранный формат отображения имени пользователя отображается корректно.
     *
     * Шаги:
     * 1. Получает список доступных форматов отображения имени пользователя.
     * 2. Поочередно выбирает каждый формат.
     * 3. Проверяет появление всплывающего сообщения об успешном сохранении.
     * 4. Сравнивает фактический отображаемый формат имени пользователя с ожидаемым.
     */
    @Test(groups = "Regression", description = "verify that the selected user name format is displayed", priority = 4)
    public void selectVisibleUserFormatTest() {
        List<String> visibleUserFormats = settingsUsersPage.getVisibleUserFormats();

        for (int i = 0; i < visibleUserFormats.size(); i++) {
            settingsUsersPage.selectVisibleUserFormat(i);

            basicSettingsTest.assertSuccessPopUpMessage(AccountAndSettings.USERS_SETTINGS_SAVED_SUCCESSFULLY);

            String actualVisibleUserName = settingsUsersPage.getActualUserFormat().getText();
            String expectedVisibleUserName = AccountAndSettings.USERS_VISIBLE_USER_NAMES.getList().get(i);
            Assert.assertEquals(actualVisibleUserName, expectedVisibleUserName, "User names are different");
        }
    }

    /**
     * Проверяет, что кнопка "Cancel" перенаправляет пользователя обратно на страницу Dashboard.
     *
     * Шаги:
     * 1. Нажимает на кнопку "Cancel" в настройках пользователя.
     * 2. Получает текущий URL страницы после нажатия.
     * 3. Сравнивает фактический URL с ожидаемым URL Dashboard.
     */
    @Test(groups = "Regression", description = "verify that button Cancel directs a user back to the page Dashboard", priority = 5)
    public void cancelTest() {
        webElementActions.click(basicSettingsPage.getCancelBtn());

        String actualURL = driver.getCurrentUrl();
        String expectedURL = AccountAndSettings.DASHBOARD_URL.getString();
        Assert.assertEquals(actualURL, expectedURL, "URLs after cancellation are different.");
    }
}
