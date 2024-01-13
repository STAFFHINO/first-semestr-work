package todoapp.repositories.impl;

import lombok.RequiredArgsConstructor;
import todoapp.exceptions.TodoDBException;
import todoapp.exceptions.TodoException;
import todoapp.classes.Todo;
import todoapp.repositories.TodoRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TodoRepositoryJDBCImpl implements TodoRepository {
    private final DataSource dataSource;
    private final String SQL_INSERT = "INSERT INTO todos(name, description, created_date, id_user) VALUES (?,?,?,?)";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM todos WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM todos";
    private final String SQL_DELETE = "DELETE FROM todos WHERE id = ?";
    private final String SQL_UPDATE = "UPDATE todos SET name = ?, description = ?, created_date = ?, id_user = ? WHERE id = ?";
    private final String SQL_SELECT_BY_USER_ID = "SELECT id_todo FROM todo_post WHERE id_user = ?";
    private final String SQL_ADD_PROJECT_MEMBERSHIP = "INSERT INTO todo_post(id_user, id_todo, admin_post) values (?,?,?)";

    @Override
    public Optional<Todo> findById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapTodo(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
    @Override
    public List<Todo> findByUserId(Integer id) throws TodoException {
        List<Todo> todos = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               todos.add(findById(resultSet.getInt("id_todo")).get());
            }
        } catch (SQLException e) {
            throw new TodoDBException("Cannot do the operation");
        }
        return todos;
    }
    @Override
    public List<Todo> findAll() {
        List<Todo> projects = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                projects.add(mapTodo(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return projects;
    }
    @Override
    public Todo save(Todo todo) {
        try (Connection connection = dataSource.getConnection()) {
            if (todo.getId() == null) {
                try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, todo.getName());
                    statement.setString(2, todo.getDescription());
                    statement.setTimestamp(3, Timestamp.valueOf(todo.getCreated_date()));
                    statement.setInt(4, todo.getId_user());
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows != 1) {
                        throw new SQLException("Cannot insert todo");
                    }
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            todo.setId(generatedKeys.getInt(1));
                            try (PreparedStatement postStatement = connection.prepareStatement(SQL_ADD_PROJECT_MEMBERSHIP)) {
                                postStatement.setInt(1, todo.getId_user());
                                postStatement.setInt(2, todo.getId());
                                postStatement.setBoolean(3, true);
                                int membershipRowsAffected = postStatement.executeUpdate();
                                if (membershipRowsAffected != 1) {
                                    throw new SQLException("Cannot add user to todo membership");
                                }
                            }
                            return todo;
                        } else {
                            throw new SQLException("Cannot retrieve id");
                        }
                    }
                }
            } else {
                try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
                    statement.setString(1, todo.getName());
                    statement.setString(2, todo.getDescription());
                    statement.setTimestamp(3, Timestamp.valueOf(todo.getCreated_date()));
                    statement.setInt(4, todo.getId_user());
                    statement.setInt(5, todo.getId());
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated == 0) {
                        throw new SQLException("Todo with ID: " + todo.getId() + " not found, update failed");
                    }
                    return todo;
                }
            }
        } catch (SQLException e) {
            throw new TodoException("Incorrect input. Length of name cannot be more than 30");
        }
    }
    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Todo with ID: " + id + " not found, deletion failed");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Todo mapTodo(ResultSet resultSet) throws SQLException {
        return Todo.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .created_date(resultSet.getTimestamp("created_date").toLocalDateTime())
                .id_user(resultSet.getInt("id_user"))
                .build();
    }
}