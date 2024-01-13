package todoapp.forms.withDto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class RemarkDto {
    Integer id;
    Integer id_user;
    String content;
    Integer id_mission;
    LocalDateTime created_date;
}