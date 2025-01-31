package talentLMS.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import talentLMS.entity.UserType;

public class RandomUserTypeGenerator {

    Faker faker = new Faker();

    public String randomName() {
        return faker.name().firstName();
    }

    public UserType randomUserType(){
        return new UserType(randomName());
    }
}
