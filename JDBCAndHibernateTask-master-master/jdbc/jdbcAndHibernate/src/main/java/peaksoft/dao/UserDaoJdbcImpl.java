package peaksoft.dao;

import org.hibernate.SessionBuilder;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users(" +
                "id  SERIAL PRIMARY KEY ," +
                "name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL," +
                "age SMALLINT )";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users ";
        try (Connection con = Util.connection();
             Statement statement = con.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("table Deleted");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name,last_name,age)" +
                "VALUES (?,?,?)";
        try (Connection connect = Util.connection();
             PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Successfully added to database: " + name);
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }

    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";
        try (Connection con = Util.connection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Successfully deleted");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection con = Util.connection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        return users;
    }


    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE users";
        try (Connection con = Util.connection();
             Statement statement = con.createStatement()) {
            statement.execute(SQL);
            System.out.println();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }


}