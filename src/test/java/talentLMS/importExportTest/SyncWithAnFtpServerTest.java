package talentLMS.importExportTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.fileUtils.ConfigReader;

///  @author Agema

public class SyncWithAnFtpServerTest extends BaseTest {

    /// Подготавливает тест, открывая страницу "Dashboard".
    @BeforeMethod
    public void beforeMethod(){
        driver.get(ConfigReader.getProperty("dashboardURL"));
    }

    @Test(groups = "Smoke", description = "Тест для настройки и проверки синхронизации с FTP-сервером", priority = 1)
    public void testFtpSyncConfiguration() {

        syncWithAnFtpServerPage
                .clickImportExport()
                .clickSyncWithAnFTPServerTab()
                .enterHost("192.168.1.1")
                .enterPort("21")
                .enterUsername("Agema")
                .enterPassword("123456")
                .clickTestConnection()
                .enableExport(true)
                .clickSave();
    }
}
