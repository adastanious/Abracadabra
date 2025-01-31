package talentLMS.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import talentLMS.entity.Branch;
import java.util.Random;

public class RandomBranchGenerator {

    Faker faker = new Faker();

    public String randomName() {
        return faker.name().name();
    }

    public String randomTitle() {
        return faker.name().title();
    }

    public String generateDescription(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?/";

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }

    public Branch randomBranch(){
        return new Branch(randomName(), randomTitle(), generateDescription(150));
    }
}
