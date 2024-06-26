package by.tms.gsproject.repository.user;

import by.tms.gsproject.config.JDBCConnection;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.entity.user.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Base64;

import static by.tms.gsproject.constants.SQLQueries.*;

public class UserJDBCRepository implements UserRepository {

    @Override
    public User add(User user) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement countStatement = connection.prepareStatement("SELECT COUNT(*) FROM gsproject.users"); PreparedStatement preparedStatementMax = connection.prepareStatement(SELECT_MAX_ID)) {
            ResultSet countResult = countStatement.executeQuery();
            countResult.next();
            long userCount = countResult.getLong(1);
            PreparedStatement preparedStatement;
            if (userCount == 0) {
                preparedStatement = connection.prepareStatement(ADD_USER);
                user.setRole(UserRole.Role.ADMIN);
            } else {
                preparedStatement = connection.prepareStatement(ADD_USER);
                user.setRole(UserRole.Role.CLIENT);
            }
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            long maxId = resultSet.getLong(1);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
            preparedStatement.setString(5, encodedPassword);
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void deleteById(Long userId) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user with ID: " + userId, e);
        }
    }

    @Override
    public Collection<User> allUsers() {
        Collection<User> allUsers = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String login = resultSet.getString(4);
                String encodedPassword = resultSet.getString(5);
                String roleString = resultSet.getString(6);
                UserRole.Role role = UserRole.Role.valueOf(roleString);
                String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword));
                User user = new User(id, name, surname, login, decodedPassword, role);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    @Override
    public User getUserById(Long userId) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String login = resultSet.getString(4);
                    String encodedPassword = resultSet.getString(5);
                    String roleString = resultSet.getString(6);
                    UserRole.Role role = UserRole.Role.valueOf(roleString);
                    String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword));
                    return new User(id, name, surname, login, decodedPassword, role);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find user with ID: " + userId, e);
        }
    }

    @Override
    public User findByLogin(String userLogin) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, userLogin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String login = resultSet.getString(4);
                    String encodedPassword = resultSet.getString(5);
                    String roleString = resultSet.getString(6);
                    UserRole.Role role = UserRole.Role.valueOf(roleString);
                    String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword));
                    return new User(id, name, surname, login, decodedPassword, role);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find user with login: " + userLogin, e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
            preparedStatement.setString(3, encodedPassword);
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user with ID: " + user.getId(), e);
        }
    }
}