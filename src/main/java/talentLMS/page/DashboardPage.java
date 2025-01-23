package talentLMS.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{
    //Main items
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Users')]")
    WebElement usersBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Categories')]")
    WebElement categoriesBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Branches')]")
    WebElement branchesBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'User types')]")
    WebElement userTypesBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Reports')]")
    WebElement reportsBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Courses')]")
    WebElement coursesBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Groups')]")
    WebElement groupsBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Events engine')]")
    WebElement eventsEngineBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Import - Export')]")
    WebElement importExportBtn;
    @FindBy(xpath = "//div[@class ='tl-bold-link']/a[contains(text(), 'Account & Settings')]")
    WebElement accountAndSettings;
    //Sub items
    @FindBy(xpath = "//span[@class='arrow-down']")
    WebElement subMenu;
    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    WebElement legacyMenuItem;


    //"Go to" method
    private WebElement getButton(String buttonName) {
        switch (buttonName.toLowerCase()) {
            case "users":
                return usersBtn;
            case "categories":
                return categoriesBtn;
            case "branches":
                return branchesBtn;
            case "user types":
                return userTypesBtn;
            case "reports":
                return reportsBtn;
            case "courses":
                return coursesBtn;
            case "groups":
                return groupsBtn;
            case "events engine":
                return eventsEngineBtn;
            case "import export":
                return importExportBtn;
            case "account settings":
                return accountAndSettings;
            default:
                throw new IllegalArgumentException("Invalid button name: " + buttonName);
        }
    }

    public <T> T goToPage(String buttonName, Class<T> pageClass) {
        webElementActions.click(getButton(buttonName));
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate page: " + pageClass.getName(), e);
        }
    }

    //"Go to" method realization
    public UsersPage usersPage(){
        return goToPage("users", UsersPage.class);
    }
    public CategoriesPage categoriesPage(){
        return goToPage("categories", CategoriesPage.class);
        }
    public BranchesPage branchesPage(){
        return goToPage("branches", BranchesPage.class);
            }
    public UserTypesPage userTypesPage(){
        return goToPage("user types", UserTypesPage.class);
                }
    public ReportsPage reportsPage(){
        return goToPage("reports", ReportsPage.class);
                    }
    public CoursesPage coursesPage(){
        return goToPage("courses", CoursesPage.class);
                        }
    public GroupsPage groupsPage(){
        return goToPage("groups", GroupsPage.class);
                            }
    public EventsEnginePage eventsEnginePage(){
        return goToPage("events engine", EventsEnginePage.class);
    }
    public ImportExportPage importExportPage (){
        return goToPage("import export", ImportExportPage.class);
    }
    public AccountSettingsPage accountSettingsPage(){
        return goToPage("account settings", AccountSettingsPage.class);
    }

    public DashboardPage switchToLegacyInterface () {
        try {
            if (driver.findElement(By.xpath("//strong[contains(text(), 'New interface')]")).isDisplayed()) {
                System.out.println("You are already using legacy interface");
                return this;
            }

        } catch (NoSuchElementException e) {
            subMenu.click();
            legacyMenuItem.click();
            return this;
        }
        return this;
    }
}


