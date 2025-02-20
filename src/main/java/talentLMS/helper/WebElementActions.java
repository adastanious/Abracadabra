package talentLMS.helper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;

import java.time.Duration;

public class WebElementActions {
    public Actions actions = new Actions(Driver.getDriver());
    public WebElementActions waitButtonToBeClickable(WebElement element) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public WebElementActions waitElementToBeDisplayed(WebElement element) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public WebElementActions waitElementToBeDisappear(WebElement element) {
        new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOf(element));
        return this;
    }

    public WebElementActions click(WebElement element) {
        waitElementToBeDisplayed(element);
        waitButtonToBeClickable(element);
        element.click();
        return this;
    }


    public WebElementActions sendKeys(WebElement element, String text) {
        waitElementToBeDisplayed(element);
        element.sendKeys(text);
        return this;
    }

    public WebElementActions clearAndSendKeys(WebElement element, String text) {
        waitElementToBeDisplayed(element);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    public WebElementActions doubleClick(WebElement element) {
        waitElementToBeDisplayed(element);
        waitButtonToBeClickable(element);
        actions.doubleClick(element).perform();
        return this;
    }

    public WebElementActions rightClick(WebElement element) {
        waitElementToBeDisplayed(element);
        waitButtonToBeClickable(element);
        actions.contextClick(element).perform();
        return this;
    }

    public WebElementActions moveToElement(WebElement element) {
        waitElementToBeDisplayed(element);
        waitButtonToBeClickable(element);
        actions.moveToElement(element).perform();
        return this;
    }

    public WebElementActions sendKeysWithEnter(WebElement element, String text) {
        waitElementToBeDisplayed(element);
        element.clear();
        element.sendKeys(text,Keys.ENTER);
        return this;
    }

    /**
     * Метод проверяет, можно ли нажать на элемент.
     *
     * @param element WebElement, который нужно проверить.
    // * @param wait    WebDriverWait для ожидания элемента.
     * @return true, если элемент кликабелен, иначе false.
     */
    public boolean isElementClickable(WebElement element) {
        try {
            waitButtonToBeClickable(element);
            return element.isDisplayed() && element.isEnabled(); // Возвращает true, если элемент видим и активен.
        } catch (Exception e) {
            return false; // Если элемент не кликабелен, возвращает false.
        }
    }

    // Проверка видимости элемента (ImportExport)
    public boolean isVisibleImport(WebElement element) {
        return element.isDisplayed();
    }

    // Метод для загрузки файла (ImportExport)
    public WebElementActions importFiles(WebElement element, String path){
        element.sendKeys(path);
        return this;
    }

    // Метод для ввода текста в поле
    public WebElementActions typeText(WebElement element, String text) {
        element.clear();  // Очищаем поле перед вводом текста
        element.sendKeys(text); // Вводим текст
        return this;
    }
}
