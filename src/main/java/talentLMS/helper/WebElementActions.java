package talentLMS.helper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;

import java.time.Duration;
import java.util.NoSuchElementException;

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
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        return this;
    }

    /**
     * Метод проверяет, можно ли нажать на элемент.
     *
     * @param element WebElement, который нужно проверить.
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
}
