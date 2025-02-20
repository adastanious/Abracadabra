package talentLMS.entity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.enums.Role;
import talentLMS.page.BasePage;

import static talentLMS.enums.Role.*;

public class Component extends BasePage {
    @FindBy(xpath = "//a[@class = 'dropdown-toggle tl-nabvar-roles-button']")
    public WebElement userRoleMove;

    // Локатор для выбора роли "Learner"
    @FindBy(xpath = "//a[normalize-space()='Learner']")
    public WebElement learnerUserRole;

    // Локатор для выбора роли "Instructor"
    @FindBy(xpath = "//a[normalize-space()='Instructor']")
    public WebElement instructorUserRole;

    // Локатор для выбора роли "Administrator"
    @FindBy(xpath = "//a[normalize-space()='Administrator']")
    public WebElement administratorUserRole;

    /**
     * Метод для клика по определенной роли пользователя.
     * Принимает строковый параметр role, который определяет, на какую роль нужно кликнуть.
     *
     * @param role Название роли, которую нужно выбрать ("learner" "instructor" или "administrator").
     * @throws IllegalArgumentException если передана некорректная роль.
     */
    public void selectRole(Role role) {
        webElementActions.moveToElement(userRoleMove);
        if (role.equals(INSTRUCTOR)) {
            webElementActions.click(instructorUserRole);// Кликаем по роли Инструктора
        }else if (role.equals(LEARNER)){
            webElementActions.click(learnerUserRole);// Кликаем по роли Учащегося
        }
        else if (role.equals(ADMINISTRATOR)) {
            webElementActions.click(administratorUserRole);// Кликаем по роли Администратора
        }
        else {
            // Выбрасываем исключение, если передана неизвестная роль
            throw new IllegalArgumentException("Неверная роль пользователя: " + role);
        }
    }
}


