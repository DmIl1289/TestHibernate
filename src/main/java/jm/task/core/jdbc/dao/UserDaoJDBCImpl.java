package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends User implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(60) NOT NULL, lastName VARCHAR(60) NOT NULL, age INT(3), PRIMARY KEY(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("INSERT INTO Users " +
                "(name, lastName, age) values(?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM Users WHERE id = id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
