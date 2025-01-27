package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.page.BasePage;

/**
 * Класс SwitchRolePage отвечает за переключение между ролями пользователей в системе TalentLMS.
 * Наследует функционал от BasePage.
 */
public class SwitchRolePage extends BasePage {

    // Локатор для выбора роли "Learner"
    @FindBy(xpath = "//a[normalize-space()='Learner']")
    public WebElement learnerUserRole;

    // Локатор для выбора роли "Instructor"
    @FindBy(xpath = "//a[normalize-space()='Instructor']")
    public WebElement instructorUserRole;

    /**
     * Метод для клика по определенной роли пользователя.
     * Принимает строковый параметр `role`, который определяет, на какую роль нужно кликнуть.
     *
     * @param role Название роли, которую нужно выбрать ("learner" или "instructor").
     * @throws IllegalArgumentException если передана некорректная роль.
     */
    public void clickUserRole(String role) {
            switch (role.toLowerCase()) { // Приводим роль к нижнему регистру для универсальности
                case "instructor":
                    webElementActions.click(instructorUserRole); // Кликаем по роли Инструктора
                    break;
                case "learner":
                    webElementActions.click(learnerUserRole); // Кликаем по роли Учащегося
                    break;
                default:
                    // Выбрасываем исключение, если передана неизвестная роль
                    throw new IllegalArgumentException("Неверная роль пользователя: " + role);
            }
        }
    }


