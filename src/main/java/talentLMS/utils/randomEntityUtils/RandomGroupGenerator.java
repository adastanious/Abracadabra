package talentLMS.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import talentLMS.entity.Group;
import java.util.Random;

public class RandomGroupGenerator {

    Faker faker = new Faker();

    public String randomName(){
        return faker.name().name();
    }

    /**
     * Генерирует случайную строку заданной длины.
     *
     * Этот метод создает строку, содержащую случайные символы из набора, включающего
     * латинские буквы (верхнего и нижнего регистра), цифры и специальные символы.
     * Длина строки определяется переданным параметром.
     *
     * @param length Длина строки, которую нужно сгенерировать.
     * @return Сгенерированная строка, содержащая случайные символы.
     */
    public String generateText(int length) {
        StringBuilder sb = new StringBuilder();  // Строим строку с использованием StringBuilder для эффективности
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?/";  // Набор символов, из которых будет формироваться строка

        Random random = new Random();  // Создаем объект Random для генерации случайных чисел
        for (int i = 0; i < length; i++) {  // Проходим по всем позициям строки
            int randomIndex = random.nextInt(characters.length());  // Генерируем случайный индекс в пределах набора символов
            sb.append(characters.charAt(randomIndex));  // Добавляем символ из набора по сгенерированному индексу
        }
        return sb.toString();  // Возвращаем строку, собранную с помощью StringBuilder
    }


    public Group randomGroup(){
        return new Group(randomName(), generateText(150));
    }
}
