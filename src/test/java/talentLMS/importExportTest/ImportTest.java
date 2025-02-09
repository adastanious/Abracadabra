package talentLMS.importExportTest;

import org.testng.Assert;
import org.testng.annotations.*;
import talentLMS.BaseTest;

///   @author Agema

public class ImportTest extends BaseTest {

    /// Подготавливает тест, открывая страницу "Dashboard".
    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    /// Проверяет успешный импорт файла и загрузку образца.
    @Test(priority = 1)
    public void importAndDownloadTest() {

        importPage
                .openImportExportPage()
                .uploadFile("/Users/Agema/Documents/Abracadabra/src/main/resources/file/agema-export-28-01-2025.xlsx");

        Assert.assertTrue(importPage.isFileImported("Upload complete. Click the import button to proceed."), "The file was not imported");

        importPage.clickImportButton().downloadSampleFile();
    }

    /// Проверяет невозможность импорта неподдерживаемого файла.
    @Test(priority = 2)
    public void importAndDownloadNegativeTest() {

        importPage
                .openImportExportPage()
                .uploadFile("/Users/Agema/Documents/Abracadabra/src/main/resources/file/Снимок экрана 2024-12-12 в 12.27.03.png");

        Assert.assertTrue(importPage.isFileImported("File type not allowed"), "The file was incorrectly imported");
    }

    /// Проверяет использование всех доступных примеров импорта.
    @Test(priority = 3)
    public void useAllReadyMadeExamplesTest() {

        importPage
                .openImportExportPage()
                .openCheatsheet()
                .useThisExample()
                .clickImportButton()
                .downloadSampleFile();

        Assert.assertTrue(importPage.isImportMessagePresent("Line 11: Group New employees updated successfully"), "Ожидаемое сообщение не найдено в результатах импорта!");
    }

    /// Проверяет, что кнопка "Import" отключена без загруженного файла.
    @Test(priority = 4)
    public void verifyImportButtonIsDisabled() {

        importPage
                .openImportExportPage();

        Assert.assertTrue(importPage.isImportButtonDisabled(), "Кнопка 'Import' должна быть неактивна без загруженного файла");
    }

    /// Проверяет возможность отмены импорта.
    @Test(priority = 5)
    public void importOrCancel() {

        importPage
                .openImportExportPage()
                .cancelImport();
    }

    /// Проверяет, что кнопка "Sample Excel File" кликабельна.
    @Test(priority = 6)
    public void verifySampleExcelFileIsClickable() {

        importPage
                .openImportExportPage();

        Assert.assertTrue(importPage.isSampleExcelFileVisible(), "Кнопка 'Sample Excel File' не отображается");

        importPage
                .downloadSampleFile();
    }

    /// Проверяет, что кнопка "Import" заблокирована во время загрузки файла.
    @Test(priority = 7)
    public void verifyImportButtonDisabledDuringUpload() {

        importPage
                .openImportExportPage();

        importPage
                .uploadFile("/Users/Agema/Documents/Abracadabra/src/main/resources/file/agema-export-28-01-2025.xlsx");

        Assert.assertFalse(importPage.isImportButtonEnabled(), "Кнопка 'Import' должна быть заблокирована во время загрузки");
    }

    /// Проверяет очистку поля загрузки перед новой загрузкой файла.
    @Test(priority = 8)
    public void verifyClearFileUpload() {

        importPage
                .openImportExportPage()
                .uploadFile("/Users/Agema/Documents/Abracadabra/src/main/resources/file/agema-export-28-01-2025.xlsx")
                .uploadFile("/Users/Agema/Documents/Abracadabra/src/main/resources/file/import_ou_xlsx (1).xlsx");

        Assert.assertTrue(importPage.isFileImported("Upload complete"), "Очистка поля загрузки не сработала");
    }

    /// Проверяет успешный импорт файла и появление сообщения "Import completed successfully".
    @Test(priority = 9)
    public void verifySuccessMessageAfterImport() {

        importPage
                .openImportExportPage()
                .uploadFile("/Users/Agema/Documents/Abracadabra/src/main/resources/file/agema-export-28-01-2025.xlsx")
                .clickImportButton();
    }
}
