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
import java.util.stream.Collectors;

/**
 * @author Nasyikat
 */

public class UserPage extends BasePage {

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[contains(text(), 'Users')]")
    public WebElement UsersDashboard;

    @FindBy(xpath = "//span[@class='tl-box-title-options']/a[@title='Users']")
    public WebElement users;

    @FindBy(xpath = "//div[@class='tl-header-tools pull-left']//a[contains(text(),'Add user')]")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//*[@id=\"tl-users-grid\"]/tbody/tr[1]/td[4]/span")
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

    @FindBy(xpath = "//*[@id=\"status\"]")
    public WebElement tickActive;

    @FindBy(xpath = "//th[@class='tl-align-left sorting' and contains(text(), 'User')]")
    public WebElement userSortClick;

    @FindBy(xpath = "//th[@class='tl-align-left hidden-phone sorting' and contains(text(), 'Email')]")
    public WebElement emailSortClock;

    @FindBy(xpath = "//th[@class='tl-align-left hidden-phone sorting' and contains(text(), 'User type')]")
    public WebElement userTypeSortClick;

    @FindBy(xpath = "//th[@class='tl-align-center hidden-phone sorting' and contains(text(), 'Registration')]")
    public WebElement registrationSortClock;

    @FindBy(xpath = "//*[@id=\"1965967776\"]")
    public WebElement clickEditEmail;

    @FindBy(xpath = "((//td[@class=' tl-align-center tl-table-operations-cell']/div[@class='tl-table-operations-trigger touchable'])[2])")
    public WebElement operationIcon;

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
                .click(this.UsersDashboard)
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(this.addUserButton);
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
                .click(UsersDashboard)
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
     * @return объект страницы пользователя (UserPage).
     */

    public UserPage deleteUsers(User user) {
        webElementActions
                .click(UsersDashboard)
                .click(learnerType)
                .click(operationIcon)
                .click(deleteClick);
        return this;
    }

    /**
     * Метод добавления пользователя с уникальным email.
     * Используется для проверки уникальности email при добавлении нового пользователя.
     *
     * @param user объект класса User с уникальным email.
     * @return объект страницы пользователя (UserPage).
     */
    public UserPage uniqueEmail(User user) {
        webElementActions
                .click(UsersDashboard)
                .click(this.addUser);
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
        webElementActions.click(UsersDashboard);

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
        webElementActions.click(UsersDashboard);

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
        clickUsers();
        WebDriverWait wait1 = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20)); // Увеличиваем время ожидания

        // Ожидание загрузки таблицы пользователей
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#tl-users-grid")));

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
                wait1.until(ExpectedConditions.elementToBeClickable(learnerType)).click();
                System.out.println("Кликнули по элементу Learner-Type.");

                // Находим кнопку редактирования аккаунта в текущей строке
                WebElement editBtn = row.findElement(By.cssSelector("i.icon-pencil.icon-grid"));
                wait1.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
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

    /**
     * Добавляет нового пользователя с неактивным статусом в системе.
     *
     * <p>Метод выполняет следующие шаги:
     * <ol>
     *   <li>Переходит на страницу пользователей.</li>
     *   <li>Открывает форму добавления нового пользователя.</li>
     *   <li>Вводит данные нового пользователя, переданные в параметре {@code user}.</li>
     *   <li>Снимает флаг активности пользователя, делая его неактивным.</li>
     *   <li>Подтверждает добавление нового пользователя.</li>
     *   <li>Возвращается на страницу списка пользователей.</li>
     * </ol>
     *
     * @param user объект класса {@link User}, содержащий данные для нового пользователя
     * @return объект {@link UserPage}, представляющий текущую страницу пользователей после добавления
     */
    public UserPage addUserNotActive(User user) {
        clickUsers();
        webElementActions
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(this.tickActive)
                .click(this.addUserButton)
                .click(this.users);
        return new UserPage();
    }

    private UserPage clickUsers() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(), 'Users')])[1]"))).click();
        return new UserPage();
    }

    /**
     * Сортирует список пользователей по имени.
     *
     * <p>Метод выполняет следующие шаги:
     * <ol>
     *   <li>Открывает панель управления пользователями.</li>
     *   <li>Нажимает на кнопку сортировки по имени.</li>
     *   <li>Приостанавливает выполнение на 2 секунды для ожидания завершения сортировки.</li>
     * </ol>
     *
     * <p><b>Примечание:</b> Использование метода {@code sleep} для ожидания может быть заменено
     * на более надежные методы явных ожиданий через {@link org.openqa.selenium.support.ui.WebDriverWait}.
     */
    public void sortUsersByName() {
        webElementActions.click(UsersDashboard);
        userSortClick.click();
        sleep(2000);
    }

    /**
     * Приостанавливает выполнение потока на указанное количество миллисекунд.
     *
     * @param millis время задержки в миллисекундах
     *
     * <p>В случае прерывания сна, поток будет повторно прерван с помощью {@code Thread.currentThread().interrupt()}.</p>
     */
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Извлекает список имен пользователей из таблицы на странице.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Находит все строки таблицы пользователей по селектору {@code table#tl-users-grid tbody tr}.</li>
     *   <li>Преобразует каждую строку в список ячеек {@code <td>}.</li>
     *   <li>Фильтрует строки, которые содержат как минимум две ячейки (чтобы избежать ошибок доступа к несуществующим ячейкам).</li>
     *   <li>Извлекает текст из второй ячейки (предположительно имя пользователя), убирает лишние пробелы и добавляет в список.</li>
     * </ol>
     *
     * @return список имен пользователей, отображенных в таблице
     */
    public List<String> getUserNamesFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 2)
                .map(cells -> cells.get(1).getText().trim())
                .collect(Collectors.toList());
    }

    /**
     * Сортирует пользователей по имени и возвращает отсортированный список имен из таблицы.
     *
     * <p>Метод сначала вызывает {@link #sortUsersByName()}, чтобы инициировать сортировку пользователей,
     * а затем извлекает обновленный список имен пользователей с помощью метода {@link #getUserNamesFromTable()}.</p>
     *
     * @return отсортированный список имен пользователей
     */
    public List<String> getSortedUserNamesFromTable() {
        sortUsersByName();
        return getUserNamesFromTable();
    }


    public void sortUsersByEmail() {
        webElementActions.click(UsersDashboard);
        emailSortClock.click();
        sleep(2000); // Ждем 2 секунды, чтобы таблица успела отсортироваться
    }

    /**
     * Извлекает список адресов электронной почты пользователей из таблицы на странице.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Находит все строки таблицы пользователей по селектору {@code table#tl-users-grid tbody tr}.</li>
     *   <li>Преобразует каждую строку в список ячеек {@code <td>}.</li>
     *   <li>Фильтрует строки, которые содержат как минимум три ячейки (для доступа к колонке с email).</li>
     *   <li>Извлекает текст из третьей ячейки (индекс 2), которая содержит адрес электронной почты, убирает лишние пробелы и добавляет в список.</li>
     * </ol>
     *
     * @return список адресов электронной почты, отображенных в таблице
     */
    public List<String> getEmailsFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 3) // Проверяем наличие как минимум 3 ячеек
                .map(cells -> cells.get(2).getText().trim()) // Извлекаем email из третьей ячейки
                .collect(Collectors.toList());
    }

    /**
     * Сортирует пользователей по адресу электронной почты и возвращает отсортированный список email.
     *
     * <p>Метод сначала вызывает {@link #sortUsersByEmail()}, чтобы инициировать сортировку пользователей по email,
     * а затем извлекает обновленный список адресов электронной почты с помощью метода {@link #getEmailsFromTable()}.</p>
     *
     * @return отсортированный список адресов электронной почты пользователей
     */
    public List<String> getSortedEmailsFromTable() {
        sortUsersByEmail();
        return getEmailsFromTable();
    }

    /**
     * Сортирует пользователей по типу (User Type) в таблице.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Открывает панель с пользователями, кликая по элементу {@code UsersDashboard}.</li>
     *   <li>Инициирует сортировку, кликая по заголовку колонки с типами пользователей.</li>
     *   <li>Приостанавливает выполнение на 2 секунды, чтобы дать таблице время для обновления после сортировки.</li>
     * </ol>
     */
    public void sortUsersByType() {
        webElementActions.click(UsersDashboard);
        userTypeSortClick.click();  // Кликаем по заголовку колонки user type
        sleep(2000);  // Ждем 2 секунды, чтобы таблица успела отсортироваться
    }

    /**
     * Извлекает список типов пользователей из таблицы на странице.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Находит все строки таблицы пользователей по селектору {@code table#tl-users-grid tbody tr}.</li>
     *   <li>Преобразует каждую строку в список ячеек {@code <td>}.</li>
     *   <li>Фильтрует строки, которые содержат как минимум четыре ячейки (для доступа к колонке с типом пользователя).</li>
     *   <li>Извлекает текст из четвертой ячейки (индекс 3), которая содержит тип пользователя, убирает лишние пробелы и добавляет в список.</li>
     * </ol>
     *
     * @return список типов пользователей, отображенных в таблице
     */
    public List<String> getUserTypesFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 4) // Проверяем наличие как минимум 4 ячеек
                .map(cells -> cells.get(3).getText().trim()) // Извлекаем user type из четвертой ячейки
                .collect(Collectors.toList());
    }

    /**
     * Сортирует пользователей по типу и возвращает отсортированный список типов пользователей.
     *
     * <p>Метод сначала вызывает {@link #sortUsersByType()} для сортировки таблицы по типу пользователя,
     * а затем извлекает обновленный список типов пользователей с помощью метода {@link #getUserTypesFromTable()}.</p>
     *
     * @return отсортированный список типов пользователей
     */
    public List<String> getSortedUserTypesFromTable() {
        sortUsersByType();
        return getUserTypesFromTable();
    }

    /**
     * Сортирует пользователей по дате регистрации в таблице.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Открывает панель с пользователями, кликая по элементу {@code UsersDashboard}.</li>
     *   <li>Кликает по заголовку колонки с датой регистрации для инициирования сортировки.</li>
     *   <li>Приостанавливает выполнение на 2 секунды, чтобы таблица успела обновиться после сортировки.</li>
     * </ol>
     */
    public void sortUsersByRegistration() {
        webElementActions.click(UsersDashboard);
        registrationSortClock.click();  // Кликаем по заголовку колонки с датой регистрации
        sleep(2000);  // Ждем 2 секунды, чтобы таблица успела отсортироваться
    }

    /**
     * Извлекает список дат регистрации пользователей из таблицы.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Находит все строки таблицы пользователей по селектору {@code table#tl-users-grid tbody tr}.</li>
     *   <li>Преобразует каждую строку в список ячеек {@code <td>}.</li>
     *   <li>Фильтрует строки, которые содержат как минимум пять ячеек (для доступа к колонке с датой регистрации).</li>
     *   <li>Извлекает текст из пятой ячейки (индекс 4), которая содержит дату регистрации, убирает лишние пробелы и добавляет в список.</li>
     * </ol>
     *
     * @return список дат регистрации пользователей, отображенных в таблице
     */
    public List<String> getRegistrationsFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 5) // Проверяем наличие как минимум 5 ячеек
                .map(cells -> cells.get(4).getText().trim()) // Извлекаем дату регистрации из пятой ячейки
                .collect(Collectors.toList());
    }

    /**
     * Сортирует пользователей по дате регистрации и возвращает отсортированный список дат.
     *
     * <p>Метод сначала вызывает {@link #sortUsersByRegistration()} для сортировки таблицы по дате регистрации,
     * а затем извлекает обновленный список дат с помощью метода {@link #getRegistrationsFromTable()}.</p>
     *
     * @return отсортированный список дат регистрации пользователей
     */
    public List<String> getSortedRegistrationsFromTable() {
        sortUsersByRegistration();
        return getRegistrationsFromTable();
    }

    /**
     * Пытается добавить пользователя с некорректными данными.
     *
     * <p>Метод выполняет следующие действия:
     * <ol>
     *   <li>Кликает по панели управления пользователями ({@code UsersDashboard}).</li>
     *   <li>Открывает форму добавления нового пользователя, кликая по кнопке {@code addUser}.</li>
     *   <li>Вызывает метод {@link #enterUserData(User)}, чтобы ввести данные пользователя.</li>
     *   <li>Пытается сохранить введенные данные, кликая по кнопке {@code addUserButton}.</li>
     * </ol>
     *
     * <p><b>Примечание:</b> Этот метод предназначен для проверки обработки некорректных данных валидацией формы.
     * Ожидается, что пользователь не будет успешно добавлен, и появятся сообщения об ошибках.</p>
     *
     * @param user объект класса {@link User}, содержащий данные для ввода в форму
     * @return новый экземпляр страницы пользователя {@link UserPage} после попытки добавления
     */
    public UserPage addUserIncorrect(User user) {
        webElementActions
                .click(UsersDashboard)
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(this.addUserButton);

        return new UserPage();
    }

}