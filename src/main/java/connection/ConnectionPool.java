package connection;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import util.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    static Property cfg = ConfigFactory.create(Property.class);
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock(true);
    private BlockingQueue<Connection> connectionQueue;

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        lock.lock();
        if (instance == null) {
            instance = new ConnectionPool();
        }
        lock.unlock();
        return instance;
    }

    private final void init() {
        LOGGER.debug("ConnectionPool init() started");

        String driver = cfg.driverName();
        String url = cfg.pathToDB();
        String user = cfg.login();
        String password = cfg.passDB();
        int poolSize = cfg.pool_size();

        connectionQueue = new LinkedBlockingDeque<>(poolSize);

        try {
            Class.forName(driver);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException");
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException");
        }

        LOGGER.debug("ConnectionPool init() finished");
    }

    public Connection takeConnection() {
        try {
            Connection connection = connectionQueue.poll(cfg.max_waiting_time(), TimeUnit.SECONDS);
            if (connection == null) {
                LOGGER.debug("Connection is NULL ");
            }
            return connection;
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException");
            return null;
        }
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
