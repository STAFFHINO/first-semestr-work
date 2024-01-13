package todoapp.servlets;

import todoapp.exceptions.TodoException;
import todoapp.forms.withDto.PostForm;
import todoapp.services.PostService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-post")
public class PostsCreate extends HttpServlet {
    PostService postService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        postService = (PostService) servletContext.getAttribute("postService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_todo = req.getParameter("id_todo");
        req.setAttribute("id_todo", id_todo);
        req.getRequestDispatcher("webapp/WEB-INF/view/create-remark.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostForm form = PostForm.builder()
                .id_user(req.getParameter("id_user"))
                .admin_post(req.getParameter("is_admin"))
                .id_todo(req.getParameter("id_todo"))
                .build();
        try {
            postService.addPost(form);
        } catch (TodoException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("id_todo", req.getParameter("id_todo"));
            req.getRequestDispatcher("create-remark.ftl?id_todo=" + req.getParameter("id_todo")).forward(req, resp);
            return;
        }
        resp.sendRedirect("posts?id_todo=" + req.getParameter("id_todo"));
    }
}
