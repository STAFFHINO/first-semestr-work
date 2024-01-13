package todoapp.forms.withDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostForm {
    String id_todo;
    String id_user;
    String admin_post;
}
