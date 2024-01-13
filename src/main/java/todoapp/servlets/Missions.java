package todoapp.servlets;

import todoapp.forms.withDto.MissionDto;
import todoapp.forms.withDto.UserDto;
import todoapp.services.MissionService;
import todoapp.services.PostService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mission")
public class Missions extends HttpServlet {
    private MissionService missionService;
    private PostService postService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        missionService = (MissionService) servletContext.getAttribute("missionService");
        postService = (PostService) servletContext.getAttribute(("postService"));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mission_id = req.getParameter("id");
        MissionDto mission  = missionService.getMission(mission_id);
        req.setAttribute("mission", mission);
        String date = mission.getCreated_date().toString().substring(0,10);
        String time = mission.getCreated_date().toString().substring(11,16);
        req.setAttribute("date",date);
        req.setAttribute("time",time);
        Integer projectId = mission.getId_todo();
        Object user = req.getSession().getAttribute("user");
        boolean isAdmin = postService.isAdmin(projectId.toString(), ((UserDto) user).getId().toString());
        req.setAttribute("isAdmin", isAdmin);
        req.getRequestDispatcher("webapp/WEB-INF/view/mission.ftl").forward(req, resp);
    }
}