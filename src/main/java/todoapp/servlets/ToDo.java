package todoapp.servlets;

import todoapp.services.PostService;
import todoapp.services.TodoService;
import todoapp.services.MissionService;
import todoapp.forms.withDto.UserDto;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todo")
public class ToDo extends HttpServlet {
    private TodoService todoService;
    private MissionService missionService;
    private PostService postService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        todoService = (TodoService) servletContext.getAttribute("todoService");
        missionService = (MissionService) servletContext.getAttribute("missionService");
        postService = (PostService) servletContext.getAttribute("postService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_todo = req.getParameter("id");
        req.setAttribute("missions", missionService.getMissions(Integer.parseInt(id_todo)));
        req.setAttribute("todo", todoService.getTodo(Integer.parseInt(id_todo)));
        Object user = req.getSession().getAttribute("user");
        boolean isAdmin = postService.isAdmin(id_todo, ((UserDto) user).getId().toString());
        req.setAttribute("isAdmin", isAdmin);
        req.getRequestDispatcher("webapp/WEB-INF/view/todo.ftl").forward(req, resp);
    }
}
