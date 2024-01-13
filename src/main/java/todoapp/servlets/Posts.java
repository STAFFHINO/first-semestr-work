package todoapp.servlets;

import todoapp.forms.withDto.UserDto;
import todoapp.services.PostService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/posts")
public class Posts extends HttpServlet {
    PostService postService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        postService = (PostService) servletContext.getAttribute("postService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_todo = req.getParameter("id_todo");
        req.setAttribute( "posts", postService.getPostByTodo(id_todo));
        Object user = req.getSession().getAttribute("user");
        req.setAttribute("admin_post", postService.isAdmin(id_todo, ((UserDto)user).getId().toString()));
        req.setAttribute("id_todo", id_todo);
        req.getRequestDispatcher("webapp/WEB-INF/view/posts.ftl").forward(req, resp);
    }
}
