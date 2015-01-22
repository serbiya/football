package dao.user;

import dao.factory.DAOFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    public Connection connection = null;

    public UserDAO (Connection connection) {
        this.connection = connection;
    }

    public void create(User user) {
        LOGGER.info("Start inserting new user");

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("insert into USERS values (default, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getRoleID());
            statement.setBoolean(7, user.isActive());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Creating new user has failed: " + e.getMessage());
        }

        LOGGER.info("Finish creating new user");
    }


/*    CREATE TABLE USERS(ID INT PRIMARY KEY, LOGIN VARCHAR(50) NOT NULL UNIQUE, NAME VARCHAR(50), LAST_NAME VARCHAR(50),
    EMAIL VARCHAR(100), PASSWORD VARCHAR(200), ROLE_ID INTEGER, ACTIVE BOOLEAN);*/

    public User findUserByLogin(String login) {
        LOGGER.info("Start findUserByLogin: " + login);

        User user = null;
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement("SELECT * FROM USERS where (login = ? and active = true)");
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("LOGIN"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRoleID(resultSet.getInt("ROLE_ID"));
                user.setActive(resultSet.getBoolean("ACTIVE"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            LOGGER.error("findUserByLogin has failed: " + e.getMessage());
        }

        LOGGER.debug("Finish findUserByLogin");
        return user;
    }

    public boolean isUserRegistered(String login) {
        LOGGER.info("Start isUserRegistered: " + login);

        PreparedStatement statement;
        ResultSet resultSet;
        boolean result = false;
        try {
            statement = connection.prepareStatement("SELECT COUNT (ID) FROM USERS WHERE LOGIN = '" + login + "')");
            //statement.setString(1, login);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1) != 0;
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            LOGGER.error("isUserRegistered has failed: " + e.getMessage());
        }

        LOGGER.info("Finish isUserRegistered");
        return result;
    }
}
