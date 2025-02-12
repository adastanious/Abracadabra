package talentLMS.page.accountAndSettings;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.enums.accountAndSettings.AccountAndSettings;
import talentLMS.page.BasePage;

@Getter

/**
 @author Dastan Aidarov
 */

public class DomainPage extends BasePage {
    @FindBy (xpath = "//label[text()='Domain name']")
    WebElement domainName;

    @FindBy (xpath = "//input[@name='domain_name']")
    WebElement domainNameCell;

    @FindBy (xpath = "//input[@name='domain_name']/parent::div/parent::div/span/span")
    WebElement errorText;

    @FindBy (xpath = "//input[@name='domain_name']/following-sibling::span")
    WebElement talentLMSCom;

    @FindBy (xpath = "//input[@name='submit_domain_name']")
    WebElement changeBtn;

    @Step("Изменение имени домена: {name}")
    /**
     * Изменяет имя домена на указанное и применяет изменения.
     *
     * Метод очищает поле ввода, вводит новое имя домена и нажимает кнопку изменения.
     *
     * @param name Новый домен в виде объекта AccountAndSettings.
     * @return Объект DomainPage после изменения доменного имени.
     */
    public DomainPage changeDomainName(AccountAndSettings name) {
        webElementActions.clearAndSendKeys(this.domainNameCell, name.getString())
                        .click(changeBtn);
        return new DomainPage();
    }
}
