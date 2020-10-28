package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test 
{
	
	
	public static void main (String args[])
	{
		Connection conn = null;
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost:3306/test?" +
		                                   "user=root&password=");
		    System.out.print("Connected");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
