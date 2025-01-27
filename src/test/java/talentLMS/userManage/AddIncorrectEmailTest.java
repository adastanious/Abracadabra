package talentLMS.userManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddIncorrectEmailTest extends AddNewUserTest {

    @Test (priority = 2)
    public void addIncorrectEmailTest() {
        driver.get("https://abracadabra.talentlms.com/user/create");
        userPage.addNewUserNoCorrect(randomUser,"alisa123.com");

        WebElement isRequired = driver.findElement(By.xpath("//div[@class='span8']/child::*[3]//span[@class='help-block']"));
        String actualResult = isRequired.getText();
        Assert.assertEquals(actualResult, "This is not a valid 'Email address'");
    }
}
