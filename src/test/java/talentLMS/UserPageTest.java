package talentLMS;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.driver.Driver;
import talentLMS.entity.UserEntity;
import java.time.Duration;
import java.util.List;


public class UserPageTest extends BaseTest {


    String email = randomUser.getEmail();

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(priority = 1)
    public void addNewUserTest() {

        userPage.addNewUser(randomUser);
    }

    @Test(priority = 3)
    public void editUserTest() {
        userPage.addNewUser(randomUser);
    }

    @Test (priority = 2)
    public void fillIncorrectEmailTest() {
        userPage.userWithIncorrectEmail(randomUserGenerator.randomUserWithIncorrectEmail());

        WebElement isRequired = driver.findElement(By.xpath("(//div/div/span/span[@class='help-inline'])[1]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }

    @Test (priority = 3)
    public void editUserTest() {
        userPage.editUser(randomUserGenerator.randomUser());
    }

    @Test(priority = 4)
    public void deleteUsersTest() {
       userPage.deleteUsers(randomUserGenerator.randomUser());
    }

    @Test(priority = 5)
    public void uniqueEmailTest() {
        userPage.uniqueEmail(randomUserGenerator.emailUniquenessCheck());

        WebElement isRequired = driver.findElement(By.xpath("(//span/span[@class='help-inline'])"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "Someone is already using this email address");
    }

    @Test(priority = 6)
    public void getListOfUsersTest() throws InterruptedException {
// Вызов метода получения списка всех сотрудников и вывода на консоль
        List<UserEntity> userEntities = userPage.getUserFromTable();
        for (UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void logIntoAccountTest() {
        String testUsername = "A. Alekseevna";
        try {
            userPage.logIntoAccount(testUsername);
            System.out.println("Тест пройден: вход выполнен для пользователя: " + testUsername);

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement backToAdminBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//i[@class='icon-undo tl-icon16']")
            ));
            Assert.assertTrue(backToAdminBtn.isDisplayed(), "Кнопка 'Назад' не отображается.");
        } catch (NoSuchElementException e) {
            Assert.fail("Тест провален: " + e.getMessage());
        }
    }

    @Test(priority = 8)
    public void editAccountTest() {
        userPage.editAccount("A. Alekseevna",randomUserGenerator.randomUser());
    }

    @Test(priority = 9)
    public void addUserNotActiveTest() {
        userPage.addUserNotActive(randomUserGenerator.randomUser());

        WebElement isRequired = driver.findElement(By.xpath("//span[contains(text(),'inactive')]"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "INACTIVE");
    }

    @Test(priority = 10)
    public void testUserSortingByName() {
        List<String> beforeSorting = userPage.getUserNamesFromTable();
        List<String> afterSorting = userPage.getSortedUserNamesFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок пользователей не изменился после сортировки!");
        Assert.assertTrue(!beforeSorting.equals(afterSorting), "Сортировка изменила порядок пользователей.");
    }

    @Test(priority = 11)
    public void testSortByEmail() {
        List<String> beforeSorting = userPage.getEmailsFromTable();
        List<String> afterSorting = userPage.getSortedEmailsFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок email не изменился после сортировки!");
        Assert.assertTrue(!beforeSorting.equals(afterSorting), "Сортировка изменила порядок email.");
    }

    @Test(priority = 12)
    public void testSortByUserType() {
        List<String> beforeSorting = userPage.getUserTypesFromTable();
        List<String> afterSorting = userPage.getSortedUserTypesFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок user type не изменился после сортировки!");
        Assert.assertTrue(!beforeSorting.equals(afterSorting), "Сортировка изменила порядок user type.");
    }

    @Test(priority = 13)
    public void testSortByRegistration() {
        List<String> beforeSorting = userPage.getRegistrationsFromTable();
        List<String> afterSorting = userPage.getSortedRegistrationsFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок registration не изменился после сортировки!");
        Assert.assertTrue(!beforeSorting.equals(afterSorting), "Сортировка изменила порядок registration.");
    }

    @Test(priority = 14)
    public void randomUserWithoutFirstNameTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutFirstName());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[contains(@class, 'help-inline')]")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'First name' is required");
    }

    @Test(priority = 15)
    public void randomUserWithoutLastNameTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutLastName());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Last name' is required");
    }

    @Test(priority = 16)
    public void randomUserWithoutEmailTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutEmail());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Email address' is required");
    }

    @Test(priority = 17)
    public void randomUserWithoutIncorrectPasswordTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserWithIncorrectPassword());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Password' is not strong enough (should have at least (1) upper case letter, at least (1) lower case letter, at least (1) number, at least (8) characters in length)");
    }

    @Test(priority = 18)
    public void checkErrorColorTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutFirstName());

        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'help-inline')]")));

        Assert.assertEquals(errorElement.getCssValue("color").trim(), "rgba(185, 74, 72, 1)", "Цвет ошибки не совпадает!");
    }

    @Test(priority = 19)
    public void randomUserFirstNameExceed50CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserFirstNameXceed50Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'First name' cannot exceed 50 characters");
    }

    @Test(priority = 20)
    public void randomUserNameExceed50CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserLastNameXceed50Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Last name' cannot exceed 50 characters");
    }

    @Test(priority = 21)
    public void randomEmailExceed150CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserEmailXceed150Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Email address' cannot exceed 150 characters");
    }

    @Test(priority = 22)
    public void randomPasswordExceed30CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserPasswordXceed30Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Password' cannot exceed 30 characters");
    }
}