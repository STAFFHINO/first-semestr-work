package todoapp.services.impl;

import todoapp.classes.Mission;
import todoapp.classes.Status;
import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.MissionForm;
import todoapp.services.MissionMapper;
import java.time.LocalDateTime;

public class MissionMapperImpl implements MissionMapper {
    @Override
    public MissionDto toDto(Mission mission) {
        return MissionDto.builder()
                .id(mission.getId())
                .name(mission.getName())
                .description(mission.getDescription())
                .created_date(mission.getCreated_date())
                .deadline(mission.getDeadline())
                .status(mission.getStatus())
                .id_todo(mission.getId_todo())
                .build();
    }
    @Override
    public Mission toMission(MissionForm missionForm) {
        Mission mission = Mission.builder()
                .id(missionForm.getId())
                .name(missionForm.getName())
                .description(missionForm.getDescription())
                .deadline(LocalDateTime.parse(missionForm.getDeadline()))
                .status(Status.valueOf(missionForm.getStatus()))
                .id_todo(missionForm.getId_todo())
                .build();
        if (missionForm.getCreated_date() != null) mission.setCreated_date(LocalDateTime.parse(missionForm.getCreated_date()));
        return mission;
    }
    @Override
    public MissionDto toDto(MissionForm missionForm) {
        MissionDto missionDto = MissionDto.builder()
                .id(missionForm.getId())
                .name(missionForm.getName())
                .description(missionForm.getDescription())
                .deadline(LocalDateTime.parse(missionForm.getDeadline()))
                .status(Status.valueOf(missionForm.getStatus()))
                .build();
        if (missionForm.getCreated_date() != null) missionDto.setCreated_date(LocalDateTime.parse(missionForm.getCreated_date()));
        return missionDto;
    }
    @Override
    public Mission toMission(MissionDto mission) {
        return Mission.builder()
                .id(mission.getId())
                .name(mission.getName())
                .description(mission.getDescription())
                .created_date(mission.getCreated_date())
                .deadline(mission.getDeadline())
                .status(mission.getStatus())
                .id_todo(mission.getId_todo())
                .build();
    }
}
