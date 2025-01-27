package talentLMS.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Sections {
    private String users = "Users";
    private String courses = "Courses";
    private String categories = "Categories";
    private String groups = "Groups";
    private String branches = "Branches";
    private String eventsEngine = "Events engine";
    private String userTypes = "User types";
    private String importExport = "Import - Export";
    private String reports = "Reports";
    private String accountsSettings = "Account & Settings";
}
