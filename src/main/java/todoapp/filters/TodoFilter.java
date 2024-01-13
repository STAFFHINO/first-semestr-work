package todoapp.filters;

import todoapp.forms.withDto.UserDto;
import todoapp.services.PostService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/todo")
public class TodoFilter implements Filter {
    protected PostService postService;
    protected Boolean condition;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        postService = (PostService) servletContext.getAttribute("postService");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String id_todo = request.getParameter("id");
        String id_user = getCurrentUserId(httpRequest);
        boolean isMember = postService.isPost(id_todo, id_user);
        boolean isAdmin = postService.isAdmin(id_todo, id_user);
        if (!isMember && !isAdmin) {
            httpResponse.sendRedirect("access-denied");
        } else {
            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }
    protected String getCurrentUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserDto user = (UserDto) session.getAttribute("user");
        return user.getId().toString();
    }
}
