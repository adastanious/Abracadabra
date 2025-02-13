package talentLMS.page.eventsEngine.tools;

import java.util.UUID;

public class RandomStringGenerator {
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(UUID.randomUUID().toString().replace("-", ""));
        }
        return sb.substring(0, length);
    }
}
