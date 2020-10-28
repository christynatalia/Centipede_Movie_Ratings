package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService 
{
	private static final String HOST = "jdbc:mysql://localhost:3306/CentipedeMovieRating?",
								USERNAME = "root",
								PASSWORD = "centipedeMan",
								DB_NAME = "CMR";
	
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
	/**
	 * Gets the database Connection object
	 * @return the connection to the MySQL
	 */
	public Connection getConnection()
	{
		return this.c;
	}
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
	/**
	 * Creates the MySQL database, unless it already exists.
	 */
	public void createDatabase()
	{
		Statement statement = null;
		ResultSet rs = null;
		
		try
		{
			statement = c.createStatement();
			
			//Create database
			rs = statement.executeQuery("CREATE DATABASE " + DB_NAME);
			
			//Create tables
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex){}
				rs = null;
			}
			
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch(SQLException ex) {}
				statement = null;
			}
		}
	}
}
