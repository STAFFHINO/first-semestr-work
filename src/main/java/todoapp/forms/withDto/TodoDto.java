package todoapp.forms.withDto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime created_date;
    private Integer id_user;
}