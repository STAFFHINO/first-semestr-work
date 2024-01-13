package todoapp.repositories;

import todoapp.classes.Todo;
import todoapp.repositories.generics.CRUD;
import java.util.List;

public interface TodoRepository extends CRUD<Todo, Integer> {
    List<Todo> findByUserId(Integer id);
}
