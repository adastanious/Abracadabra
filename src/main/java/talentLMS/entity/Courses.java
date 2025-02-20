package talentLMS.entity;

import lombok.Data;
@Data

public class Courses {


    private String courseName = "Digital123"+ System.currentTimeMillis();
    private String category = "SpaceX" + System.currentTimeMillis();
    private String description = "Проверка интерфейса и UX\n" +
            "Шаги:\n" +
            "Проверьте расположение кнопок и доступность элементов на странице создания курса.\n" +
            "Убедитесь, что интерфейс удобен и все поля имеют соответствующие подсказки.\n" +
            "Ожидаемый результат:\n" +
            "Интерфейс интуитивно понятен, элементы управления работают корректно.\n" +
            "6. Тестирование прав доступа\n" +
            "Шаги:\n" +
            "Попробуйте создать курс с учетной записью, не имеющей прав администратора (например, учетная запись студента или преподавателя).\n" +
            "Ожидаемый результат:\n" +
            "Учетная запись без прав администратора не может создать курс.\n" +
            "После выполнения этих тестов вы сможете оценить, насколько корректно реализован данный" + System.currentTimeMillis();


    private String courseName1 = "winter" + System.currentTimeMillis();
    private String category1 = "simple" + System.currentTimeMillis();
    private String description1 = "123" + System.currentTimeMillis();

}
