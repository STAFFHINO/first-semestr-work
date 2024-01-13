package todoapp.classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String password_hash;
}
