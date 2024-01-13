package todoapp.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import todoapp.forms.withDto.UserDto;
import todoapp.services.TodoService;

@WebServlet("/my-todos")
public class ToDoMy extends HttpServlet {
    private TodoService todoService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        todoService = (TodoService) config.getServletContext().getAttribute("todoService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        Object todos = todoService.getUsersTodos((UserDto)user);
        req.setAttribute("todos", todos);
        req.getRequestDispatcher("webapp/WEB-INF/view/my-todos.ftl").forward(req, resp);
    }
}
