package todoapp.services.impl;

import todoapp.forms.withoutDto.SignUpForm;
import todoapp.forms.withDto.UserDto;
import todoapp.classes.User;
import todoapp.services.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .build();
    }
    @Override
    public User toUser(UserDto dto) {
        return User.builder()
                .first_name(dto.getFirst_name())
                .last_name(dto.getLast_name())
                .email(dto.getEmail())
                .build();
    }
    @Override
    public User toUser(SignUpForm dto) {
        return User.builder()
                .first_name(dto.getLast_name())
                .last_name(dto.getLast_name())
                .email(dto.getEmail())
                .password_hash(dto.getPassword())
                .build();
    }

}