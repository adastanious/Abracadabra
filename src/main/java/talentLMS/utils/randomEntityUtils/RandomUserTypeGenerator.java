package talentLMS.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import talentLMS.entity.UserTypeEntity;

public class RandomUserTypeGenerator {

    Faker faker = new Faker();

    public String randomName() {
        return faker.name().firstName();
    }

    public UserTypeEntity randomUserType(){
        return new UserTypeEntity(randomName());
    }
}
