package todoapp.servlets;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.MissionForm;
import todoapp.services.MissionService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-mission")
public class MissionsUpdate extends HttpServlet {
    private MissionService missionService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        missionService = (MissionService) servletContext.getAttribute("missionService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_mission = req.getParameter("id");
        req.setAttribute("mission", missionService.getMission(id_mission));
        req.getRequestDispatcher("webapp/WEB-INF/view/update-mission.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_mission = req.getParameter("id");
        MissionDto mission = missionService.getMission(id_mission);
        MissionForm form = MissionForm.builder()
                .id(mission.getId())
                .created_date(mission.getCreated_date().toString())
                .id_todo(mission.getId_todo())
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .status(req.getParameter("status"))
                .deadline(req.getParameter("deadline"))
                .build();
        try {
            missionService.update(form);
        } catch (TodoException e) {
            req.setAttribute("mission", missionService.getMission(id_mission));
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("update-mission.ftl?id=" + req.getParameter("id")).forward(req, resp);
            return;
        }
        resp.sendRedirect("mission?id=" + id_mission);
    }
}
