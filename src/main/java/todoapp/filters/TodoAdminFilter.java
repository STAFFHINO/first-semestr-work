package todoapp.filters;

import todoapp.services.PostService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/todo")
public class TodoAdminFilter extends TodoFilter {
    protected String ID;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ID = "id";
        ServletContext servletContext = filterConfig.getServletContext();
        postService = (PostService) servletContext.getAttribute("postService");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String id_todo = request.getParameter(ID);
        String id_user = getCurrentUserId(httpRequest);
        boolean isAdmin = postService.isAdmin(id_todo, id_user);
        if (!isAdmin) {
            httpResponse.sendRedirect("access-denied");
        } else {
            chain.doFilter(request, response);
        }
    }
}
