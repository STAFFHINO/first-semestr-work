package todoapp.servlets;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.TodoDto;
import todoapp.forms.withDto.TodoForm;
import todoapp.forms.withDto.UserDto;
import todoapp.services.TodoService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create-todo")
public class ToDoCreate extends HttpServlet {
    private TodoService todoService;
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        todoService = (TodoService) servletContext.getAttribute("todoService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("webapp/WEB-INF/view/create-todo.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoForm form = TodoForm.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .build();
        TodoDto project;
        HttpSession session = req.getSession(true);
        try {
            project = todoService.save(form, (UserDto) session.getAttribute("user"));
        } catch (TodoException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("create-todo.ftl").forward(req, resp);
            return;
        }
        resp.sendRedirect("todo?id=" + project.getId());
    }
}
