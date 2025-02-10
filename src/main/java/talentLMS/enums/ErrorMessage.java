package talentLMS.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage{
    CATEGORIES_ADD_NAME_LENGTH_MORE80("'Name' cannot exceed 80 characters"),
    CATEGORIES_PRICE_MESSAGE("This is not a valid 'Price'"),
    CATEGORIES_ADD_NAME_LENGTH_LESS1("'Name' is required"),

    FILE_TYPE_NOT_ALLOWED("File type not allowed"),  // Ошибка при загрузке неподдерживаемого файла
    IMPORT_BUTTON_DISABLED("Import button should be disabled without a file"),  // Ошибка, если кнопка активна без файла
    IMPORT_BUTTON_DISABLED_DURING_UPLOAD("Import button should be disabled during file upload");  // Ошибка, если кнопка активна во время загрузки

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
