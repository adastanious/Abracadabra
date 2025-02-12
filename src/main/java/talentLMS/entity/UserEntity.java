package talentLMS.entity;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class UserEntity {

    private String user;
    private String email;
    private String usertype;
    private String registration;
}