package talentLMS.enums;

public enum EventsEngineNavigationBar {
    NOTIFICATIONS("Notifications"),
    HISTORY("History"),
    PENDINGNOTIFICATIONS("Pending notifications");

    private final String currentSection;

    EventsEngineNavigationBar(String section) {
        this.currentSection = section;
    }

    public String getCurrentSection() {
        return currentSection;
    }
}
