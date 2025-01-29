package talentLMS.entity;

import lombok.Data;

@Data
public class Category {
    /**
     @author Turan
     */
    // переменные для создания категорий
    private String categoryName = "Digital Nomad1";
    private String price= "50";
    private String categoryName2 = "Digital Nomad2";
    private String price2= "100";
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