package talentLMS.entity;

import lombok.*;

import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Group {

    private String name;
    private String description;
}
