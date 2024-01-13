package todoapp.services.impl;

import todoapp.classes.Todo;
import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.TodoDto;
import todoapp.forms.withDto.TodoForm;
import todoapp.forms.withDto.UserDto;
import lombok.AllArgsConstructor;
import todoapp.repositories.TodoRepository;
import todoapp.services.TodoMapper;
import todoapp.services.TodoService;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;
    TodoMapper todoMapper;
    public TodoDto save(TodoForm form, UserDto user) {
        if (form.getName() == null) {
            throw new TodoException("Todo must nave name");
        }
        Todo todo = todoMapper.toTodo(form);
        todo.setId_user(user.getId());
        todo.setCreated_date(LocalDateTime.now());
        todoRepository.save(todo);
        return todoMapper.toDto(todo);
    }
    public List<TodoDto> getUsersTodos(UserDto user) {
        return todoRepository.findByUserId(user.getId())
                .stream()
                .map(todoMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public TodoDto getTodo(Integer id) {
        try {
            return todoMapper.toDto(todoRepository.findById(id).get());
        } catch (SQLException e) {
            throw new TodoException("Cannot find a todo");
        }
    }
    @Override
    public TodoDto update(TodoDto form) {
        try {
            Todo existingTodo = todoRepository.findById(form.getId())
                    .orElseThrow(() -> new TodoException("Todo not found"));
            if (form.getId() == null || form.getName() == null) {
                throw new TodoException("Todo ID and name are required for updating");
            }
            existingTodo.setName(form.getName());
            existingTodo.setDescription(form.getDescription());
            todoRepository.save(existingTodo);
            return todoMapper.toDto(existingTodo);
        } catch (SQLException e) {
            throw new TodoException("Todo does not exist");
        }
    }
    @Override
    public void delete(Integer id) {
        todoRepository.delete(id);
    }
}
