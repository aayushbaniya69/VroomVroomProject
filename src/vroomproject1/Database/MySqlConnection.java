package vroomproject1.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySqlConnection handles connection to the MySQL database.
 */
public class MySqlConnection implements DbConnection {

    private final String username = "root";
    private final String password = "newpassword";
    private final String database = "vroom";
    private final String url = "jdbc:mysql://localhost:3306/" + database;

    /**
     * Opens and returns a database connection.
     */
    @Override
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // For older MySQL versions
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Alternative method to get a new connection, can be reused anywhere.
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Closes a given database connection.
     */
    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
