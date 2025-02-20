package talentLMS.entity;

import lombok.ToString;
import talentLMS.enums.Recipient;
import talentLMS.enums.Event;
import lombok.Data;

@Data
public class Notification {
    public Notification(String name, Event event, Recipient recipient, String body, boolean status){
        setName(name);
        setEvent(event);
        setRecipient(recipient);
        setBody(body);
        setActive(status);
    }

    public Notification(String name, Event event, Recipient recipient, String body){
        setName(name);
        setEvent(event);
        setRecipient(recipient);
        setBody(body);
    }

    public Notification(String name, Event event, Recipient recipient){
        setName(name);
        setEvent(event);
        setRecipient(recipient);
        setBody(body);
    }
    public Notification(Notification notification){
        setName(notification.getName());
        setEvent(notification.getEvent());
        setRecipient(notification.getRecipient());
        setBody(notification.getBody());
        setActive(notification.isActive);
    }
    private String name;
    private Event event;
    private Recipient recipient;
    private String body;
    private boolean isActive;
}
