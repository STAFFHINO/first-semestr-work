package todoapp.forms.withDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoForm {
    private Integer id;
    private String name;
    private String description;
}