package talentLMS.enums;

import lombok.Getter;

public enum Role {
    ADMINISTRATOR("administrator"),
    INSTRUCTOR("instructor"),
    LEARNER("learner"),
    ;
    @Getter
    String role;

    Role (String role){
        this.role = role;
    }

}
