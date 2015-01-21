package dao;

import entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    private static Connection connection;

    private static void setConnection() {
        try {
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:test",
                    "sa", "");
            LOGGER.info("The connection to the database is successful!");
        } catch (Exception e) {
            LOGGER.error("The connection to the database has failed!" + e.toString());
            throw new NullPointerException("The connection to the database has failed!" + e.toString());
        }
    }

    public static void closeConnection() {
        try {
            LOGGER.info("Trying to close the connection");
            connection.close();
            if (connection.isClosed()) {
                LOGGER.info("Closing a database connection is successful!");
            }
        } catch (SQLException e) {
            LOGGER.error("Closing a database connection has failed!" + e.toString());
            throw new NullPointerException("Closing a database connection has failed!" + e.toString());
        }
    }

    public void create(User user) throws Exception {
        LOGGER.debug("create started");
        try {
            PreparedStatement statement = connection.prepareStatement("insert into user values (default, ?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("create - " + e.getMessage());
        }
        LOGGER.debug("create() - OK");
    }

    public User findUserByLogin(String name) {
        LOGGER.debug("findUserByLogin started");
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users where (name = " + name);
            while (resultSet.next()) {
                String nameFound = resultSet.getString("NAME");
                String passFound = resultSet.getString("PASSWORD");
                System.out.println(resultSet.getString("ID") + " " + nameFound);

                user = new User();
                user.setLogin(nameFound);
                user.setPassword(passFound);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            LOGGER.error("findUserByLogin - " + e.getMessage());
        }
        LOGGER.debug("findUserByLogin() - OK");
        return user;
    }
}
