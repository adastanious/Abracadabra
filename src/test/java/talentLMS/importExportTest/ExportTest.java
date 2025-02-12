package talentLMS.importExportTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;

///   @author Agema

public class ExportTest extends BaseTest {

    /// Подготавливает тест, открывая страницу "Dashboard".
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    /// Тест для экспорта данных в формате CSV
    @Test(priority = 1)
    public void exportAsCsvTest() {
        exportPage
                .clickImportExport()
                .clickExportTab()
                .exportType()
                .selectCsvFormat()
                .clickExportButton();
    }

    /// Тест для экспорта данных в формате Excel (по умолчанию)
    @Test(priority = 2)
    public void exportAsExcelTest() {
        exportPage
                .clickImportExport()
                .clickExportTab()
                .clickExportButton();
    }

    /// Тест для отмены экспорта.
    @Test(priority = 3)
    public void exportOrCancel(){
        exportPage
                .clickImportExport()
                .clickExportTab()
                .orCancelExport();
    }
}
