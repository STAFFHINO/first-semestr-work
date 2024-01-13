package todoapp.services;

import todoapp.forms.withDto.TodoDto;
import todoapp.forms.withDto.TodoForm;
import todoapp.forms.withDto.UserDto;
import java.util.List;

public interface TodoService {
    List<TodoDto> getUsersTodos(UserDto user);
    TodoDto save(TodoForm form, UserDto user);
    TodoDto getTodo(Integer id);
    TodoDto update(TodoDto form);
    void delete(Integer id);
}