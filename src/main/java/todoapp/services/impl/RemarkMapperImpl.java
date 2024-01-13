package todoapp.services.impl;

import todoapp.classes.Remark;
import todoapp.forms.withDto.RemarkDto;
import todoapp.services.RemarkMapper;

public class RemarkMapperImpl implements RemarkMapper {
    public RemarkDto toDto(Remark remark){
        return RemarkDto.builder()
                .id(remark.getId())
                .content(remark.getContent())
                .id_mission(remark.getId_mission())
                .created_date(remark.getCreated_date())
                .id_user(remark.getId_user())
                .build();
    }
}