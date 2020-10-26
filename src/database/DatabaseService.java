package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService 
{
	private static final String HOST = "jdbc:mysql://localhost:3306/FinalProjectData?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "centipedeMan";
	private Connection c;
	
	//Constructor
	public DatabaseService()
	{
		//Connect to mysql database
		try
		{
			c = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	//Public Methods
	public PreparedStatement prepStatement(String query)
	{
		try
		{
			PreparedStatement prepStat = c.prepareStatement(query);
			return prepStat;
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
