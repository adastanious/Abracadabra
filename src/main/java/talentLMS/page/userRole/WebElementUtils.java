package talentLMS.page.userRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Утилитный класс для работы с WebElement.
 * Содержит методы для проверки кликабельности элементов и другие полезные функции.
 */
public class WebElementUtils {
    /**
     * Метод проверяет, можно ли нажать на элемент.
     *
     * @param element WebElement, который нужно проверить.
     * @param wait WebDriverWait для ожидания элемента.
     * @return true, если элемент кликабелен, иначе false.
     */
    public static boolean isElementClickable(WebElement element, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Ждём, пока элемент станет кликабельным.
            return element.isDisplayed() && element.isEnabled(); // Возвращает true, если элемент видим и активен.
        } catch (Exception e) {
            return false; // Если элемент не кликабелен, возвращает false.
        }
    }
}
