package todoapp.forms.withDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
}