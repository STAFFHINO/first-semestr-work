package todoapp.services.impl;

import todoapp.classes.Todo;
import todoapp.forms.withDto.TodoDto;
import todoapp.forms.withDto.TodoForm;
import todoapp.services.TodoMapper;

public class TodoMapperImpl implements TodoMapper {
    public TodoDto toDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .description(todo.getDescription())
                .name(todo.getName())
                .created_date(todo.getCreated_date())
                .id_user(todo.getId_user())
                .build();
    }
    public Todo toTodo(TodoDto dto){
        return Todo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
    public Todo toTodo(TodoForm dto){
        return Todo.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
