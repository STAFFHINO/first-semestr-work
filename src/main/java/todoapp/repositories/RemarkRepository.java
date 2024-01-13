package todoapp.repositories;

import todoapp.classes.Remark;
import todoapp.repositories.generics.CRUD;
import java.util.List;

public interface RemarkRepository extends CRUD<Remark, Integer> {
    List<Remark> findByMissionId(Integer id);
}