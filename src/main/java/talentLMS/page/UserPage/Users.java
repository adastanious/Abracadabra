package talentLMS.page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import talentLMS.entity.User;
import talentLMS.page.BasePage;

public class Users extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Add user')]")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//div[@class='span8']/child::*[3]//span[@class='help-block']")
    public WebElement errorMessage;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    // xpath кнопки поля first name в разделе edit
    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement clickEditName;

    //xpath кнопки submit
    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement submit;

    // xPath удалить пользователя
    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteClick;

    /**
     * Добавляет нового пользователя.
     * <p>
     * Этот метод заполняет форму для добавления нового пользователя, включая имя, фамилию,
     * email, логин и пароль, и нажимает кнопку для добавления пользователя. Также метод
     * поддерживает как позитивный, так и негативный кейс. В случае негативного кейса проверяется
     * отображение сообщения об ошибке.
     *
     * @param user           объект типа User, содержащий имя, фамилию, логин и пароль нового пользователя.
     * @param email          email-адрес пользователя.
     * @param isNegativeCase флаг, указывающий, является ли тест негативным кейсом. Если true, проверяется
     *                       наличие сообщения об ошибке.
     * @return возвращает объект типа Users, если это позитивный кейс, или текущую страницу в случае негативного кейса.
     */
    public Users addNewUser(User user, String email, boolean isNegativeCase) {
        // Заполняем форму для добавления нового пользователя
        webElementActions
                .click(this.addUser) // Кликаем на кнопку "Добавить пользователя"
                .sendKeys(this.firstName, user.getFirstname()) // Вводим имя пользователя
                .sendKeys(this.lastName, user.getLastname()) // Вводим фамилию пользователя
                .sendKeys(this.email, email) // Вводим email
                .sendKeys(this.login, user.getUsername()) // Вводим логин
                .sendKeys(this.password, user.getPassword()) // Вводим пароль
                .click(this.addUserButton); // Нажимаем на кнопку добавления пользователя

        // Проверяем, является ли это негативным кейсом
        if (isNegativeCase) {
            // Если это негативный кейс, проверяем наличие сообщения об ошибке
            if (webElementActions.isVisible(this.errorMessage)) {
                String errorText = webElementActions.getText(this.errorMessage); // Получаем текст ошибки
                System.out.println("Error message displayed: " + errorText); // Выводим текст ошибки
            } else {
                System.out.println("Expected an error, but no error message was displayed."); // Если ошибки нет
            }
            return this; // Возвращаем текущую страницу для дальнейшей проверки
        }

        // Если это позитивный кейс, возвращаем страницу нового пользователя
        return new Users();
    }

    /**
     * Редактирует имя пользователя.
     * <p>
     * Этот метод находит пользователя по указанному email в таблице пользователей,
     * нажимает кнопку редактирования рядом с этим пользователем и изменяет его имя
     * на значение, переданное в объекте `user`. После этого отправляется форма редактирования.
     *
     * @param user  объект типа User, содержащий обновленное имя пользователя.
     * @param email email-адрес пользователя, которого необходимо отредактировать.
     * @return возвращает текущую страницу (объект типа Users) после успешного редактирования.
     */
    public Users editUserName(User user, String email) {
        // Находим пользователя по email
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::/child:://span[contains(text(),'" + email + "')]"));

        // Находим кнопку редактирования для этого пользователя
        WebElement editBtn = driver.findElement(By.xpath("//thead/following-sibling::/child:://span[contains(text(),'" + email + "')]/parent::/following-sibling::[4]//i[@title='Edit']"));

        // Делаем все необходимые действия: наводим курсор на пользователя, кликаем на кнопку редактирования,
        // очищаем текущее имя и вводим новое, а затем отправляем форму
        webElementActions
                .moveToElement(createdUser) // Наводим курсор на найденного пользователя
                .click(editBtn) // Кликаем по кнопке редактирования
                .clearAndSendKeys(clickEditName, user.getFirstname()) // Очищаем поле имени и вводим новое
                .click(submit); // Нажимаем кнопку отправки формы

        return this; // Возвращаем текущую страницу после редактирования
    }

    /**
     * Удаляет пользователя по указанному email.
     * <p>
     * Этот метод находит пользователя по email в таблице пользователей, нажимает кнопку
     * удаления рядом с этим пользователем и подтверждает удаление. После выполнения операции
     * метод возвращает текущую страницу.
     *
     * @param email email-адрес пользователя, которого необходимо удалить.
     * @return возвращает текущую страницу (объект типа Users) после успешного удаления.
     */
    public Users deleteUsers(String email) {
        // Находим пользователя по email
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::/child:://span[contains(text(),'" + email + "')]"));

        // Находим кнопку удаления для этого пользователя
        WebElement xBtn = driver.findElement(By.xpath("//thead/following-sibling::/child:://span[contains(text(),'" + email + "')]/parent::/following-sibling::[4]"));

        // Делаем все необходимые действия: наводим курсор на пользователя, кликаем на кнопку удаления
        // и подтверждаем удаление
        webElementActions
                .moveToElement(createdUser) // Наводим курсор на найденного пользователя
                .click(xBtn) // Кликаем по кнопке удаления
                .click(deleteClick); // Подтверждаем удаление

        return this; // Возвращаем текущую страницу после удаления
    }
}

