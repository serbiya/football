package connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    private String DRIVER_NAME;
    private BlockingQueue<Connection> connectionQueue;
    private String URL;
    private String user;
    private String password;
    private int maxConn;

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static Lock lock = new ReentrantLock(true);

    private ConnectionPool(String driverName, String url, String user, String password, int maxConn) {
        this.DRIVER_NAME = driverName;
        this.URL = url;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
        initDriver();
    }

    private void initDriver() {
        connectionQueue = new LinkedBlockingDeque<>(maxConn);
        try {
            Driver driver = (Driver) Class.forName(DRIVER_NAME).newInstance();
            DriverManager.registerDriver(driver);
            for (int i = 0; i < maxConn; i++) {
                Connection connection = DriverManager.getConnection(URL, user, password);
                connectionQueue.add(connection);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static synchronized public ConnectionPool getInstance(String driverName, String url, String user, String password,
                                                          int maxConn) {
        lock.lock();
        if (instance == null) {
            instance = new ConnectionPool(driverName, url, user, password, maxConn);
        }
        lock.unlock();
        return instance;
    }

    public Connection takeConnection() {
        //Property cfg = ConfigFactory.create(Property.class);//todo
        try {
            Connection connection = connectionQueue.poll(/*cfg.max_waiting_time()*/5, TimeUnit.SECONDS);//todo
            if (connection == null) {
                LOGGER.debug("Connection is NULL ");
            }
            return connection;
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException");
            return null;
        }
    }

    private Connection newConnection() {
        Connection con;
        try {
            if (user == null) {
                con = DriverManager.getConnection(URL);
            } else {
                con = DriverManager.getConnection(URL,
                        user, password);
            }
        } catch (SQLException e) {
            return null;
        }
        return con;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connectionQueue.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException in releaseConnection()");
            }
        }
    }

    public void shutDown() {
        Iterator<Connection> iterator = connectionQueue.iterator();
        while (iterator.hasNext()) {
            Connection connection = iterator.next();
            try {
                connection.close();
                iterator.remove();
            } catch (SQLException e) {
                LOGGER.error("SQLException in shutDown()");
            }
        }
        LOGGER.debug("Pool is shut down");
    }

}
