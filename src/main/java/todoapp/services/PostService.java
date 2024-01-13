package todoapp.services;

import todoapp.forms.withDto.PostDto;
import todoapp.forms.withDto.PostForm;
import java.util.List;

public interface PostService {
    boolean isOwner(String id_todo, String id_user);
    void addPost(PostForm form);
    void deletePost(String id_user, String id_project);
    List<PostDto> getPostByTodo(String id);
    Boolean isAdmin(String id_project, String id_user);
    Boolean isPost(String id_project, String id_user);
}
