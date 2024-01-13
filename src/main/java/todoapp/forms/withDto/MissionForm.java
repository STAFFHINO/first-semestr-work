package todoapp.forms.withDto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class MissionForm {
    private Integer id;
    private String name;
    private String description;
    private String deadline;
    private String status;
    private String created_date;
    private Integer id_todo;
}