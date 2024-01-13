package todoapp.services;

import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.MissionForm;
import todoapp.classes.Mission;

public interface MissionMapper {
    MissionDto toDto(Mission todo);
    Mission toMission(MissionDto dto);
    Mission toMission(MissionForm mission);
    MissionDto toDto(MissionForm form);
}
