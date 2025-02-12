package talentLMS.page.users;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//*[@id=\"tl-users-grid\"]/tbody/tr[1]/td[7]/div/div/i[3]")
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

    @FindBy(xpath = "(//span/span[@class='help-inline'])")
    public WebElement uniqueEmailError;

    @FindBy(xpath = "((//td[@class=' tl-align-center tl-table-operations-cell']/div[@class='tl-table-operations-trigger touchable'])[2])")
    public WebElement operationIcon;

    @FindBy(xpath = "//span[contains(text(),'inactive')]")
    public WebElement userIsNotActiveErrorText;

    @FindBy(xpath = "//span/span[contains(@class, 'help-inline")
    public WebElement randomUserWithoutFirstNameErrorText;

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


    @Step("Метод добавления нового пользователя.")
    public UserPage addNewUser(User user) {
        webElementActions
                .click(this.UsersDashboard)
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(this.addUserButton);
        return new UserPage();
    }


    @Step("Метод редактирования данных существующего пользователя.")
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


    @Step("Метод добавления пользователя с уникальным email.")
    public UserPage uniqueEmail(User user) {
        webElementActions
                .click(UsersDashboard)
                .click(this.addUser);
        enterUserData(user);  // Ввод данных пользователя
        webElementActions
                .click(addUserButton);
        return new UserPage();
    }


    @Step("Метод для получения списка пользователей с веб-страницы из таблицы.")
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


    @Step("Метод для входа в аккаунт пользователя по имени.")
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


    @Step("Метод для редактирования аккаунта пользователя по имени.")
    public void editAccount(String username, User user) {

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


    @Step("Добавляет нового пользователя с неактивным статусом в системе.")
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
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(35));

        try {
            // Сначала проверяем видимость элемента
            WebElement usersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='tl-admin-users']//a[contains(normalize-space(.), 'Users')]")
            ));

            // Проверяем, что элемент действительно кликабелен
            wait.until(ExpectedConditions.elementToBeClickable(usersLink)).click();

        } catch (TimeoutException e) {
            // Используем JavaScript клик, если стандартный клик не срабатывает
            System.out.println("Элемент 'Users' не стал кликабельным, пробуем кликнуть через JavaScript.");
            WebElement usersLink = Driver.getDriver().findElement(
                    By.xpath("//div[@id='tl-admin-users']//a[contains(normalize-space(.), 'Users')]")
            );
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", usersLink);
        }

        return new UserPage();
    }


    @Step("Сортирует список пользователей по имени.")
    public void sortUsersByName() {
        webElementActions.click(UsersDashboard);
        userSortClick.click();
        sleep(2000);
    }


    @Step("Приостанавливает выполнение потока на указанное количество миллисекунд.")
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    @Step("Извлекает список имен пользователей из таблицы на странице.")
    public List<String> getUserNamesFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 2)
                .map(cells -> cells.get(1).getText().trim())
                .collect(Collectors.toList());
    }


    @Step("Сортирует пользователей по имени и возвращает отсортированный список имен из таблицы.")
    public List<String> getSortedUserNamesFromTable() {
        sortUsersByName();
        return getUserNamesFromTable();
    }


    public void sortUsersByEmail() {
        webElementActions.click(UsersDashboard);
        emailSortClock.click();
        sleep(2000); // Ждем 2 секунды, чтобы таблица успела отсортироваться
    }


    @Step("Извлекает список адресов электронной почты пользователей из таблицы на странице.")
    public List<String> getEmailsFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 3) // Проверяем наличие как минимум 3 ячеек
                .map(cells -> cells.get(2).getText().trim()) // Извлекаем email из третьей ячейки
                .collect(Collectors.toList());
    }


    @Step("Сортирует пользователей по адресу электронной почты и возвращает отсортированный список email.")
    public List<String> getSortedEmailsFromTable() {
        sortUsersByEmail();
        return getEmailsFromTable();
    }


    @Step("Сортирует пользователей по типу (User Type) в таблице.")
    public void sortUsersByType() {
        webElementActions.click(UsersDashboard);
        userTypeSortClick.click();  // Кликаем по заголовку колонки user type
        sleep(2000);  // Ждем 2 секунды, чтобы таблица успела отсортироваться
    }


    @Step("Извлекает список типов пользователей из таблицы на странице.")
    public List<String> getUserTypesFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 4) // Проверяем наличие как минимум 4 ячеек
                .map(cells -> cells.get(3).getText().trim()) // Извлекаем user type из четвертой ячейки
                .collect(Collectors.toList());
    }


    @Step("Сортирует пользователей по типу и возвращает отсортированный список типов пользователей.")
    public List<String> getSortedUserTypesFromTable() {
        sortUsersByType();
        return getUserTypesFromTable();
    }


    @Step("Сортирует пользователей по дате регистрации в таблице.")
    public void sortUsersByRegistration() {
        webElementActions.click(UsersDashboard);
        registrationSortClock.click();  // Кликаем по заголовку колонки с датой регистрации
        sleep(2000);  // Ждем 2 секунды, чтобы таблица успела отсортироваться
    }


    @Step("Извлекает список дат регистрации пользователей из таблицы.")
    public List<String> getRegistrationsFromTable() {
        return Driver.getDriver().findElements(By.cssSelector("table#tl-users-grid tbody tr"))
                .stream()
                .map(row -> row.findElements(By.cssSelector("td")))
                .filter(cells -> cells.size() >= 5) // Проверяем наличие как минимум 5 ячеек
                .map(cells -> cells.get(4).getText().trim()) // Извлекаем дату регистрации из пятой ячейки
                .collect(Collectors.toList());
    }


    @Step("Сортирует пользователей по дате регистрации и возвращает отсортированный список дат.")
    public List<String> getSortedRegistrationsFromTable() {
        sortUsersByRegistration();
        return getRegistrationsFromTable();
    }


    @Step("Пытается добавить пользователя с некорректными данными.")
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