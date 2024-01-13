package todoapp.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import todoapp.repositories.RemarkRepository;
import todoapp.repositories.TodoRepository;
import todoapp.repositories.MissionRepository;
import todoapp.repositories.UserRepository;
import todoapp.repositories.impl.RemarkRepositoryJDBCImpl;
import todoapp.repositories.impl.TodoRepositoryJDBCImpl;
import todoapp.repositories.impl.MissionRepositoryJDBCImpl;
import todoapp.repositories.impl.UserRepositoryJDBCImpl;
import todoapp.services.*;
import todoapp.services.impl.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ateez";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql Driver not found.");
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        UserMapper userMapper = new UserMapperImpl();
        RemarkRepository remarkRepository = new RemarkRepositoryJDBCImpl(dataSource);
        MissionRepository missionRepository = new MissionRepositoryJDBCImpl(dataSource);
        TodoRepository todoRepository = new TodoRepositoryJDBCImpl(dataSource);
        UserRepository userRepository = new UserRepositoryJDBCImpl(dataSource);
        AuthorizationService authorizationService = new AuthorizationServiceImpl(userRepository, userMapper, passwordEncoder);
        TodoMapper todoMapper = new TodoMapperImpl();
        TodoService todoService =  new TodoServiceImpl(todoRepository, todoMapper);
        MissionMapper missionMapper = new MissionMapperImpl();
        MissionService missionService = new MissionServiceImpl(missionRepository,missionMapper);
        PostService postService = new PostServiceImpl(userRepository, todoService);
        RemarkMapper remarkMapper = new RemarkMapperImpl();
        RemarkService remarkService = new RemarkServiceImpl(userRepository, remarkRepository, remarkMapper);
        UserService userService = new UserServiceImpl(userRepository);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("taskService", missionService);
        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("taskRepository", missionRepository);
        servletContext.setAttribute("projectRepository", todoRepository);
        servletContext.setAttribute("commentRepository", remarkRepository);
        servletContext.setAttribute("authorizationService", authorizationService);
        servletContext.setAttribute("projectService", todoService);
        servletContext.setAttribute("membershipService", postService);
        servletContext.setAttribute("commentService", remarkService);
        servletContext.setAttribute("userService", userService);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
