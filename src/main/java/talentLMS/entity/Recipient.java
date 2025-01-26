package talentLMS.entity;

public enum Recipient {
    SELECTRECIPIENT("Select recipient"),
    RELATEDUSER("Related user"),
    ACCOUNTOWNER("Account owner"),
    SUPERADMINS("SuperAdmins"),
    BRANCHADMINS("Branch admins"),
    COURSEINSTRUCTORS("Course instructors"),
    COURSELEARNERS("Course learners"),
    SPECIFICRECIPIENTS("Specific recipients");

    private final String recipient;

    Recipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }
}
