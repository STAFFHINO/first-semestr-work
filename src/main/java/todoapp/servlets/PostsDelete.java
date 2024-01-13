package todoapp.servlets;

import todoapp.forms.withDto.UserDto;
import todoapp.services.PostService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostsDelete extends HttpServlet {
    PostService postService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        postService = (PostService) servletContext.getAttribute("postService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_todo = req.getParameter("id_todo");
        String id_user = req.getParameter("id_user");
        HttpSession session = req.getSession(true);
        UserDto user = ((UserDto) session.getAttribute("user"));
        if (user.getId().toString().equals(id_user)) {
            req.setAttribute("errorMessage", "It's you");
        }
        postService.deletePost(id_user, id_todo);
        resp.sendRedirect("members");
    }
}
