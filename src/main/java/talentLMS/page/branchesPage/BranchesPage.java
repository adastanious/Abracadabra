package talentLMS.page.branchesPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Branch;
import talentLMS.page.BasePage;

/**
 @author Adinai
 */

public class BranchesPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Add branch')]")
    public WebElement addBranch;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement addName;

    @FindBy(xpath = "//input[@name='title']")
    public WebElement addTitle;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement addDescription;

    @FindBy(xpath = "//input[@name='submit_branch']")
    public WebElement submit;

    @FindBy(xpath = "//table[@id='tl-branches-grid']//tr/td[@class=' tl-align-left hidden-phone']")
    public WebElement clickDescription;

    @FindBy(xpath = "//table[@id='tl-branches-grid']//tr[td/a]//i[contains(@class,'icon-pencil')]")
    public WebElement edit;

    @FindBy(xpath = "//input[@name='name'and @id='branch-name']")
    public WebElement clickEditName;

    @FindBy(xpath = "(//i[@title='Clone'])[1]")
    public WebElement clone;

    @FindBy(xpath = "//a[@id='tl-clone-modal-confirm-submit']")
    public WebElement clickClone;

    @FindBy(xpath = "//i[@title='Delete']")
    public WebElement delete;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement clickDelete;

        /**
         * Добавляет новый филиал.
         *
         * @param branch объект Branch, содержащий данные нового филиала
         * @return текущий экземпляр BranchesPage
         */
        public BranchesPage addNewBranch(Branch branch) {
            webElementActions.click(addBranch)
                    .sendKeys(this.addName, branch.getName())
                    .sendKeys(this.addTitle, branch.getTitle())
                    .sendKeys(this.addDescription, branch.getDescription())
                    .click(this.submit);
            return this;
        }

        /**
         * Редактирует существующий филиал.
         *
         * @param branch объект Branch, содержащий новые данные филиала
         * @return текущий экземпляр BranchesPage
         */
        public BranchesPage editBranch(Branch branch) {
            webElementActions.click(clickDescription)
                    .click(edit)
                    .click(clickEditName)
                    .sendKeys(clickEditName, branch.getName())
                    .click(submit);
            return this;
        }

        /**
         * Клонирует существующий филиал.
         *
         * @param branch объект Branch (не используется в методе, но может быть полезен для расширения функционала)
         * @return текущий экземпляр BranchesPage
         */
        public BranchesPage cloneBranch(Branch branch) {
            webElementActions.click(clickDescription)
                    .click(clone)
                    .click(clickClone);
            return this;
        }

        /**
         * Удаляет существующий филиал.
         *
         * @param branch объект Branch (не используется в методе, но может быть полезен для логирования или подтверждения удаления)
         * @return текущий экземпляр BranchesPage
         */
        public BranchesPage deleteBranch(Branch branch) {
            webElementActions.click(clickDescription)
                    .click(delete)
                    .click(clickDelete);
            return this;
        }
    }

