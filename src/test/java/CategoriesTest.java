import org.testng.annotations.Test;
import talentLMS.page.BasePage;

import static talentLMS.enams.Role.ADMINISTRATOR;

public class CategoriesTest extends BaseTest {
    @Test
    public void categoriesTest(){
        // Навести на элемент роли пользовотеля и нажать роль администратора
       component.selectRole(ADMINISTRATOR);



    }
}
