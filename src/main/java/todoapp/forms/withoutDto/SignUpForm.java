package todoapp.forms.withoutDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpForm {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
