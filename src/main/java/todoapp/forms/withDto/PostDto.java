package todoapp.forms.withDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    Integer id;
    Integer id_user;
    Integer id_todo;
    Boolean admin_post;
}