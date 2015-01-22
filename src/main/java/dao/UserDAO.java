package dao;

import connection.ConnectionPool;
import entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);


    public void create(User user) throws Exception {
        LOGGER.debug("create started");
        ConnectionPool pool;
        PreparedStatement st = null;
        Connection connection;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.prepareStatement("insert into user values (default, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, user.getLogin());
            st.setString(2, user.getName());
            st.setString(3, user.getLastname());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setInt(6, user.getRole_ID());
            st.setBoolean(7, user.getActive());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            LOGGER.error("create - " + e.getMessage());
        }
        LOGGER.debug("create() - OK");
    }


/*    CREATE TABLE USERS(ID INT PRIMARY KEY, LOGIN VARCHAR(50) NOT NULL UNIQUE, NAME VARCHAR(50), LAST_NAME VARCHAR(50),
    EMAIL VARCHAR(100), PASSWORD VARCHAR(200), ROLE_ID INTEGER, ACTIVE BOOLEAN);*/

    public User findUserByLogin(String name) {
        LOGGER.debug("findUserByLogin started");
        ConnectionPool pool;
        User user = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        Connection connection;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.prepareStatement("SELECT * FROM user where (login = ? and active = true)");
            st.setString(1, name);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                String nameFound = resultSet.getString("NAME");
                String passFound = resultSet.getString("PASSWORD");
                System.out.println(resultSet.getString("ID") + " " + nameFound);
                user = new User();
                user.setLogin(nameFound);
                user.setPassword(passFound);
            }
            resultSet.close();
            st.close();
        } catch (Exception e) {
            LOGGER.error("findUserByLogin - ");
        }
        LOGGER.debug("findUserByLogin() - OK");
        return user;
    }
}
