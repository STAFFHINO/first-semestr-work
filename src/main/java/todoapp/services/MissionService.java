package todoapp.services;

import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.MissionForm;
import java.util.List;

public interface MissionService {
    MissionDto update(MissionForm form);
    MissionDto save(MissionForm form, Integer id_todo);
    List<MissionDto> getMissions(Integer id);
    MissionDto getMission(String id);
    void delete(String id);
}
