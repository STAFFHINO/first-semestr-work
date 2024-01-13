package todoapp.repositories.impl;

import lombok.RequiredArgsConstructor;
import todoapp.exceptions.TodoException;
import todoapp.classes.Status;
import todoapp.classes.Mission;
import todoapp.repositories.MissionRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MissionRepositoryJDBCImpl implements MissionRepository {
    private final DataSource dataSource;
    private final String SQL_INSERT = "INSERT INTO missions(name, description, created_date, deadline, status, id_todo) VALUES (?,?,?,?,?,?)";
    private final String SQL_SELECT_BY_PROJECT_ID = "SELECT * FROM missions WHERE id_todo = ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM missions WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM missions";
    private final String SQL_DELETE = "DELETE FROM missions WHERE id = ?";
    private final String SQL_UPDATE = "UPDATE missions SET name = ?, description = ?, created_date = ?, deadline = ?, status = ?, id_todo = ? WHERE id = ?";
    @Override
    public List<Mission> findByTodoId(Integer id) {
        List<Mission> missions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_PROJECT_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                missions.add(mapMission(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return missions;
    }

    @Override
    public Optional<Mission> findById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapMission(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
    @Override
    public List<Mission> findAll() {
        List<Mission> missions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                missions.add(mapMission(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return missions;
    }
    @Override
    public Mission save(Mission mission) {
        try (Connection connection = dataSource.getConnection()) {
            if (mission.getId() == null) {
                try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, mission.getName());
                    statement.setString(2, mission.getDescription());
                    statement.setTimestamp(3, Timestamp.valueOf(mission.getCreated_date()));
                    statement.setTimestamp(4, Timestamp.valueOf(mission.getDeadline()));
                    statement.setString(5, mission.getStatus().name());
                    statement.setInt(6, mission.getId_todo());
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows != 1) {
                        throw new SQLException("Cannot insert mission");
                    }
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            mission.setId(generatedKeys.getInt(1));
                            return mission;
                        } else {
                            throw new SQLException("Cannot retrieve id");
                        }
                    }
                }
            } else {
                try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
                    statement.setString(1, mission.getName());
                    statement.setString(2, mission.getDescription());
                    statement.setTimestamp(3, Timestamp.valueOf(mission.getCreated_date()));
                    statement.setTimestamp(4, Timestamp.valueOf(mission.getDeadline()));
                    statement.setString(5, mission.getStatus().name());
                    statement.setInt(6, mission.getId_todo());
                    statement.setInt(7, mission.getId());
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated == 0) {
                        throw new SQLException("Mission with ID: " + mission.getId() + " not found, update failed");
                    }
                    return mission;
                }
            }
        } catch (SQLException e) {
            throw new TodoException("Cannot apply the changes");
        }
    }


    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Mission with ID: " + id + " not found, deletion failed");
            }
        } catch (SQLException e) {
            throw new TodoException("Cannot delete the todo");
        }
    }
    private Mission mapMission(ResultSet resultSet) throws SQLException {
        return Mission.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .created_date(resultSet.getTimestamp("created_date").toLocalDateTime())
                .deadline(resultSet.getTimestamp("deadline").toLocalDateTime())
                .status(Status.valueOf(resultSet.getString("status")))
                .id_todo(resultSet.getInt("id_todo"))
                .build();
    }
}
