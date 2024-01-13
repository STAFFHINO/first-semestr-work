package todoapp.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.MissionForm;
import todoapp.services.MissionService;

@WebServlet("/create-mission")
public class MissionsCreate extends HttpServlet {
    MissionService missionService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        missionService = (MissionService) servletContext.getAttribute("missionService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("webapp/WEB-INF/view/create-mission.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MissionForm form = MissionForm.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .deadline(req.getParameter("deadline"))
                .status(req.getParameter("status"))
                .build();
        MissionDto mission;
        try {
            mission = missionService.save(form, Integer.parseInt(req.getParameter("id_todo")));
        } catch (TodoException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("create-mission.ftl?id_todo=" + req.getParameter("id_todo")).forward(req, resp);
            return;
        }
        resp.sendRedirect("todo?id=" + req.getParameter("id_todo"));
    }
}
