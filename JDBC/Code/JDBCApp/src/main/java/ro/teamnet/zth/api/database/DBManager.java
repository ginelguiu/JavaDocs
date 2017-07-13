package ro.teamnet.zth.api.database;

import java.sql.*;

/**
 * Created by Ginel.Guiu on 7/13/2017.
 */
public class DBManager {
    private DBManager() throws UnsupportedOperationException{

    }
    private static final String CONNECTION_STRING = "jdbc:oracle:thin:@"+DBProperties.IP+":"+DBProperties.PORT+":xe";
    private static void registerDriver() throws ClassNotFoundException {
        Class.forName(DBProperties.DRIVER_CLASS);
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        registerDriver();
        Connection con = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        return con;
    }

    public static boolean checkConnection(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT 1 FROM DUAL");
        if(resultSet.next())
            return true;
        return false;
    }
}
