package talentLMS.enums;

import lombok.Getter;
@Getter

/**
 @author Dastan Aidarov
 */

public enum AdminSection {
    USERS ("Users"),
    COURSES ("Courses"),
    CATEGORIES ("Categories"),
    GROUPS ("Groups"),
    BRANCHES ("Branches"),
    EVENTS_ENGINE ("Events engine"),
    USER_TYPES ("User types"),
    IMPORT_EXPORT ("Import - Export"),
    REPORTS ("Reports"),
    ACCOUNT_SETTINGS ("Account & Settings");

    private String section;

    AdminSection (String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return section;
    }
}
