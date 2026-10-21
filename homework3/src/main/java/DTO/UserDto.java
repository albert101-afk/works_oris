package DTO;

import entities.User;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements UserDto_interface {
    private final DataSource dataSource;

    public UserDto(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getCountOfUsers() {
        String sql = "SELECT value FROM settings";
        int count = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt("value");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public String insertUsers(List<User> users) {
        String sql = "INSERT INTO users (user_id, first_name, last_name, email, age, city, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (User user : users) {
                statement.setInt(1, user.getUser_id());
                statement.setString(2, user.getFirst_name());
                statement.setString(3, user.getLast_name());
                statement.setString(4, user.getEmail());
                statement.setInt(5, user.getAge());
                statement.setString(6, user.getCity());
                statement.setString(7, user.getRole());
                statement.addBatch();
            }
            int [] result = statement.executeBatch();
            String answer = "Было добавлено " + result.length + " строк в базу данных.";
            return answer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRowToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUser_id(resultSet.getInt("user_id"));
        user.setFirst_name(resultSet.getString("first_name"));
        user.setLast_name(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setAge(resultSet.getInt("age"));
        user.setCity(resultSet.getString("city"));
        user.setRole(resultSet.getString("role"));
        return user;
    }

    public List<User> getUsersByCity(String city) {
        String sql = "SELECT * FROM users WHERE city = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city);
            try (ResultSet resultset = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultset.next()) {
                    users.add(mapRowToUser(resultset));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsersByAge(int age) {
        String sql = "SELECT * FROM users WHERE age = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, age);
            try (ResultSet resultset = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultset.next()) {
                    users.add(mapRowToUser(resultset));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsersByRole(String role) {
        String sql = "SELECT * FROM users WHERE role = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            try (ResultSet resultset = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultset.next()) {
                    users.add(mapRowToUser(resultset));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
