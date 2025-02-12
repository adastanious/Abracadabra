package talentLMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import talentLMS.driver.Driver;
import talentLMS.entity.*;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.accountAndSettings.BasicSettingsPage;

import talentLMS.fileUtils.ConfigReader;
import talentLMS.page.categoriesPage.CategoriesPage;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.dashboard.DashboardPage;
import talentLMS.page.groupsPage.Groups;

import talentLMS.page.accountAndSettings.DomainPage;
import talentLMS.page.accountAndSettings.UsersPage;
import talentLMS.page.categoriesPage.CategoriesPage;
import talentLMS.page.coursePage.CoursesPage;
import talentLMS.page.dashboard.DashboardPage;

import talentLMS.page.reports.CourseReportPage;
import talentLMS.page.importExport.ExportPage;
import talentLMS.page.importExport.ImportPage;
import talentLMS.page.importExport.SyncWithAnFtpServerPage;

import talentLMS.page.userRole.AdministratorUserRole;
import talentLMS.page.userRole.InstructorUserRole;
import talentLMS.page.userRole.LearnerUserRole;
import talentLMS.page.userTypesPage.UserTypes;
import talentLMS.page.users.UserPage;
import talentLMS.helper.WebElementActions;
import talentLMS.page.login.LoginPage;
import talentLMS.utils.randomEntityUtils.RandomGroupGenerator;
import talentLMS.utils.randomEntityUtils.RandomUserGenerator;

import talentLMS.utils.randomEntityUtils.RandomUserTypeGenerator;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;
    public WebElementActions webElementActions = new WebElementActions();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    public User randomUser = randomUserGenerator.randomUser();
    public CoursesPage coursesPage = new CoursesPage();
    public Courses courses = new Courses();
    public Component component = new Component();
    public CategoriesPage categoriesPage = new CategoriesPage();
    public Category category = new Category();
    public AdministratorUserRole administratorUserRole = new AdministratorUserRole();
    public InstructorUserRole instructorUserRole = new InstructorUserRole();
    public LearnerUserRole learnerUserRole = new LearnerUserRole();
    public BasicSettingsPage basicSettingsPage = new BasicSettingsPage();
    public DashboardPage dashboardPage = new DashboardPage();

    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public Groups groups  = new Groups();
    public RandomGroupGenerator randomGroupGenerator = new RandomGroupGenerator();
    public UsersPage settingsUsersPage = new UsersPage();
    public DomainPage domainPage = new DomainPage();
    public CourseReportPage courseReportPage = new CourseReportPage();
    public ImportPage importPage = new ImportPage();
    public ExportPage exportPage = new ExportPage();
    public SyncWithAnFtpServerPage syncWithAnFtpServerPage = new SyncWithAnFtpServerPage();
    public RandomUserTypeGenerator randomUserTypeGenerator = new RandomUserTypeGenerator();
    public UserTypes userTypes = new UserTypes();



    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
    }

    @BeforeClass
    public void beforeClass(){
        driver.get(ConfigReader.getProperty("dashboardURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"));
    }

    @AfterClass
    public void afterClass(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}