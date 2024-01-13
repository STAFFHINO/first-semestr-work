package todoapp.classes;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Builder
public class Mission {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime created_date;
    private LocalDateTime deadline;
    private Status status;
    private Integer id_todo;
}
