package entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User {
    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private int age;
    private String city;
    private String role;
}
