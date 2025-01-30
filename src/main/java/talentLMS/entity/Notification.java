package talentLMS.entity;

import talentLMS.enums.Recipient;
import talentLMS.enums.Event;
import lombok.Data;

@Data
public class Notification {
    public Notification(String name, Event event, Recipient recipient, String body){
        setName(name);
        setEvent(event);
        setRecipient(recipient);
        setBody(body);
    }
    private String name;
    private Event event;
    private Recipient recipient;
    private String body;
}
