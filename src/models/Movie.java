package models;

public class Movie 
{
	//Fields
	private int movieID;
	private String movieName;
	
	//Constructor
	public Movie(int movieID, String movieName)
	{
		this.movieID = movieID;
		this.movieName = movieName;
	}
	
	//Getters
	/**
	 * Get the ID of the movie
	 * @return the movie ID
	 */
	public int getID()
	{
		return this.movieID;
	}
	/**
	 * Get the name of the movie
	 * @return the movie name
	 */
	public String getName()
	{
		return this.movieName;
	}
	
	//Setters
	/**
	 * Set the movie ID
	 * @param id
	 */
	public void setID(int id)
	{
		this.movieID = id;
	}
	/**
	 * Set the movie name
	 * @param name of the movie
	 */
	public void setName(String name)
	{
		this.movieName = name;
	}
}
