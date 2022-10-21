package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	
	private static Connection _conn;

    private static Connection createConnection() throws SQLException {
    	String url       = "jdbc:mysql://localhost:3306/buildabike";
        String user      = "client";
        String password  = "password";
        
        try {
        	return DriverManager.getConnection(url, user, password);
        }
        catch(SQLException ex) {
        	throw ex;
        }
    }
    
    public static Connection getConnection() {
    	try {
    	if (_conn == null || _conn.isClosed()) {
				_conn = createConnection();
    		}
    	}
    	 catch (SQLException ex) {
 			System.out.println(ex.getMessage());
 		}
    	
    	return _conn;
    }
}
