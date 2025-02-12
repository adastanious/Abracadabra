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

    public User randomUserWithoutFirstName(){
        return new User("",randomLastName(), randomEmail(), randomUserName(), randomPassword());
    }

    public User randomUserWithoutLastName(){
        return new User(randomFirstName(), " ", randomEmail(), randomUserName(), randomPassword());
    }

    public User randomUserWithoutEmail(){
        return new User(randomFirstName(), randomLastName(), " ", randomUserName(), randomPassword());
    }

    public User randomUserWithIncorrectPassword(){
        return new User(randomFirstName(), randomLastName(), randomEmail(), randomUserName(),"123456");
    }

    public User randomUserFirstNameXceed50Characters(){
        return new User("лвсщцушосссссссьчбфддддбйцщлвзцуусщтывстщывьсзщцзцч", randomLastName(), randomEmail(), randomUserName(),randomPassword());
    }

    public User randomUserLastNameXceed50Characters(){
        return new User(randomFirstName(), "лвсщцушосссссссьчбфддддбйцщлвзцуусщтывстщывьсзщцзцч", randomEmail(), randomUserName(), randomPassword());
    }

    public User randomUserEmailXceed150Characters(){
        return new User(randomFirstName(), randomLastName(), "lorraine.friesensndcdnpinsdpin idnbeirboadpfolm xc; l]s[plverkboad nk;cv, zlvm lfkn v mzlkn m oogf kngf cv ,x';fvkpjbsfgn ;cv ,cm kxjbn km lfk@hotmail.com", randomUserName(), randomPassword());
    }

    public User randomUserPasswordXceed30Characters(){
        return new User(randomFirstName(), randomLastName(), randomEmail(), randomUserName(), "123456987856412365498232Dsdknssdrhivkhb!@");
    }

    public User randomUser1(){
        return new User(randomFirstName(), randomLastName(), " ", randomUserName(), randomPassword());
    }

}