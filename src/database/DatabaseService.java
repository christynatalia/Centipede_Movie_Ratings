package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Movie;
import models.Review;
import models.User;

public class DatabaseService 
{
	private static final String HOST = "jdbc:mysql://localhost:3306/",
								USERNAME = "root",
								PASSWORD = "";
	public static final String DB_NAME = "CMR",
								TABLE_MOVIE = "Movie",
								TABLE_REVIEW = "Review",
								TABLE_USER = "User";
	
	private Connection c;
	
	//Constructor
	public DatabaseService()
	{
		this.init();
	}
	
	//Private Methods
	/**
	 * Initializes the class. It checks for existance of database. If not create it. Tables are also checked and created if needed.
	 */
	private void init()
	{
		//Connect to mysql database
		try
		{
			//Try to connect to the database
			c = DriverManager.getConnection(HOST + DB_NAME, USERNAME, PASSWORD);
			System.out.println("Database connection successful");
			
			//Check for tables
			this.createTables();
		}
		catch (SQLException ex)
		{
			//If it goes here, most likely the database has not been created yet.
			System.err.println(ex.getMessage());
			try
			{
				//Create the database and try to connect again.
				c = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				this.createDatabase();
				this.init();
			} 
			catch (SQLException e) 
			{
				System.err.println(ex.getMessage());
				System.out.println("Database connection failed after creating table");
			}
		}
	}
	/**
	 * Creates the MySQL database.
	 * @throws SQLException
	 */
	private void createDatabase() throws SQLException
	{
		PreparedStatement prep = this.prepStatement("CREATE DATABASE " + DB_NAME);
		prep.execute();
		prep.close();
	}
	/**
	 * Creates the tables if they do not exist yet
	 * @throws SQLException
	 */
	private void createTables() throws SQLException
	{
		//Movie table
		PreparedStatement prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_MOVIE + "("
													+ "movieID int unsigned NOT NULL AUTO_INCREMENT,"
													+ "movieName varchar(255) NOT NULL,"
													+ "primary key (movieID))");
		prep.execute();
		
		//User table
		prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_USER + "("
									+ "userID int unsigned not null AUTO_INCREMENT,"
									+ "firstName varchar(255) not null,\r\n" + 
										"lastName varchar(255),\r\n" + 
										"username varchar(255) not null,\r\n" + 
										"password varchar(255) not null,"
									+ "primary key (userID))");
		prep.execute();
		
		//Review table
		prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_REVIEW + "("
				+ "reviewID int unsigned not null AUTO_INCREMENT,\r\n" + 
				"movieID int unsigned not null,\r\n" + 
				"userID int unsigned not null,\r\n" + 
				"description mediumtext,\r\n" + 
				"ratingUser decimal(10, 1) not null,\r\n" + 
				"primary key (reviewID),\r\n" + 
				"foreign key (movieID) references Movie(movieID) on update cascade,\r\n" + 
				"foreign key (userID) references User(userID) on update cascade)");
		
		prep.execute();
		prep.close();
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
	/**
	 * Prepares the MySQL Query statement before execution.
	 * @param query - the MySQL query command
	 * @return a PreparedStatement object
	 */
	public PreparedStatement prepStatement(String query)
	{
		try
		{
			PreparedStatement prepStat = c.prepareStatement(query);
			return prepStat;
		}
		catch (SQLException ex)
		{
			System.err.println(ex.getMessage());
			return null;
		}
	}
	/**
	 * Insert a Movie to the database
	 * @param movie
	 */
	public void insert(Movie movie)
	{
		PreparedStatement ps = null;
		try
		{		
			if (movie.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_MOVIE + "(movieName) values (\'" + movie.getName() + "\')");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_MOVIE + " values (" + movie.getID() + ", \'" + movie.getName() + "\')");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert a User to the database
	 * @param user
	 */
	public void insert(User user)
	{
		PreparedStatement ps = null;
		try
		{
			if (user.getUserID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_USER + 
										"(firstName, lastName, username, password) values "
										+ "(\'" + user.getFirstName() 
										+ "\', \'" + user.getLastName() + "\', "
										+ "\'" + user.getUsername() + "\', "
										+ "\'" + user.getPassword() + "\')");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_USER + " values "
											+ "(" + user.getUserID() + ", "
											+ "\'" + user.getFirstName() + "\', "
											+ "\'" + user.getLastName() + "\', "
											+ "\'" + user.getUsername() + "\', "
											+ "\'" + user.getPassword() + "\')");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert a Review to the database
	 * @param review
	 */
	public void insert(Review review)
	{
		PreparedStatement ps = null;
		try
		{
			if (review.getReviewID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_REVIEW + 
										"(movieID, userID, description, ratingUser) values "
										+ "(\'" + review.getMovieID() + "\', "
										+ "\'" + review.getUserID() + "\', "
										+ "\'" + review.getDesc() + "\', "
										+ "\'" + review.getUserRating() + "\')");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_REVIEW + " values "
										+ "(" + review.getReviewID() + ", "
										+ "\'" + review.getMovieID() + "\', "
										+ "\'" + review.getUserID() + "\', "
										+ "\'" + review.getDesc() + "\', "
										+ "\'" + review.getUserRating() + "\')");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	/**
	 * Update a Movie record
	 * @param id - the id of the movie record to be updated
	 * @param movie - new details about the movie
	 */
	public void update(int id, Movie movie)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_MOVIE 
									+ " SET "
										+ "movieID=" + movie.getID() + ", "
										+ "movieName=\'" + movie.getName() + "\'" 
									+ " WHERE movieID=" + id);
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Update a User record
	 * @param id - the id of the user record to be updated
	 * @param movie - new details about the user
	 */
	public void update(int id, User user)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_USER 
									+ " SET "
										+ "userID=" + user.getUserID() + ", "
										+ "firstName=\'" + user.getFirstName() + "\'," 
										+ "lastName=\'" + user.getLastName() + "\'," 
										+ "username=\'" + user.getUsername() + "\',"
										+ "password=\'" + user.getPassword() + "\'"
									+ " WHERE userID=" + id);
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Update a Review record
	 * @param id - the id of the review record to be updated
	 * @param movie - new details about the review
	 */
	public void update(int id, Review review)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_REVIEW 
									+ " SET "
										+ "reviewID=" + review.getReviewID() + ", "
										+ "movieID=" + review.getMovieID() + "," 
										+ "userID=" + review.getUserID() + "," 
										+ "description=\'" + review.getDesc() + "\',"
										+ "ratingUser=" + review.getUserRating() + ""
									+ " WHERE reviewID=" + id);
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException ex) {}
			}
		}
	}
	
	public static void main (String args[])
	{
		DatabaseService ds = new DatabaseService();
//		Movie m = new Movie(1, "Lord of the Rings");
//		User u = new User(1, "John", "Garret", "sjcoaos", "pass");
//		Review r = new Review(0, m.getID(), u.getUserID(), "Epic Rohan", 5.0f);
//		
//		ds.insert(m);
//		ds.insert(u);
//		ds.insert(r);
		
		Movie m = new Movie(1, "Lord of the Rings");
		User u = new User(1, "Gary", "Garret", "asascdscds", "pass");
		Review r = new Review(1, m.getID(), u.getUserID(), "Saruman Lives", 4.5f);
		
		ds.update(m.getID(), m);
		ds.update(u.getUserID(), u);
		ds.update(2, r);
	}
}
