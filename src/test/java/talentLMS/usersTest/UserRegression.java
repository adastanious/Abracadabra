package talentLMS.usersTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import talentLMS.BaseTest;
import talentLMS.driver.Driver;
import talentLMS.entity.UserEntity;

import java.time.Duration;
import java.util.List;

public class UserRegression extends BaseTest {
    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://abracadabra.talentlms.com/dashboard");
    }

    @Test(groups = {"Regression"}, description = "Тест получает список всех пользователей и выводит его в консоль.", priority = 1)
    public void getListOfUsersTest() throws InterruptedException {

        List<UserEntity> userEntities = userPage.getUserFromTable();
        for (UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }
        Thread.sleep(5000);
    }

    @Test(groups = {"Regression"}, description = "Тест проверяет редактирование данных учетной записи пользователя.", priority = 2)
    public void editAccountTest() {
        userPage.editAccount("AdiAl", randomUserGenerator.randomUser());
    }

    @Test(groups = {"Regression"}, description = "Тест проверяет корректность сортировки пользователей по имени.", priority = 3)
    public void testUserSortingByName() {

        List<String> beforeSorting = userPage.getUserNamesFromTable();
        List<String> afterSorting = userPage.getSortedUserNamesFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок пользователей не изменился после сортировки!");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет корректность сортировки пользователей по email.", priority = 4)
    public void testSortByEmail() {
        List<String> beforeSorting = userPage.getEmailsFromTable();
        List<String> afterSorting = userPage.getSortedEmailsFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок email не изменился после сортировки!");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет корректность сортировки пользователей по типу учетной записи.", priority = 5)
    public void testSortByUserType() {
        List<String> beforeSorting = userPage.getUserTypesFromTable();
        List<String> afterSorting = userPage.getSortedUserTypesFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок user type не изменился после сортировки!");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет корректность сортировки пользователей по дате регистрации.", priority = 6)
    public void testSortByRegistration() {

        List<String> beforeSorting = userPage.getRegistrationsFromTable();
        List<String> afterSorting = userPage.getSortedRegistrationsFromTable();

        Assert.assertNotEquals(beforeSorting, afterSorting, "Порядок registration не изменился после сортировки!");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при попытке добавить пользователя без имени.", priority = 7)
    public void randomUserWithoutFirstNameTest() {

        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutFirstName());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[contains(@class, 'help-inline')]")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'First name' is required");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при попытке добавить пользователя без фамилии.", priority = 8)
    public void randomUserWithoutLastNameTest() {

        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutLastName());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Last name' is required");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при попытке добавить пользователя без email.", priority = 9)
    public void randomUserWithoutEmailTest() {

        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutEmail());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Email address' is required");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при добавлении пользователя с некорректным паролем.", priority = 10)
    public void randomUserWithoutIncorrectPasswordTest() {

        userPage.addUserIncorrect(randomUserGenerator.randomUserWithIncorrectPassword());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Password' is not strong enough (should have at least (1) upper case letter, at least (1) lower case letter, at least (1) number, at least (8) characters in length)");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет корректность цвета текста сообщения об ошибке.", priority = 11)
    public void checkErrorColorTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserWithoutFirstName());

        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'help-inline')]")));

        Assert.assertEquals(errorElement.getCssValue("color").trim(), "rgba(185, 74, 72, 1)", "Цвет ошибки не совпадает!");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при превышении длины имени более 50 символов.", priority = 12)
    public void randomUserFirstNameExceed50CharactersTest() {

        userPage.addUserIncorrect(randomUserGenerator.randomUserFirstNameXceed50Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'First name' cannot exceed 50 characters");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при превышении длины фамилии более 50 символов.", priority = 13)
    public void randomUserNameExceed50CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserLastNameXceed50Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Last name' cannot exceed 50 characters");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при превышении длины email более 150 символов.", priority = 14)
    public void randomEmailExceed150CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserEmailXceed150Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Email address' cannot exceed 150 characters");
    }


    @Test(groups = {"Regression"}, description = "Тест проверяет сообщение об ошибке при превышении длины пароля более 30 символов.", priority = 15)
    public void randomPasswordExceed30CharactersTest() {
        userPage.addUserIncorrect(randomUserGenerator.randomUserPasswordXceed30Characters());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isRequired = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/span[@class='help-inline']")));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "'Password' cannot exceed 30 characters");
    }
}
