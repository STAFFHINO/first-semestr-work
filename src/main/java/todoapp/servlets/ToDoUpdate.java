package todoapp.servlets;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.TodoDto;
import todoapp.services.TodoService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-todo")
public class ToDoUpdate extends HttpServlet {
    private TodoService todoService;
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        todoService = (TodoService) servletContext.getAttribute("todoService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_project = req.getParameter("id");
        req.setAttribute("todo", todoService.getTodo(Integer.parseInt(id_project)));
        req.getRequestDispatcher("webapp/WEB-INF/view/update-todo.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDto form = todoService.getTodo(Integer.parseInt(req.getParameter("id")));
        TodoDto todo = form;
        form.setName(req.getParameter("name"));
        form.setDescription(req.getParameter("description"));
        try {
            form = todoService.update(form);
        } catch (TodoException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("todo", todo);
            req.getRequestDispatcher("update-todo.ftl?id=" + form.getId()).forward(req, resp);
            return;
        }
        resp.sendRedirect("todo?id=" + form.getId());
    }
}
