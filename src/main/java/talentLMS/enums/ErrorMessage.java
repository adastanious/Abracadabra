package talentLMS.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage{
    CATEGORIES_ADD_NAME_LENGTH_MORE80("'Name' cannot exceed 80 characters"),
    CATEGORIES_PRICE_MESSAGE("This is not a valid 'Price'"),
    CATEGORIES_ADD_NAME_LENGTH_LESS1("'Name' is required")

    ;
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
