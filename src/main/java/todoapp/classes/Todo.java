package todoapp.classes;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Todo {
    private Integer id;
    private String name;
    private String description;
    private Integer id_user;
    private LocalDateTime created_date;
}
