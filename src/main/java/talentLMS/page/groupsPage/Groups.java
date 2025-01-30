package talentLMS.page.groupsPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.Group;
import talentLMS.page.BasePage;
   /**
   @author Aiperi, Nasyikat, Aiza, Agema, Adinai
    */
public class Groups extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-primary btn-large']")
    public WebElement addGroup;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement name;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement description;

    @FindBy(xpath = "//input[@name='submit_group']")
    public WebElement submit;

    @FindBy(xpath = "//tr[@class='odd']/td[@class=' tl-align-left hidden-phone']")
    public WebElement clickDescription;

    @FindBy(xpath = "//i[@title='Edit']")
    public WebElement edit;

    @FindBy(xpath = "//input[@name='submit_group']")
    public WebElement updateGroup;

    @FindBy(xpath = "//i[@class='icon-ellipsis-h tl-table-operations-icon']")
    public WebElement options;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement clickDelete;

        /**
         * Добавляет новую группу.
         *
         * Этот метод нажимает кнопку "Add group", затем заполняет поля
         * для имени и описания группы и отправляет форму для создания новой группы.
         *
         * @param group Объект группы, содержащий имя и описание.
         * @return Текущий экземпляр класса Groups для продолжения цепочки действий.
         */
        public Groups addNewGroup(Group group) {
            webElementActions.click(addGroup)
                    .sendKeys(this.name, group.getName())
                    .sendKeys(this.description, group.getDescription())
                    .click(submit);
            return this;
        }

        /**
         * Редактирует существующую группу.
         *
         * Этот метод выбирает группу для редактирования, заполняет новые значения
         * для имени и описания и отправляет форму для обновления группы.
         *
         * @param group Объект группы с обновленными данными (имя и описание).
         * @return Текущий экземпляр класса Groups для продолжения цепочки действий.
         */
        public Groups editGroup(Group group) {
            webElementActions.click(clickDescription)
                    .click(edit)
                    .sendKeys(this.name, group.getName())
                    .sendKeys(this.description, group.getDescription())
                    .click(updateGroup);
            return this;
        }

        /**
         * Удаляет группу.
         *
         * Этот метод открывает меню опций для группы и подтверждает её удаление.
         *
         * @param group Объект группы, которую необходимо удалить.
         * @return Текущий экземпляр класса Groups для продолжения цепочки действий.
         */
        public Groups deleteGroup(Group group) {
            webElementActions
                    .click(options)
                    .click(clickDelete);
            return this;
        }
    }