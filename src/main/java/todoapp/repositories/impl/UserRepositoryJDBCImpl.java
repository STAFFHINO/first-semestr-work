package todoapp.repositories.impl;

import todoapp.forms.withDto.PostDto;
import todoapp.exceptions.TodoDBException;
import todoapp.exceptions.TodoException;
import todoapp.classes.User;
import todoapp.repositories.UserRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJDBCImpl implements UserRepository {
    private final String SQL_INSERT = "insert into users(first_name,last_name,email,password_hash) values (?,?,?,?)";
    private final String SQL_SELECT_BY_ID = "select * from users where id = ?";
    private final String SQL_SELECT_ALL = "select * from users";
    private final String SQL_DELETE = "delete from users where id = ?";
    private final String SQL_UPDATE = "update users set first_name = ?, last_name = ?, email = ?, password_hash = ? where id = ?";
    private final String SQL_FIND_BY_EMAIL = "select * from users where email = ?";
    private final String SQL_ADD_USER_TO_PROJECT = "INSERT INTO todo_post(id_user, id_todo, admin_post) values(?,?,?)";
    private final String SQL_IS_MEMBER = "select exists(select * from todo_post where id_user = ? and id_todo = ?)";
    private final String SQL_IS_ADMIN = "select admin_post from todo_post where id_user = ? and id_todo = ?";
    private final String SQL_GET_USERS_BY_PROJECT = "select id_user from todo_post where id_todo = ?";
    private final String SQL_DELETE_MEMBERS = "delete from todo_post where id_user = ? and id_todo = ?";
    private final DataSource dataSource;
    public UserRepositoryJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void deletePostFromTodo(Integer id_user, Integer id_project){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MEMBERS)) {
            statement.setInt(1,id_user);
            statement.setInt(2,id_project);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new TodoDBException("Cannot delete member");
        }
    }
    @Override
    public List<PostDto> getPostByTodo(Integer id) {
        List<PostDto> members = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_USERS_BY_PROJECT)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id_user = resultSet.getInt("id_user");
                Optional<User> userOptional = findById(id_user);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    Boolean isAdmin = isAdmin(id, id_user);
                    PostDto memberDto = PostDto.builder()
                            .id(id_user)
                            .id_user(user.getId())
                            .id_todo(id)
                            .admin_post(isAdmin)
                            .build();
                    members.add(memberDto);
                }
            }
        } catch (SQLException e) {
            throw new TodoDBException("Error with project: " + id + " :(");
        }
        return members;
    }
    @Override
    public Optional<User> findById(Integer id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString("first_name"));
            User user = User.builder().id(resultSet
                            .getInt("id"))
                    .first_name(resultSet.getString("first_name"))
                    .last_name(resultSet.getString("last_name"))
                    .password_hash(resultSet.getString("password_hash"))
                    .email(resultSet.getString("email")).build();
            return Optional.ofNullable(user);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
        while (resultSet.next()) {
            User user = User.builder().id(resultSet
                            .getInt("id"))
                    .first_name(resultSet.getString("first_name"))
                    .last_name(resultSet.getString("last_name"))
                    .password_hash(resultSet.getString("password_hash"))
                    .email(resultSet.getString("email")).build();
            users.add(user);
        }
        return users;
    }
    @Override
    public User save(User model) {
        try (Connection connection = dataSource.getConnection()) {
            if (model.getId() == null) {
                try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, model.getFirst_name());
                    statement.setString(2, model.getLast_name());
                    statement.setString(3, model.getEmail());
                    statement.setString(4, model.getPassword_hash());
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows != 1) {
                        throw new SQLException("Cannot insert user");
                    }
                    try (ResultSet generatedIds = statement.getGeneratedKeys()) {
                        if (generatedIds.next()) {
                            model.setId(generatedIds.getInt("id"));
                            return model;
                        } else {
                            throw new SQLException("Cannot retrieve id");
                        }
                    }
                }
            } else {
                try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
                    statement.setString(1, model.getFirst_name());
                    statement.setString(2, model.getLast_name());
                    statement.setString(3, model.getEmail());
                    statement.setString(4, model.getPassword_hash());
                    statement.setInt(5, model.getId());
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated == 0) {
                        throw new SQLException("User with ID: " + model.getId() + " not found, update failed");
                    }
                    return model;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted == 0) {
                    throw new SQLException("User with ID: " + id + " not found, deletion failed");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getInt("id"))
                            .first_name(resultSet.getString("first_name"))
                            .last_name(resultSet.getString("last_name"))
                            .password_hash(resultSet.getString("password_hash"))
                            .email(resultSet.getString("email"))
                            .build();
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public void addUserToTodo(String email, Integer id_todo, Boolean admin_post) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER_TO_PROJECT)
        ) {
            Optional<User> user = findByEmail(email);
            if (!user.isPresent()) {
                throw new TodoException("User with such email does not exist");
            }
            if (isPost(id_todo, user.get().getId()))
                throw new TodoException("This user is already a member of this project");
            statement.setInt(1, user.get().getId());
            statement.setInt(2, id_todo);
            statement.setBoolean(3, admin_post);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new TodoException("Cannot add a member");
        }
    }

    @Override
    public Boolean isPost(Integer id_todo, Integer id_user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_MEMBER)
        ) {
            statement.setInt(1, id_user);
            statement.setInt(2, id_todo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("exists").equals("t");
            } else {
                throw new TodoDBException("Something went wrong");
            }

        } catch (SQLException e) {
            throw new TodoDBException("Something went wrong :(");
        }
    }

    @Override
    public Boolean isAdmin(Integer id_todo, Integer id_user) {
        if (!isPost(id_todo, id_user)) return false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_ADMIN)
        ) {
            statement.setInt(1, id_user);
            statement.setInt(2, id_todo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("admin_post").equals("t");
            } else {
                throw new TodoDBException("Something went wrong");
            }
        } catch (SQLException e) {
            throw new TodoDBException("Something went wrong :(");
        }
    }
}
