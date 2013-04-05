package shop.myConnection;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 3. 28.
 * Time: 오후 3:33
 * To change this template use File | Settings | File Templates.
 */
public class MyConnection {

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/study";
        String usr = "study";
        String pwd = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, usr, pwd);
            connection.setAutoCommit(false);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void connException(Connection connection) {
        try {
            if(connection != null)
                connection.rollback();
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); connection = null; } catch ( Exception e ) {}
        }
    }
    public static void endConnection(Connection connection) {
        try {
            if(connection != null)
                connection.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); connection = null; } catch ( Exception e ) {}
        }
    }
}
