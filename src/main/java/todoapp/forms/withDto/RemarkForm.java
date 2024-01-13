package todoapp.forms.withDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemarkForm {
    String content;
}