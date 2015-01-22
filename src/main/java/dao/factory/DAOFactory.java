package dao.factory;

import connection.ConnectionPool;
import dao.user.UserDAO;

import java.sql.Connection;

public class DAOFactory {
    ConnectionPool pool = null;

    public UserDAO getUserDAO() {
        return new UserDAO(createConnection());
    }

    public Connection createConnection() {
        //todo Вынести в проперти файл
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:file:~/football";
        String user = "sa";
        String password = "";
        int poolSize = 10;

        if (pool == null) pool = ConnectionPool.getInstance(driver, url, user, password, poolSize);
        return pool.takeConnection();
    }
}
