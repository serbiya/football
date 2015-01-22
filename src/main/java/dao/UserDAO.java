package dao;

import connection.ConnectionPool;
import entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);


    public void create(User user) {
        LOGGER.info("Start inserting new user");

        ConnectionPool pool;
        PreparedStatement st;
        Connection connection;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.prepareStatement("insert into users values (default, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, user.getLogin());
            st.setString(2, user.getName());
            st.setString(3, user.getLastName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setInt(6, user.getRoleID());
            st.setBoolean(7, user.isActive());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            LOGGER.error("Creating new user has failed: " + e.getMessage());
        }

        LOGGER.info("Finish creating new user");
    }


/*    CREATE TABLE USERS(ID INT PRIMARY KEY, LOGIN VARCHAR(50) NOT NULL UNIQUE, NAME VARCHAR(50), LAST_NAME VARCHAR(50),
    EMAIL VARCHAR(100), PASSWORD VARCHAR(200), ROLE_ID INTEGER, ACTIVE BOOLEAN);*/

    public User findUserByLogin(String login) {
        LOGGER.info("Start findUserByLogin: " + login);

        ConnectionPool pool;
        User user = null;
        PreparedStatement st;
        ResultSet resultSet;
        Connection connection;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.prepareStatement("SELECT * FROM users where (login = ? and active = true)");
            st.setString(1, login);
            resultSet = st.executeQuery();
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
            st.close();
        } catch (Exception e) {
            LOGGER.error("findUserByLogin has failed: " + e.getMessage());
        }

        LOGGER.debug("Finish findUserByLogin");
        return user;
    }

    public boolean isUserRegistered(String login) {
        LOGGER.info("Start isUserRegistered: " + login);

        ConnectionPool pool;
        PreparedStatement st;
        ResultSet resultSet;
        Connection connection;
        boolean result = false;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.prepareStatement("SELECT COUNT (id) FROM users where (login = ?)");
            st.setString(1, login);
            resultSet = st.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1) != 0;
            resultSet.close();
            st.close();
        } catch (Exception e) {
            LOGGER.error("isUserRegistered has failed: " + e.getMessage());
        }

        LOGGER.info("Finish isUserRegistered");
        return result;
    }
}
