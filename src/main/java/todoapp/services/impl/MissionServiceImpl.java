package todoapp.services.impl;

import todoapp.classes.Mission;
import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.MissionForm;
import lombok.AllArgsConstructor;
import todoapp.repositories.MissionRepository;
import todoapp.services.MissionMapper;
import todoapp.services.MissionService;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MissionServiceImpl implements MissionService {
    MissionRepository missionRepository;
    MissionMapper missionMapper;
    @Override
    public MissionDto save(MissionForm form, Integer id_project) {
        Mission mission = missionMapper.toMission(form);
        mission.setCreated_date(LocalDateTime.now());
        mission.setId_todo(id_project);
        mission = missionRepository.save(mission);
        return missionMapper.toDto(mission);
    }
    @Override
    public MissionDto update(MissionForm form) {
        Mission mission = missionMapper.toMission(form);
        mission = missionRepository.save(mission);
        return missionMapper.toDto(mission);
    }
    @Override
    public List<MissionDto> getMissions(Integer id) {
        return missionRepository.findByTodoId(id)
                .stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public MissionDto getMission(String missionId) {
        try {
            if (missionId.isEmpty()) throw new TodoException("Cannot find this mission");
            Integer id = Integer.parseInt(missionId);
            Optional<Mission> mission = missionRepository.findById(id);
            if (!mission.isPresent()) {
                throw new TodoException("Cannot find a todo");
            }
            return missionMapper.toDto(mission.get());
        } catch (SQLException e) {
            throw new TodoException("Cannot find a mission");
        }
    }
    @Override
    public void delete(String id) {
        if (id == null) {
            throw new TodoException("No mission id provided");
        }
        missionRepository.delete(Integer.parseInt(id));
    }
}
