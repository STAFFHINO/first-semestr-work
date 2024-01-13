package todoapp.repositories.generics;

import java.util.List;
import java.util.Optional;

public interface CRUD<T, K> {
    Optional<T> findById(K id) throws java.sql.SQLException;
    List<T> findAll() throws java.sql.SQLException;
    T save(T item);
    void delete(K id);
}
