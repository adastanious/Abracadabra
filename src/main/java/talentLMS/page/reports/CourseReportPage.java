package talentLMS.page.reports;

import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.ReportCourses;
import talentLMS.page.BasePage;
import java.util.ArrayList;
import java.util.List;
@Data

public class CourseReportPage extends BasePage {


    @FindBy(xpath = "//a[text()='Course reports']")
    WebElement courseReport;

    @FindBy(xpath = "//a[@class='tl-grid-size-more']")
    WebElement forTableBtn;

    @FindBy(xpath = "//td[@class=' tl-align-left']//a[@href]")
    List<WebElement> coursesName;

    @FindBy(xpath = "//div[@class=\"item-data\"]//div[@class=\"item-caption\" and text()='Courses']/..//div[@class=\"item-value hide\"]")
    WebElement coursesQuantity;

    @FindBy(xpath = "//h3//span[@class='tl-formatted-course-name']")
    WebElement courseName1;

    @FindBy(xpath = "//div[@class=\"tl-title tl-ellipsis\"]//span[@class='tl-formatted-course-name']")
    WebElement courseName2;

    @FindBy(id = "users-tab")
    WebElement userBtn;

    @FindBy(xpath = "//th[@class=\"tl-align-left sorting_asc\"]")
    WebElement userText;

    @FindBy(id = "timeline-tab")
    WebElement timeLineBtn;

    @FindBy(xpath = "//th[@class=\"tl-align-left sorting_disabled\"]")
    WebElement timeLineText;

    @FindBy(xpath = "//a[@class=\"btn tl-set-report-interval\" and text()='Today']")
    WebElement todayBtn;

    @FindBy(xpath = "//a[@class=\"btn tl-set-report-interval\" and text()='Yesterday']")
    WebElement yesterdayBtn;

    @FindBy(xpath = "//a[@class=\"btn tl-set-report-interval\" and text()='Week']")
    WebElement weekBtn;

    @FindBy(xpath = "//a[@class=\"btn tl-set-report-interval\" and text()='Month']")
    WebElement monthBtn;

    @FindBy(xpath = "//a[@class=\"btn tl-set-report-interval\" and text()='Year']")
    WebElement yearBtn;

    @FindBy(xpath = "//a[@class=\"btn tl-set-report-period\"]")
    WebElement periodBtn;

    /**
     * Метод для перехода на страницу отчета по курсу.
     * Этот метод выполняет последовательные клики по элементам на странице: сначала по кнопке отчета о курсе,
     * затем по конкретному курсу из списка.
     *
     * @return CourseReportPage Страница отчета о курсе, на которую происходит переход.
     */
    public CourseReportPage goCourse(){
        webElementActions.click(courseReport)
                .click(coursesName.get(1));

        return this;
    }


    /**
     * Метод для получения отчетных данных о курсах из таблицы на веб-странице.
     * Этот метод выполняет клик по кнопке таблицы, ждет загрузки данных и собирает информацию
     * о курсах, таких как название курса, категория, количество назначенных учеников и завершивших курс.
     *
     * @return ArrayList<ReportCourses> Список объектов ReportCourses, каждый из которых представляет
     *         строку таблицы с данными о курсе.
     *
     * @throws InterruptedException Если поток был прерван при ожидании (используется Thread.sleep).
     */
    @Step("Метод для получения отчетных данных о курсах из таблицы на веб-странице.")
    public ArrayList<ReportCourses> getCoursesReportFormTable() {
            if (webElementActions.isElementClickable(forTableBtn)){
                webElementActions.click(forTableBtn);
            }else {
            }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));

        ArrayList<ReportCourses> reportCoursesTable = new ArrayList<>();

        for (WebElement row : rows){
            List<WebElement> cells = row.findElements(By.xpath("//td"));
            String course = cells.get(0).getText();
            String category = cells.get(1).getText();
            String assignedLearners = cells.get(2).getText();
            String completedLearners = cells.get(3).getText();
            reportCoursesTable.add((new ReportCourses(course, category, assignedLearners, completedLearners)));
        }
        return  reportCoursesTable;
    }
}
