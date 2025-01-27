package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;


public class Component extends UserRole {
    UserRole userRole = new UserRole();

    // Локатор для выбора роли "Learner"
    @FindBy(xpath = "//a[normalize-space()='Learner']")
    public WebElement learnerUserRole;

    // Локатор для выбора роли "Instructor"
    @FindBy(xpath = "//a[normalize-space()='Instructor']")
    public WebElement instructorUserRole;

    // Локатор для выбора роли "Administrator"
    @FindBy(xpath = "//i[@class ='tl-select-icon-selected']")
    public WebElement administratorUserRole;

    public void selectRole(String role) {
        /**
         * Метод для клика по определенной роли пользователя.
         * Принимает строковый параметр ⁠ role ⁠, который определяет, на какую роль нужно кликнуть.
         *
         * @param role Название роли, которую нужно выбрать ("learner" или "instructor").
         * @throws IllegalArgumentException если передана некорректная роль.
         */
        userRole.moveUserRole();
        switch (role.toLowerCase()) { // Приводим роль к нижнему регистру для универсальности
            case "instructor":
                webElementActions.click(instructorUserRole);// Кликаем по роли Инструктора
                break;
            case "learner":
                webElementActions.click(learnerUserRole);// Кликаем по роли Учащегося
                break;
            case "administrator":
                webElementActions.click(administratorUserRole);// Кликаем по роли Администратора
                break;
            default:
                // Выбрасываем исключение, если передана неизвестная роль
                throw new IllegalArgumentException("Неверная роль пользователя: " + role);
        }
    }
}


