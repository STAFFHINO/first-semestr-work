package todoapp.services;

import todoapp.forms.withDto.RemarkDto;
import todoapp.classes.Remark;

public interface RemarkMapper {
    RemarkDto toDto(Remark comment);
}
