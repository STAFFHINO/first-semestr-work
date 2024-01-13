package todoapp.repositories.impl;

import lombok.RequiredArgsConstructor;
import todoapp.exceptions.TodoDBException;
import todoapp.classes.Remark;
import todoapp.repositories.RemarkRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RemarkRepositoryJDBCImpl implements RemarkRepository {
    private final DataSource dataSource;
    private final String SQL_SELECT_BY_TASK_ID = "SELECT * FROM remarks WHERE id_mission = ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM remarks WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM remarkts";
    private final String SQL_DELETE = "DELETE cascade FROM remarks WHERE id = ?";
    private final String SQL_INSERT = "INSERT INTO remarks(content, created_date, id_mission, id_user) VALUES (?,?,?,?)";
    @Override
    public List<Remark> findByMissionId(Integer id) {
        List<Remark> remarks = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TASK_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                remarks.add(mapRemark(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return remarks;
    }

    @Override
    public Optional<Remark> findById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRemark(resultSet));
            }
        } catch (SQLException e) {
            throw new TodoDBException(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public List<Remark> findAll() {
        List<Remark> comments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                comments.add(mapRemark(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return comments;
    }
    @Override
    public Remark save(Remark item) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, item.getContent());
                statement.setTimestamp(2, Timestamp.valueOf(item.getCreated_date()));
                statement.setInt(3, item.getId_mission());
                statement.setInt(4, item.getId_user());
                int affectedRows = statement.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Cannot insert remark");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        item.setId(generatedKeys.getInt(1));
                        return item;
                    } else {
                        throw new SQLException("Cannot retrieve id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new TodoDBException("Cannot add a remark");
        }
    }
    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Comment with ID: " + id + " not found, deletion failed");
            }
        } catch (SQLException e) {
            throw new TodoDBException(e.getMessage());
        }
    }
    private Remark mapRemark(ResultSet resultSet) throws SQLException {
        return Remark.builder()
                .id(resultSet.getInt("id"))
                .content(resultSet.getString("content"))
                .created_date(resultSet.getTimestamp("created_date").toLocalDateTime())
                .id_mission(resultSet.getInt("id_mission"))
                .id_user(resultSet.getInt("id_user"))
                .build();
    }
}
