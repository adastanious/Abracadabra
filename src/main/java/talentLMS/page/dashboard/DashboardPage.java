package talentLMS.page.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.enums.AdminSection;
import talentLMS.page.BasePage;

import java.util.NoSuchElementException;

/**
 @author Dastan Aidarov
 */
public class DashboardPage extends BasePage {
    @FindBy(xpath = "//span[@class='arrow-down']")
    WebElement subMenu;
    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']//p")
    WebElement legacyMenuItem;

    @FindBy(xpath = "//div[@class='tl-bold-link']")
    WebElement sectionsList;

    /**
     * Метод выбирает нужный раздел администратора на панели управления.
     * @param section раздел, который необходимо выбрать
     * @return новый объект DashboardPage
     */
    public DashboardPage selectSection(AdminSection section) {
        //switchToLegacyInterface();
        webElementActions.click(sectionsList.findElement(By.xpath("//a[contains(text(),'" + section + "')]")));
        return new DashboardPage();
    }

    /**
     * Метод переключается на устаревший интерфейс, если элементы доступны.
     * В случае отсутствия элементов, просто возвращает новую страницу.
     * @return новый объект DashboardPage
     */
    public DashboardPage switchToLegacyInterface () {
        try {
            webElementActions.click(subMenu)
                    .click(legacyMenuItem);
            return new DashboardPage();
        } catch (NoSuchElementException e) {
            return new DashboardPage();
        }
    }
    ////div[@class='tl-bold-link']//a[contains(text(),'Reports')]
}
