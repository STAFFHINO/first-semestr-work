package todoapp.services;

import todoapp.forms.withoutDto.SignUpForm;
import todoapp.forms.withDto.UserDto;
import todoapp.classes.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto dto);
    User toUser(SignUpForm dto);
}