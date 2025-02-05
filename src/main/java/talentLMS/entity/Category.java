package talentLMS.entity;

import lombok.Data;

@Data
public class Category {
    /**
     @author Turan
     */
    // переменные для создания категорий
    private String correctCategoryName = "Digital Nomad1";
    private String correctPrice= "50";
    private String correctCategoryName2 = "Digital Nomad2";
    private String correctPrice2= "100";
    //не правельные данные
    private String incorrectCategoryName = "12345678901234567890qwertyuiop[asdfghjkl;zxcvjkuhkvk1234567890qwertyvkvkvv,hjbnm,";
    private String incorrectPrice = "10000000000000000";
    // список из категорий
    private String NAME;

    //для таблицыы
    public Category(String name) {
        this.NAME = name;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "EmployeeEntity: " + NAME ;
    }
}