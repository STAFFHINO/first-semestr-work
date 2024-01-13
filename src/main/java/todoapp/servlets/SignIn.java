package todoapp.servlets;

import todoapp.forms.withoutDto.SignInForm;
import todoapp.forms.withDto.UserDto;
import todoapp.exceptions.TodoException;
import todoapp.services.AuthorizationService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
    AuthorizationService authorizationService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        authorizationService = (AuthorizationService) config.getServletContext().getAttribute("authorizationService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("webapp/WEB-INF/view/sign-in.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SignInForm form = SignInForm.builder()
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .build();
            UserDto user = authorizationService.signIn(form);
            req.getSession().setAttribute("user", user);
        } catch (TodoException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("webapp/WEB-INF/view/sign-in.ftl").forward(req, resp);
        }
        resp.sendRedirect("profile");
    }
}
