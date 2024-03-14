package by.tms.gsproject.repository.user;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.entity.user.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDBRepository implements UserRepository {
    private final String ADD_USER = "insert into gsproject.users (id, \"name\",surname, login, password, role) " + "values (?,?,?,?,?,?)";
    private final String MAX_ID = "select max(id) from gsproject.users";

    @Override
    public User add(User user) {
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/gsbase";
            String username = "postgres";
            String password = "1111";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement countStatement = connection.prepareStatement("SELECT COUNT(*) FROM gsproject.users");
            ResultSet countResult = countStatement.executeQuery();
            countResult.next();
            int userCount = countResult.getInt(1);
            PreparedStatement preparedStatement;
            if (userCount == 0) {
                preparedStatement = connection.prepareStatement("INSERT INTO gsproject.users (id, \"name\", surname, login, password, role) VALUES (?, ?, ?, ?, ?, ?)");
                user.setRole(UserRole.Role.ADMIN);
            } else {
                preparedStatement = connection.prepareStatement(ADD_USER);
                user.setRole(UserRole.Role.CLIENT);
            }
            PreparedStatement preparedStatementMax = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.execute();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    @Override
    public void deleteById(Long userId) {
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Collection<User> allUsers() {
        Collection<User> allUsers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/gsbase";
            String username = "postgres";
            String password = "1111";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM gsproject.users";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String login = resultSet.getString(4);
                String password1 = resultSet.getString(5);
                String role = resultSet.getString(6);
                User user = new User(Long.valueOf(id), name, surname, login, password1, role);
                allUsers.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return allUsers;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }
}