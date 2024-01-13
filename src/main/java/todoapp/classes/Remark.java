package todoapp.classes;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Remark {
    private Integer id;
    private String content;
    private LocalDateTime created_date;
    private Integer id_mission;
    private Integer id_user;
}
