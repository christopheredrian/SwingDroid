
package edu.slu.swingdroid.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Chris
 */
public class ConnectionManager {

    private static ConnectionManager instance = null;
    private Connection connection;
    /* String that references to the database relative to the root of the project */
    private static final String CONNECTION_STRING = "jdbc:sqlite:database/swingdroid.sqlite";

    private ConnectionManager() {
    }

    /**
     * @return the single ConnectionManager instance
     */
    public static ConnectionManager getInstance() {
        return (instance == null) ? instance = new ConnectionManager() : instance;
    }

    /**
     * @return the Connection
     */
    public Connection getConnection() {
        if (connection == null) {
            if (openConnection()) {
                System.out.println("Connection is opened");
                return connection;
            } else {
                return null;
            }
        }
        return connection;
    }

    private boolean openConnection() {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(CONNECTION_STRING, config.toProperties());
            return true;
        } catch (SQLException ex) {
            
            System.err.println(ex);
            return false;
        }
    }

    /**
     * Closes the connection
     * Usually used before closing the application or access to the database
     **/
    public void close() {
        System.out.println("Closing connection");
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
