package todoapp.services;

import todoapp.forms.withDto.RemarkDto;
import todoapp.forms.withDto.UserDto;
import java.util.List;

public interface RemarkService {
    RemarkDto save(String content, UserDto user, String id_mission);
    List<RemarkDto> getRemarksByMission(String mission);
}