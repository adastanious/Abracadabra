package talentLMS.page.importExport;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.page.BasePage;

import java.time.Duration;

/// @author Agema

public class ImportPage extends BasePage {

    @FindBy(xpath = "//div/a[normalize-space()='Import - Export']")
    private WebElement importExport;

    @FindBy(xpath = "//input[@name='import-uploader']")
    private WebElement fileUploader;

    @FindBy(xpath = "//input[@name='submit_import' and @value='Import']")
    private WebElement importButton;

    @FindBy(xpath = "//a[@title='Sample Excel file']")
    private WebElement sampleExcelFile;

    @FindBy(xpath = "//a[@title='View cheatsheet']")
    private WebElement viewCheatsheet;

    @FindBy(xpath = "//a[@class='btn tl-example-paste-code' and @data-id='tl-concrete-import-example']")
    private WebElement useThisExample;

    @FindBy(xpath = "//span/a[text()='cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[contains(@class, 'import-success-message')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class, 'import-error-message')]")
    private WebElement errorMessage;

    @Step("Открывает страницу импорта и экспорта")
    public ImportPage openImportExportPage() {
        webElementActions.click(importExport);
        return this;
    }

    @Step("Загружает файл для импорта")
    public ImportPage uploadFile(String filePath) {
        webElementActions.importFiles(fileUploader, filePath);
        return this;
    }

    @Step("Нажимает кнопку Import")
    public ImportPage clickImportButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(importButton));
        importButton.click();
        return this;
    }

    @Step("Загружает образец файла Excel")
    public ImportPage downloadSampleFile() {
        webElementActions.click(sampleExcelFile);
        return this;
    }

    @Step("Открывает View cheatsheet")
    public ImportPage openCheatsheet() {
        webElementActions.click(viewCheatsheet);
        return this;
    }

    @Step("Используем готовый пример")
    public ImportPage useThisExample() {
        webElementActions.click(useThisExample);
        return this;
    }

    @Step("Нажимает кнопку Cancel")
    public ImportPage cancelImport() {
        webElementActions.click(cancelButton);
        return this;
    }

    @Step("Проверяет, импортирован ли файл по заданному сообщению")
    public boolean isFileImported(String message) {
        WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + message + "')]"));
        return webElementActions.isVisibleImport(element);
    }

    @Step("Проверяет, отключена ли кнопка Import")
    public boolean isImportButtonDisabled() {
        return !importButton.isEnabled();
    }

    @Step("Проверяет, что кнопка Import активна")
    public boolean isImportButtonEnabled() {
        return importButton.isEnabled();
    }

    @Step("Проверяет, отображается ли кнопка Sample Excel File")
    public boolean isSampleExcelFileVisible() {
        return sampleExcelFile.isDisplayed();
    }

    @Step("Проверяет, присутствует ли сообщение об импорте в результатах")
    public boolean isImportMessagePresent(String expectedMessage) {
        WebElement element = driver.findElement(By.id("tl-import-results"));
        String resultText = element.getText();
        return resultText.contains(expectedMessage);
    }
}
