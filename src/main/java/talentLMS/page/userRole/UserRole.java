package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.page.BasePage;

public class UserRole extends BasePage {
    /**
     @author Turan, Agema, Nazik
     */
    @FindBy(xpath = "//a[@class = 'dropdown-toggle tl-nabvar-roles-button']")
    public WebElement userRole;

    // Метод для наведения на окно выбора роли пользователя
    public void moveUserRole(){
        webElementActions.moveToElement(userRole);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод проверяет, можно ли нажать на элемент.
     *
     * @param element WebElement, который нужно проверить.
     * @param wait WebDriverWait для ожидания элемента.
     * @return true, если элемент кликабелен, иначе false.
     */
    public boolean isElementClickable(WebElement element, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Ждём, пока элемент станет кликабельным.
            return element.isDisplayed() && element.isEnabled(); // Возвращает true, если элемент видим и активен.
        } catch (Exception e) {
            return false; // Если элемент не кликабелен, возвращает false.
        }
    }
}
