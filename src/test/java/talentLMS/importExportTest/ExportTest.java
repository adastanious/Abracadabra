package talentLMS.importExportTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.fileUtils.ConfigReader;

///   @author Agema

public class ExportTest extends BaseTest {

    /// Подготавливает тест, открывая страницу "Dashboard".
    @BeforeMethod
    public void beforeMethod(){
        driver.get(ConfigReader.getProperty("dashboardURL"));
    }

    @Test(groups = "Smoke", description = "Тест для экспорта данных в формате CSV", priority = 1)
    public void exportAsCsvTest() {

        exportPage
                .clickImportExport()
                .clickExportTab()
                .exportType()
                .selectCsvFormat()
                .clickExportButton();
    }

    @Test(groups = "Smoke", description = "Тест для экспорта данных в формате Excel (по умолчанию)", priority = 2)
    public void exportAsExcelTest() {

        exportPage
                .clickImportExport()
                .clickExportTab()
                .clickExportButton();
    }

    @Test(groups = "Regression", description = "Тест для отмены экспорта", priority = 3)
    public void exportOrCancel(){

        exportPage
                .clickImportExport()
                .clickExportTab()
                .orCancelExport();
    }
}
