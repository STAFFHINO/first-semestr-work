package todoapp.services;

import todoapp.forms.withDto.TodoDto;
import todoapp.forms.withDto.TodoForm;
import todoapp.classes.Todo;

public interface TodoMapper {
    TodoDto toDto(Todo Todo);
    Todo toTodo(TodoDto dto);
    Todo toTodo(TodoForm dto);
}
