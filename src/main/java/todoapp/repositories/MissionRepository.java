package todoapp.repositories;

import todoapp.classes.Mission;
import todoapp.repositories.generics.CRUD;
import java.util.List;

public interface MissionRepository extends CRUD<Mission,Integer> {
    List<Mission> findByTodoId(Integer id);
}
