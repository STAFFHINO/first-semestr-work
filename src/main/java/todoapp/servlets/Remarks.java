package todoapp.servlets;

import todoapp.services.RemarkService;
import todoapp.services.UserService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remarks")
public class Remarks extends HttpServlet {
    RemarkService remarkService;
    UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
        remarkService = (RemarkService) servletContext.getAttribute("remarkService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_mission = req.getParameter("id_mission");
        req.setAttribute("id_mission", id_mission);
        req.setAttribute("remarks", remarkService.getRemarksByMission(id_mission));
        req.getRequestDispatcher("webapp/WEB-INF/view/remarks.ftl").forward(req, resp);
    }
}
