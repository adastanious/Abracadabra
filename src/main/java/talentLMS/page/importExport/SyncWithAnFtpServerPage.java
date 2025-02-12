package talentLMS.page.importExport;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

///   @author Agema

public class SyncWithAnFtpServerPage extends BasePage {

    @FindBy(xpath = "//div/a[normalize-space() ='Import - Export']")
    public WebElement importExport;

    @FindBy(xpath = "//a[normalize-space()='Sync with an FTP server']")
    public WebElement syncWithAnFTPServerTab;

    @FindBy(xpath = "//input[@name='ftp_host']")
    public WebElement hostInput;

    @FindBy(xpath = "//input[@name='ftp_port']")
    public WebElement portInput;

    @FindBy(xpath = "//input[@name='ftp_username']")
    public WebElement userNameInput;

    @FindBy(xpath = "//input[@name='ftp_password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//span[@id='tl-test-connection']")
    public WebElement testConnectionButton;

    @FindBy(xpath = "//input[@name='ftp_export_enabled']")
    public WebElement enableExportCheckbox;

    @FindBy(xpath = "//input[@value='Save']")
    public WebElement saveButton;

    @Step("Открывает вкладку Import - Export")
    public SyncWithAnFtpServerPage clickImportExport() {
        webElementActions.click(importExport);
        return this;
    }

    @Step("Переходит на вкладку Sync with an FTP server")
    public SyncWithAnFtpServerPage clickSyncWithAnFTPServerTab() {
        webElementActions.click(syncWithAnFTPServerTab);
        return this;
    }

    @Step("Вводит значение в поле Host")
    public SyncWithAnFtpServerPage enterHost(String host) {
        webElementActions.typeText(hostInput, host);
        return this;
    }

    @Step("Вводит значение в поле Port")
    public SyncWithAnFtpServerPage enterPort(String port) {
        webElementActions.typeText(portInput, port);
        return this;
    }

    @Step("Вводит значение в поле Username")
    public SyncWithAnFtpServerPage enterUsername(String userName) {
        webElementActions.typeText(userNameInput, userName);
        return this;
    }

    @Step("Вводит значение в поле Password")
    public SyncWithAnFtpServerPage enterPassword(String password) {
        webElementActions.typeText(passwordInput, password);
        return this;
    }

    @Step("Нажимает кнопку Test Connection")
    public SyncWithAnFtpServerPage clickTestConnection() {
        webElementActions.click(testConnectionButton);
        return this;
    }

    @Step("Включает или отключает экспорт данных")
    public SyncWithAnFtpServerPage enableExport(boolean enable) {
        if (enableExportCheckbox.isSelected() != enable) {
            webElementActions.click(enableExportCheckbox);
        }
        return this;
    }

    @Step("Нажимает кнопку Save")
    public SyncWithAnFtpServerPage clickSave() {
        webElementActions.click(saveButton);
        return this;
    }
}
