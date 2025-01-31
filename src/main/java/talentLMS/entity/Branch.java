package talentLMS.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Branch {

    private String name;
    private String title;
    private String description;
}
