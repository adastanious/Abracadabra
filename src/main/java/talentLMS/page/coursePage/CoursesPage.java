package talentLMS.page.coursePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import talentLMS.entity.Courses;
import talentLMS.page.BasePage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoursesPage extends BasePage {

    /**
     * Mirat
     */

    @FindBy(xpath = "//input[@name='name']")
    WebElement courseName;

    @FindBy(xpath = "//span[@class='select2-chosen' and contains(text(), 'category')]")
    WebElement category;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    WebElement categoryAdd;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    WebElement categoryClick;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']//textarea[@name='description']")
    WebElement description;

    @FindBy(xpath = "//label[@class='checkbox inline' and contains(text(), 'Active')]")
    WebElement active;

    @FindBy(xpath = "//input[@name='submit_course']")
    WebElement submit;

    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Add course')]")
    WebElement addCourse;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    WebElement deleteCourseMain;

    @FindBy(xpath = "//li//a[@data-toggle='dropdown']//span")
    WebElement switchAdmin;

    @FindBy(xpath = "//li[@id='tl-trainer-option' and normalize-space(.)='Instructor']")
    WebElement instructorButton;

    @FindBy(xpath = "//li[@id='tl-learner-option' and normalize-space(.)='Learner']")
    WebElement learnerButton;

    @FindBy(xpath = "//a[@title='Courses']")
    WebElement clickCourse;

    /**
     * Метод для создания нового курса
     * Заполняет все необходимые поля и нажимает кнопку "Создать курс".
     */

    public CoursesPage addCourses(Courses courses, String course) {
        webElementActions.click(addCourse)
                .sendKeys(this.courseName, course)
                .click(category)
                .sendKeys(this.categoryAdd, courses.getCategory())
                .click(categoryClick)
                .sendKeys(this.description, courses.getDescription())
                .click(active)
                .click(submit);
        return new CoursesPage();
    }

    /**
     * Переход на страницу со списком курсов
     */

    public CoursesPage goToCourse(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webElementActions.click(clickCourse);
        return new CoursesPage();
    }

    /**
     * Поиск курса по названию
     * Ищет курс в таблице и кликает по нему для редактирования.
     */

    public void findCourseByName(String courseName) {
        WebElement course = driver.findElement(By.xpath("//span[@class='tl-formatted-course-name']"));
        course.click();
    }

    /**
     * Обновление названия курса
     * Очищает поле и вводит новое имя курса.
     */

    public void updateCourseName(String name) {
        WebElement editName = driver.findElement(By.xpath("//input[@name='name']"));
        editName.clear();
        editName.sendKeys(name);
    }

    /**
     *  Сохранение изменений курса
     */

    public void savesChanges() {
        webElementActions.click(submit);
    }

    /**
     *  Удаление курса по названию
     * - Наводит курсор на строку с курсом
     * - Кликает на кнопку удаления
     * - Подтверждает удаление
     * - Переключает роли для проверки удаления у инструктора и студента
     */

    public CoursesPage deleteCourse(String courseName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Находим строку с курсом
        WebElement courseRow = driver.findElement(By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]"));
        webElementActions.moveToElement(courseRow);

        // Ждем появления кнопки удаления
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]//i[@alt='Delete']")
        ));
        webElementActions.click(deleteButton);
        webElementActions.click(deleteCourseMain);

        // Ждем, пока курс исчезнет из списка
        webElementActions.moveToElement(courseRow);
        webElementActions.moveToElement(switchAdmin).click(instructorButton);
        webElementActions.moveToElement(switchAdmin).click(learnerButton);

        return new CoursesPage();
    }

    /**
     * Проверка, существует ли курс
     * Возвращает `true`, если курс найден, иначе `false`.
     */

    public boolean isCoursePresent(String courseName) {
        List<WebElement> courses = driver.findElements(By.xpath("//tr[td//span[contains(text(), '" + courseName + "')]]"));
        return !courses.isEmpty();
    }

    /**
     * Получение списка всех курсов
     * Собирает названия всех курсов, доступных на странице.
     */

    public List<String> getAllCourses(){
        List<WebElement> courseElements = driver.findElements(By.xpath("//tr[td//span[@class='tl-formatted-course-name']]"));
        List<String> courseName = new ArrayList<>();
        for (WebElement course : courseElements){
            courseName.add(course.getText().trim());
        }
        return courseName;
    }

    /**
     * Генерация случайной строки
     * Используется для создания длинных имен курсов.
     */

    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }





}
