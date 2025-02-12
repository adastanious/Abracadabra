package talentLMS.page.importExport;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

///   @author Agema

public class ExportPage extends BasePage {

    @FindBy(xpath = "//div/a[normalize-space() ='Import - Export']")
    public WebElement importExport;

    @FindBy(xpath = "//a[normalize-space()='Export']")
    public WebElement exportTab;

    @FindBy(xpath = "//div[@class='select2-container tl-select2']//span[@class='select2-arrow']/b")
    public WebElement exportType;

    @FindBy(xpath = "//div[contains(@class, 'select2-result-label') and contains(., 'CSV')]")
    public WebElement csvOption;

    @FindBy(xpath = "//input[@name='submit_export']")
    public WebElement exportButton;

    @FindBy(xpath = "//a[normalize-space(text())='cancel']")
    public WebElement orCancelExport;

    @Step("Нажимает на вкладку Import - Export")
    public ExportPage clickImportExport() {
        webElementActions.click(importExport);
        return this;
    }

    @Step("Переключается на вкладку Export")
    public ExportPage clickExportTab() {
        webElementActions.click(exportTab);
        return this;
    }

    @Step("Открывает список доступных типов экспорта")
    public ExportPage exportType() {
        webElementActions.click(exportType);
        return this;
    }

    @Step("Выбирает формат CSV для экспорта")
    public ExportPage selectCsvFormat() {
        webElementActions.click(csvOption);
        return this;
    }

    @Step("Нажимает кнопку Export для начала экспорта данных")
    public ExportPage clickExportButton() {
        webElementActions.click(exportButton);
        return this;
    }

    @Step("Отменяет процесс экспорта")
    public ExportPage orCancelExport(){
        webElementActions.click(orCancelExport);
        return this;
    }
}
