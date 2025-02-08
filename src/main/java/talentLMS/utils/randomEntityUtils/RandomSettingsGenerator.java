package talentLMS.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import lombok.Getter;
import talentLMS.entity.Settings;

@Getter

/**
 @author Dastan Aidarov
 */

public class RandomSettingsGenerator {
    static Faker faker = new Faker();

    public String randomSiteName() {
        return faker.funnyName().name();
    }

    /**
     * Генерирует случайное название сайта длиной более 40 символов.
     * Использует слова из Lorem Ipsum, добавляя их в строку, пока длина не превысит 42 символа.
     * @return случайное название сайта
     */
    public String randomSiteNameOver40Chars() {
        StringBuilder randomText = new StringBuilder();
        while (randomText.length() < 42) {
            randomText.append(faker.lorem().word()).append(" ");
        }
        return randomText.toString().trim();
    }

    /**
     * Генерирует случайное описание сайта длиной до 255 символов.
     * Использует случайные абзацы Lorem Ipsum.
     * @return случайное описание сайта
     */
    public String randomSiteDescription() {
        StringBuilder randomText = new StringBuilder();
        while (randomText.length() < 200) {
            randomText.append(faker.lorem().word()).append(" ");
        }
        return randomText.toString().trim();
    }

    /**
     * Генерирует случайное описание сайта длиной более 255 символов.
     * Аналогично предыдущему методу, но длина будет не менее 257 символов.
     * @return случайное описание сайта, превышающее 255 символов
     */
    public String randomSiteDescriptionOver255Chars() {
        StringBuilder randomText = new StringBuilder();
        while (randomText.length() < 257) {
            randomText.append(faker.lorem().word()).append(" ");
        }
        return randomText.toString().trim();
    }

    public Settings randomSettings() {
        return new Settings(randomSiteName(),randomSiteDescription());
    }

}
