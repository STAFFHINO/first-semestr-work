package todoapp.forms.withDto;

import todoapp.classes.Status;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MissionDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime created_date;
    private LocalDateTime deadline;
    private Status status;
    private Integer id_todo;
}