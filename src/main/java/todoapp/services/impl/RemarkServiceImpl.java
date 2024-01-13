package todoapp.services.impl;

import todoapp.classes.Remark;
import todoapp.classes.User;
import todoapp.exceptions.TodoDBException;
import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.RemarkDto;
import todoapp.forms.withDto.UserDto;
import lombok.AllArgsConstructor;
import todoapp.repositories.RemarkRepository;
import todoapp.repositories.UserRepository;
import todoapp.services.RemarkMapper;
import todoapp.services.RemarkService;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RemarkServiceImpl implements RemarkService {
    UserRepository userRepository;
    RemarkRepository remarkRepository;
    RemarkMapper remarkMapper;
    @Override
    public RemarkDto save(String content, UserDto user, String id_mission) {
        if (content == null || user == null || id_mission == null) {
            throw new TodoException("Cannot save the remark");
        }
        Remark remark = Remark.builder().created_date(LocalDateTime.now())
                .content(content)
                .id_user(user.getId())
                .id_mission(Integer.parseInt(id_mission))
                .build();
        remarkRepository.save(remark);
        return remarkMapper.toDto(remark);
    }
    @Override
    public List<RemarkDto> getRemarksByMission(String id_mission) {
        if (id_mission == null) throw new TodoDBException("This mission does not exist");
        return remarkRepository.findByMissionId(Integer.parseInt(id_mission))
                .stream()
                .map(remark -> {
                    try {
                        RemarkDto remarkDto = remarkMapper.toDto(remark);
                        Integer userId = remark.getId_user();
                        User user = userRepository.findById(userId).get();
                        remarkDto.setId_user(user.getId());
                        return remarkDto;
                    } catch (SQLException e) {
                        throw new TodoException(e.getMessage());
                    }
                })
                .collect(Collectors.toList());

    }
}