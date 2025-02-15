package talentLMS.reportsTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;

public class ReportCoursesTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod(){
        driver.get(ConfigReader.getProperty("dashboardURL"));
        dashboardPage.selectSection(AdminSection.REPORTS);
    }

    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test1() {
        webElementActions.click(courseReportPage.getCourseReport());
        courseReportPage.getCoursesReportFormTable();
        Assert.assertEquals(Integer.parseInt(courseReportPage.getCoursesQuantity().getText()), courseReportPage.getCoursesReportFormTable().size(), "Проверьте количество курсов");
    }

    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test2(){
        courseReportPage.goCourse();
        Assert.assertEquals(courseReportPage.getCourseName1().getText(), courseReportPage.getCourseName2().getText(), "Проверьте название курсов после того как вы вошли в отчеты в курсов");
    }
    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test3(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getYesterdayBtn());
        webElementActions.isElementClickable(courseReportPage.getTodayBtn());
        Assert.assertEquals(courseReportPage.getTodayBtn().getText(), "Today", "Проверьте поле Overall");
    }
    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test4(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getTodayBtn());
        webElementActions.isElementClickable(courseReportPage.getYesterdayBtn());
        Assert.assertEquals(courseReportPage.getYesterdayBtn().getText(), "Yesterday", "Проверьте поле Overall");
    }
    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test5(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getYesterdayBtn());
        Assert.assertEquals(courseReportPage.getWeekBtn().getText(), "Week", "Проверьте поле Overall");
        webElementActions.isElementClickable(courseReportPage.getWeekBtn());
    }
    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test6(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getYesterdayBtn());
        Assert.assertEquals(courseReportPage.getMonthBtn().getText(), "Month", "Проверьте поле Overall");
        webElementActions.isElementClickable(courseReportPage.getMonthBtn());
    }
    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test7(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getYesterdayBtn());
        Assert.assertEquals(courseReportPage.getYearBtn().getText(), "Year", "Проверьте поле Overall");
        webElementActions.isElementClickable(courseReportPage.getYearBtn());
    }
    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test8(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getYesterdayBtn());
        Assert.assertEquals(courseReportPage.getPeriodBtn().getText(), "Period", "Проверьте поле Overall");
        webElementActions.isElementClickable(courseReportPage.getPeriodBtn());
    }

    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test9(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getUserBtn());
        Assert.assertEquals(courseReportPage.getUserText().getText(), "USER");
    }

    @Test(groups = "Regression", description = "Проверка соответствий текстов")
    public void test10(){
        courseReportPage.goCourse();
        webElementActions.click(courseReportPage.getTimeLineBtn());
        Assert.assertEquals(courseReportPage.getTimeLineText().getText(), "EVENTS");
    }
}
