package talentLMS.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import talentLMS.entity.User;


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

    public String randomPassword() {
        String password;
        do {
            password = faker.internet().password(8, 16, true, true, true);
        } while (!isValidPassword(password));
        return password;
    }

    private boolean isValidPassword(String password) {
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}");
    }

    public User randomUser(){
        return new User(randomFirstName(), randomLastName(), randomEmail(), randomUserName(), randomPassword());
    }

    public User randomUserWithIncorrectEmail(){
        return new User(randomFirstName(), randomLastName(), "Alisa.com", randomUserName(), randomPassword());
    }

    public User emailUniquenessCheck(){
        return new User(randomFirstName(), randomLastName(), "das.digital.nomads@gmail.com", randomUserName(), randomPassword());
    }

}