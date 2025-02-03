package talentLMS.page.users;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.driver.Driver;
import talentLMS.entity.User;
import talentLMS.entity.UserEntity;
import talentLMS.page.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nasyikat Aiperi Aizada Agema Adinai
 */

public class UserPage extends BasePage {

    @FindBy(xpath = "//div[@class='tl-header-tools pull-left']//a[contains(text(),'Add user')]")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//span[@title='Learner-Type']")
    public WebElement learnerType;

    @FindBy(xpath = "(//div/i[@class='icon-pencil icon-grid' and @alt='Edit'])[1]")
    public WebElement edit;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement clickEditName;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement submit;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteClick;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement updateUserBtn;


    /**
     * Общий метод для ввода данных пользователя.
     *
     * @param user объект класса User, содержащий данные пользователя (имя, фамилия, email, логин, пароль).
     */
    private void enterUserData(User user) {
        webElementActions
                .sendKeys(this.firstName, user.getFirstname())
                .sendKeys(this.lastName, user.getLastname())
                .sendKeys(this.email, user.getEmail())
                .sendKeys(this.login, user.getUsername())
                .sendKeys(this.password, user.getPassword());
    }

    /**
     * Метод добавления нового пользователя.
     *
     * @param user объект класса User с данными нового пользователя.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage addNewUser(User user) {
        webElementActions
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(this.addUserButton);
        return new UserPage();
    }

    /**
     * Метод добавления пользователя с некорректным email.
     * Используется для проверки обработки ошибок при вводе данных.
     *
     * @param user объект класса User с некорректным email.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage userWithIncorrectEmail(User user) {
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(addUserButton);
        return new UserPage();
    }

    /**
     * Метод редактирования данных существующего пользователя.
     *
     * @param user объект класса User с обновленными данными (например, имя).
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage editUser(User user) {
        webElementActions
                .click(learnerType)
                .click(edit)
                .click(clickEditName)
                .sendKeys(clickEditName, user.getFirstname())
                .click(submit);
        return new UserPage();
    }

    /**
     * Метод удаления пользователя по email.
     *
     * @param email строка, содержащая email пользователя, которого необходимо удалить.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage deleteUser(String email) {
        WebElement createdUser = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]"));
        WebElement xBtn = driver.findElement(By.xpath("//thead/following-sibling::*/child::*//span[contains(text(),'" + email + "')]/parent::*/following-sibling::*[4]"));
        webElementActions
                .moveToElement(createdUser)
                .click(xBtn)
                .click(deleteClick);
        return new UserPage();
    }

    /**
     * Метод добавления пользователя с уникальным email.
     * Используется для проверки уникальности email при добавлении нового пользователя.
     *
     * @param user объект класса User с уникальным email.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage uniqueEmail(User user) {
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(addUserButton);
        return new UserPage();
    }

    /**
     * Метод для получения списка пользователей с веб-страницы из таблицы.
     * <p>
     * Этот метод извлекает все строки таблицы пользователей и создает список объектов UserEntity,
     * представляющих каждого пользователя. Каждый объект UserEntity содержит информацию о пользователе,
     * такой как имя пользователя, email, тип пользователя и дата регистрации.
     * </p>
     *
     * @return Список объектов UserEntity, представляющих всех пользователей на странице.
     */
    public ArrayList<UserEntity> getUserFromTable() {

        // Находим все строки таблицы пользователей
        List<WebElement> rows = Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"));

        // Создаём список для хранения объектов UserEntity
        ArrayList<UserEntity> userEntities = new ArrayList<>();

        // Перебираем каждую строку таблицы (каждый пользователь = одна строка)
        for (WebElement row : rows) {

            // Получаем все ячейки (столбцы) внутри строки
            List<WebElement> cells = row.findElements(By.cssSelector("td"));

            // Проверяем, что в строке есть все нужные данные
            if (cells.size() < 5) {
                continue; // Пропускаем строки с ошибками
            }

            //  Извлекаем данные из ячеек
            String username = cells.get(1).getText().trim();  // Имя пользователя
            String email = cells.get(2).getText().trim();     // Email
            String userType = cells.get(3).getText().trim();  // Тип пользователя
            String registration = cells.get(4).getText().trim(); // Дата регистрации

            //  Создаём объект сотрудника и добавляем его в список
            userEntities.add(new UserEntity(username, email, userType, registration));
        }
        //  Возвращаем список всех найденных сотрудников
        return userEntities;
    }

    /**
     * Метод для входа в аккаунт пользователя по имени.
     *
     * @param username Имя пользователя, в аккаунт которого нужно войти.
     * @throws NoSuchElementException если пользователь с указанным именем не найден.
     */
    public void logIntoAccount(String username) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20)); // Увеличиваем время ожидания

        // Ожидание загрузки таблицы пользователей
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#tl-users-grid")));

        // Находим все строки таблицы с пользователями
        List<WebElement> rows = Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"));

        boolean userFound = false; // Флаг для проверки нахождения пользователя

        // Перебираем строки таблицы
        for (WebElement row : rows) {
            // Получаем ячейки текущей строки
            List<WebElement> cells = row.findElements(By.cssSelector("td"));

            // Получаем имя пользователя из нужной ячейки (предполагается, что это вторая колонка)
            String foundUsername = cells.get(1).getText().trim();

            // Проверяем, совпадает ли имя пользователя с переданным аргументом
            if (foundUsername.equalsIgnoreCase(username.trim())) {
                System.out.println("Совпадение найдено, выполняем клик по Learner-Type.");

                // Находим элемент Learner-Type в текущей строке
                WebElement learnerType = cells.get(3).findElement(By.cssSelector("td.tl-align-left span[title=\"Learner-Type\"]"));
                wait.until(ExpectedConditions.elementToBeClickable(learnerType)).click();
                System.out.println("Кликнули по элементу Learner-Type.");

                // Находим кнопку входа в аккаунт в той же строке
                WebElement logIntoAccountBtn = row.findElement(By.cssSelector("i.icon-signin"));
                wait.until(ExpectedConditions.elementToBeClickable(logIntoAccountBtn)).click();
                System.out.println("Кликнули по кнопке для входа в аккаунт.");

                // Ожидание появления кнопки "Назад" и клик по ней
                WebElement backToAdminBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//i[@class='icon-undo tl-icon16']") // Иконка кнопки "Назад"
                ));
                backToAdminBtn.click();
                System.out.println("Кликнули по кнопке 'Назад'.");

                userFound = true; // Устанавливаем флаг, что пользователь найден
                break; // Прерываем цикл, так как нужный пользователь найден и обработан
            }
        }
        // Если пользователь не найден, выбрасываем исключение
        if (!userFound) {
            throw new NoSuchElementException("Пользователь с именем " + username + " не найден.");
        }
    }

    /**
     * Метод для редактирования аккаунта пользователя по имени.
     *
     * @param username Имя пользователя, чей аккаунт нужно отредактировать.
     * @param user     Объект User с новыми данными (например, новое имя).
     * @throws NoSuchElementException если пользователь с указанным именем не найден.
     */
    public void editAccount(String username, User user) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20)); // Увеличиваем время ожидания

        // Ожидание загрузки таблицы пользователей
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#tl-users-grid")));

        // Находим все строки таблицы с пользователями
        List<WebElement> rows = Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"));

        boolean userFound = false; // Флаг для проверки нахождения пользователя

        // Перебираем строки таблицы
        for (WebElement row : rows) {
            // Получаем ячейки текущей строки
            List<WebElement> cells = row.findElements(By.cssSelector("td"));

            // Получаем имя пользователя из нужной ячейки (предполагается, что это вторая колонка)
            String foundUsername = cells.get(1).getText().trim();

            // Проверяем, совпадает ли имя пользователя с переданным аргументом
            if (foundUsername.equalsIgnoreCase(username.trim())) {
                System.out.println("Совпадение найдено, выполняем клик по Learner-Type.");

                // Находим элемент Learner-Type в текущей строке
                WebElement learnerType = cells.get(3).findElement(By.cssSelector("td.tl-align-left span[title=\"Learner-Type\"]"));
                wait.until(ExpectedConditions.elementToBeClickable(learnerType)).click();
                System.out.println("Кликнули по элементу Learner-Type.");

                // Находим кнопку редактирования аккаунта в текущей строке
                WebElement editBtn = row.findElement(By.cssSelector("i.icon-pencil.icon-grid"));
                wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
                System.out.println("Кликнули по кнопке редактирования.");

                // Редактирование имени пользователя
                webElementActions.click(clickEditName) // Клик в поле редактирования имени
                        .sendKeys(clickEditName, user.getFirstname()) // Ввод нового имени
                        .click(updateUserBtn); // Подтверждение изменений

                userFound = true; // Устанавливаем флаг, что пользователь найден
                break; // Прерываем цикл, так как нужный пользователь найден и обработан
            }
        }

        // Если пользователь не найден, выбрасываем исключение
        if (!userFound) {
            throw new NoSuchElementException("Пользователь с именем " + username + " не найден.");
        }
    }
}