package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Movie;
import models.Review;
import models.User;

public class DatabaseService 
{
	private static final String HOST = "jdbc:mysql://localhost:3306/",
								USERNAME = "root",
								PASSWORD = "christynatalia";
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
	 * @param user - new details about the user
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
	 * @param review - new details about the review
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
	/**
	 * Performs a select * operation on the Movie table.
	 * @return a List containing Movie objects
	 */
	public List<Movie> getAllMovies()
	{
		List<Movie> movies = new ArrayList<Movie>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_MOVIE);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Movie movie = new Movie(rs.getInt(1), rs.getString(2));
				movies.add(movie);
			}
			
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
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return movies;
	}
	/**
	 * Performs a select * operation on the User table.
	 * @return a List containing User objects
	 */
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_USER);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				User user = new User(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5));
				users.add(user);
			}
			
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
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return users;
	}
	/**
	 * Get the user with the selected username and password;
	 * @param username
	 * @param password
	 * @return a User object. It will return null if no User matches the username and password.
	 */
	public User getUser(String username, String password)
	{
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_USER 
									+ " WHERE "
									+ "username = " + "\'" + username + "\'"
									+ " AND "
									+ "password = " + "\'" + password + "\'");
			rs = ps.executeQuery();
			
			rs.next();		//Just get first occurance
			
			user = new User(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5));
			
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
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return user;
	}
	
	/**
	 * Performs a select * operation on the Review table.
	 * @return a List containing Review objects
	 */
	public List<Review> getAllReviews()
	{
		List<Review> reviews = new ArrayList<Review>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_REVIEW);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Review review = new Review(rs.getInt(1),
											rs.getInt(2),
											rs.getInt(3),
											rs.getString(4),
											rs.getFloat(5));
				reviews.add(review);
			}
			
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
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return reviews;
	}
	
	/**
	 * Returns a float number which is the average movie rating of a movie with its parameter 
	 * being the movie we want to count the average of its rating
	 * @param movie 
	 */
	public float countAverageMovieRating(int movieID) {
		float movieRating = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT AVG(ratingUser) from "+ TABLE_REVIEW
					+ " INNER JOIN " + TABLE_MOVIE + " ON review.movieID = Movie.movieID "
					+ "where Movie.movieID = " + movieID);
			rs = ps.executeQuery();
			
			rs.next();
			movieRating = rs.getFloat(1);
			
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
		return movieRating;
	}
	
	public String getUserReviewName(int userID)
	{
		String userName = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.prepStatement("SELECT username FROM " + TABLE_USER + " WHERE userID=" + userID);
			rs = ps.executeQuery();
			rs.next();
			userName = rs.getString(1);
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
			return userName;
		}
	
	public static void main (String args[])
	{
		DatabaseService ds = new DatabaseService();
		//Movie m = new Movie(15, "Lord of the Rings");
		//User u = new User(3, "John", "Garret", "sjcoaos", "pass");
		//Review r = new Review(0, m.getID(), u.getUserID(), "Epic Rohan", 5.0f);
//		
		//ds.insert(m);
		//ds.insert(u);
		//ds.insert(r);
		
//		Movie m = new Movie(1, "Lord of the Rings");
//		User u = new User(1, "Gary", "Garret", "asascdscds", "pass");
//		User u1 = new User(2, "Bruhyan", "Ultra", "bryan150929", "bruh");
//		Review r = new Review(1, m.getID(), u.getUserID(), "Saruman Lives", 4.5f);
//		Review r1 = new Review(2, m.getID(), u1.getUserID(), "LOL", 5.0f);
//		
//		ds.update(m.getID(), m);
//		ds.update(u.getUserID(), u);
//		ds.update(2, r);
//		ds.countAverageMovieRating(m);
	}
}
