package by.tms.gsproject.repository.user;

import by.tms.gsproject.entity.user.User;

import java.sql.*;
import java.util.Collection;

public class UserDBRepository implements UserRepository {
    private final String ADD_USER = "insert into gsproject.users (id, \"name\",surname, login, password, role) " + "values (?,?,?,?,?,?)";
    private final String MAX_ID = "select max(id) from gsproject.users";

    @Override
    public User add(User user) {
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/gsproject";
            String username = "postgres";
            String password = "1111";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatementMax = connection.prepareStatement("select max(id) from gsproject.users");
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getSurname());
            preparedStatement.setString(4,user.getLogin());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.execute();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
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
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }
}