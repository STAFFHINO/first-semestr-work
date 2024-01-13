package todoapp.servlets;

import todoapp.services.TodoService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-todo")
public class ToDoDelete extends HttpServlet {
    TodoService todoService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        todoService = (TodoService) servletContext.getAttribute("todoService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_todo = Integer.parseInt(req.getParameter("id_todo"));
        todoService.delete(id_todo);
        resp.sendRedirect("my-todos");
    }
}