package spring.context.lesson.database.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import spring.context.lesson.database.configuration.DataBaseConfig;
import spring.context.lesson.database.dao.UserDao;
import spring.context.lesson.database.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final DataBaseConfig config;

    @Override
    public Long createUser(User user) {
        String sql = "INSERT INTO users (username) VALUES (?) RETURNING id";

        try (Connection conn = config.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id");
                }
                throw new SQLException("Failed to retrieve generated ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sql request - " + sql + " failed", e);
        }
    }

    @Override
    public Optional<User> getUser(Long id) {
        String sql = "SELECT id, username FROM users WHERE id = ?";

        try (Connection conn = config.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getLong("id"),
                            rs.getString("username")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sql request - " + sql + " failed", e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username FROM users";

        try (Connection conn = config.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sql request - " + sql + " failed", e);
        }
        System.out.println("All users was getting from DB");

        return users;
    }

    @Override
    public Long updateUser(Long id, User user) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";

        try (Connection conn = config.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setLong(2, id);

            var countRowUpdated = stmt.executeUpdate();
            System.out.println(countRowUpdated + " rows success updated");

            return id;
        } catch (SQLException e) {
            throw new RuntimeException("Sql request - " + sql + " failed", e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = config.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            var countRowDeleted = stmt.executeUpdate();
            System.out.println(countRowDeleted + " rows success deleted");

        } catch (SQLException e) {
            throw new RuntimeException("Sql request - " + sql + " failed", e);
        }
    }
}
