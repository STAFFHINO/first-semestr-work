package todoapp.services.impl;

import lombok.AllArgsConstructor;
import todoapp.exceptions.TodoException;
import todoapp.classes.User;
import todoapp.repositories.UserRepository;
import todoapp.services.UserService;
import java.sql.SQLException;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Override
    public String getEmailById(String id) {
        Integer userId = Integer.parseInt(id);
        try {
            User user = userRepository.findById(userId).get();
            return user.getEmail();
        } catch (SQLException e) {
            throw new TodoException(e.getMessage());
        }
    }
}
