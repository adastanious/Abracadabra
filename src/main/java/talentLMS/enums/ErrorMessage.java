package talentLMS.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage{
    CATEGORIES_ADD_NAME_LENGTH_MORE80("'Name' cannot exceed 80 characters"),
    CATEGORIES_PRICE_MESSAGE("This is not a valid 'Price'"),
    CATEGORIES_ADD_NAME_LENGTH_LESS1("'Name' is required"),
    USER_TYPES_DELETE("You must specify a replacement user type"),
    USER_TYPES_NOT_CORRECT("The assigned default role is not available for this user type");

    ;
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
