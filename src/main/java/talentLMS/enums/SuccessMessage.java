package talentLMS.enums;

import lombok.Getter;

@Getter
public enum SuccessMessage {
    CATEGORIES_ADD_MESSAGE("Success! New category created."),
    CATEGORIES_CHANGE_MESSAGE("Category updated successfully");

    private String message;

    SuccessMessage(String message) {
        this.message = message;
    }
}
