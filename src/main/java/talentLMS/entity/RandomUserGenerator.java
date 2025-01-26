package talentLMS.entity;

import com.github.javafaker.Faker;
import java.util.Random;

public class RandomUserGenerator {
    Faker faker = new Faker();

    public String randomFirstName() {
        return faker.name().firstName();
    }

    public String randomLastName() {
        return faker.name().lastName();
    }

    public String randomEmail() {
        return faker.internet().emailAddress();
    }

    public String randomUserName() {
        return faker.name().username();
    }

    // метод для генерации корректного пароля
    public String randomPassword() {
        String password;
        do {
            password = faker.internet().password(8, 16, true, true, true);
        } while (!isValidPassword(password));
        return password;
    }

    private boolean isValidPassword(String password) {
        // Исправленное регулярное выражение
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}");
    }

  public User randomUser(){
        return new User(randomFirstName(), randomLastName(), randomEmail(), randomUserName(), randomPassword(), generateText(100));
    }

    // Метод для генерации случайного текста длиной до 800 символов
    public String generateText(int length) {
        StringBuilder sb = new StringBuilder();  // Строка, в которую будем добавлять символы
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?/";  // Набор символов

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());  // Выбираем случайный индекс из набора
            sb.append(characters.charAt(randomIndex));  // Добавляем выбранный символ в строку
        }
        return sb.toString();  // Возвращаем сгенерированный текст
    }

}
