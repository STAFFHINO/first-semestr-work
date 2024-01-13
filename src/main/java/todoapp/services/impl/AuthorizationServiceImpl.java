package todoapp.services.impl;

import lombok.AllArgsConstructor;
import todoapp.forms.withoutDto.*;
import todoapp.forms.withDto.UserDto;
import todoapp.exceptions.TodoException;
import todoapp.classes.User;
import todoapp.repositories.UserRepository;
import todoapp.services.AuthorizationService;
import todoapp.services.PasswordEncoder;
import todoapp.services.UserMapper;
import java.util.Optional;

@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(SignUpForm form) throws TodoException {
        if (form.getEmail() == null) {
            throw new TodoException("Email cannot be null");
        }
        Optional<User> optionalUser = userRepository.findByEmail(form.getEmail());
        if (optionalUser.isPresent()) {
            throw new TodoException("User with email " + form.getEmail() + " already exist");
        }
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        User user = userMapper.toUser(form);
        User savedUser = userRepository.save(user);
        user.setId(userRepository.findByEmail(user.getEmail()).get().getId());
        return userMapper.toDto(savedUser);
    }
    @Override
    public UserDto signIn(SignInForm form) throws TodoException {
        if(form.getEmail() == null) {
            throw new TodoException("Email cannot be null");
        }
        Optional<User> optionalUser = userRepository.findByEmail(form.getEmail());
        if(!optionalUser.isPresent()) {
            throw new TodoException("User with email " + form.getEmail() + " not found.");
        }
        User user = optionalUser.get();
        user.setId(userRepository.findByEmail(user.getEmail()).get().getId());
        if(!passwordEncoder.matches(form.getPassword(), user.getPassword_hash())) {
            throw new TodoException("Wrong password");
        }
        return userMapper.toDto(user);
    }
}
