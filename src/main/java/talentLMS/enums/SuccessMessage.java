package talentLMS.enums;

public enum SuccessMessage {
    CATEGORIES_ADD_MESSAGE("Success! New category created."),
    CATEGORIES_CHANGE_MESSAGE("Category updated successfully");



    String message;
    SuccessMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;

    }
}
