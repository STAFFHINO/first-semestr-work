package todoapp.filters;

import todoapp.services.PostService;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

@WebFilter("/create-mission")
public class NewMissionFilter extends TodoAdminFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ID = "id_todo";
        ServletContext servletContext = filterConfig.getServletContext();
        postService = (PostService) servletContext.getAttribute("postService");
    }
}
