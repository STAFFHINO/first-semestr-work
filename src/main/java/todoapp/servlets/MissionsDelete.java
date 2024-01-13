package todoapp.servlets;

import todoapp.services.MissionService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-mission")
public class MissionsDelete extends HttpServlet {
    MissionService missionService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        missionService = (MissionService) servletContext.getAttribute("missionService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_mission = req.getParameter("id");
        String id_todo = req.getParameter("id_todo");
        missionService.delete(id_mission);
        resp.sendRedirect("todo?id="+id_todo);
    }
}
