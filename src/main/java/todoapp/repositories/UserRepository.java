package todoapp.repositories;

import todoapp.forms.withDto.PostDto;
import todoapp.classes.User;
import todoapp.repositories.generics.CRUD;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CRUD<User, Integer> {
    void deletePostFromTodo(Integer id_user, Integer id_project);
    List<PostDto> getPostByTodo(Integer id);
    Optional<User> findByEmail(String email);
    void addUserToTodo(String email, Integer id_user, Boolean isAdmin);
    Boolean isPost(Integer id_project, Integer id_user);
    Boolean isAdmin(Integer id_project, Integer id_user);
}