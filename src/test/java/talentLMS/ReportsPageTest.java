package talentLMS;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.enums.AdminSection;

public class ReportsPageTest extends BaseTest{

    @BeforeMethod
    public void beforeMethod(){
        dashboardPage.selectSection(AdminSection.REPORTS);
    }

    @Test(priority = 1)
    public void goToTheUserReports(){
        reportsPage.goUserReports();
    }

    @Test(priority = 2)
    public void reportsButton(){
        reportsPage.reportBtn();
    }
}
