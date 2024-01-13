package todoapp.services.impl;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.PostDto;
import todoapp.forms.withDto.PostForm;
import todoapp.forms.withDto.TodoDto;
import lombok.AllArgsConstructor;
import todoapp.repositories.UserRepository;
import todoapp.services.PostService;
import todoapp.services.TodoService;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class PostServiceImpl implements PostService {
    UserRepository userRepository;
    TodoService projectService;
    @Override
    public void addPost(PostForm form) {
        if (form.getId_user().isEmpty() || form.getId_todo().isEmpty() || form.getAdmin_post().isEmpty()) {
            throw new TodoException("Cannot add a post. Parameters cannot be blank");
        }
        userRepository.addUserToTodo(form.getId_user(), Integer.parseInt(form.getId_todo()), Boolean.parseBoolean(form.getAdmin_post()));
    }
    @Override
    public void deletePost(String id_user, String id_todo){
        if (id_user == null || id_todo == null) throw new TodoException("No remark id provided");
        userRepository.deletePostFromTodo(Integer.parseInt(id_user), Integer.parseInt(id_todo));
    }
    @Override
    public boolean isOwner(String id_todo, String id_user){
        Integer todoIdInt = Integer.parseInt(id_todo);
        Integer userIdInt = Integer.parseInt(id_user);
        TodoDto todo = projectService.getTodo(todoIdInt);
        return Objects.equals(todo.getId_user(), userIdInt);
    }
    @Override
    public List<PostDto> getPostByTodo(String id) {
        Integer id_todo = Integer.parseInt(id);
        return userRepository.getPostByTodo(id_todo);
    }
    @Override
    public Boolean isPost(String id_todo, String id_user) {
        Integer todoIdInt = Integer.parseInt(id_todo);
        Integer userIdInt = Integer.parseInt(id_user);
        return userRepository.isPost(todoIdInt, userIdInt);
    }
    @Override
    public Boolean isAdmin(String id_todo, String id_user) {
        Integer todoIdInt = Integer.parseInt(id_todo);
        Integer userIdInt = Integer.parseInt(id_user);
        return userRepository.isAdmin(todoIdInt, userIdInt);
    }
}
