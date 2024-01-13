package todoapp.servlets;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.UserDto;
import todoapp.services.RemarkService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create-remark")
public class RemarksCreate extends HttpServlet {
    RemarkService remarkService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        remarkService = (RemarkService) servletContext.getAttribute("remarkService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_mission = req.getParameter("id_mission");
        req.setAttribute("id_mission", id_mission);
        req.getRequestDispatcher("webapp/WEB-INF/view/create-remark.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String content = req.getParameter("content");
        String id_mission = req.getParameter("id_mission");
        UserDto user = ((UserDto) session.getAttribute("user"));
        try {
            remarkService.save(content, user, id_mission);
        } catch (TodoException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("create-todo.ftl").forward(req, resp);
            return;
        }
        resp.sendRedirect("remarks?id_mission=" + id_mission);
    }
}
