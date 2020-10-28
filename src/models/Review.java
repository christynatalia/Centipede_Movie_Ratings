package models;

public class Review
{
	//Fields
	private int reviewID, movieID, userID;
	private String desc;
	private float userRating;

	//Constructor
	public Review(int reviewID, int movieID, int userID, String desc, float userRating)
	{
		this.reviewID = reviewID;
		this.movieID = movieID;
		this.userID = userID;
		this.desc = desc;
		this.userRating = userRating;
	}
	
	//Getters
	/**
	 * Get the ID of this review object
	 * @return the review ID
	 */
	public int getReviewID() 
	{
		return reviewID;
	}
	/**
	 * Get the movie ID referenced in the Review
	 * @return the movie ID
	 */
	public int getMovieID() 
	{
		return movieID;
	}
	/**
	 * Get the user ID referenced in the Review
	 * @return the User ID
	 */
	public int getUserID() 
	{
		return userID;
	}
	/**
	 * Get the description of the movie review
	 * @return the movie review description
	 */
	public String getDesc() 
	{
		return desc;
	}
	/**
	 * Get the user rating score for the movie in this Review
	 * @return the user rating score (out of 5)
	 */
	public float getUserRating() 
	{
		return userRating;
	}

	//Setters
	/**
	 * Sets the ID for this Review
	 * @param reviewID - the review ID
	 */
	public void setReviewID(int reviewID) 
	{
		this.reviewID = reviewID;
	}
	/**
	 * Sets the ID of the Movie to be referenced
	 * @param movieID - the ID of the movie
	 */
	public void setMovieID(int movieID)
	{
		this.movieID = movieID;
	}
	/**
	 * Sets the ID of the User to be referenced
	 * @param userID - the User ID
	 */
	public void setUserID(int userID) 
	{
		this.userID = userID;
	}
	/**
	 * Sets the description of the Review
	 * @param desc - the Review description
	 */
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * Sets the user rating score for the movie in this Review
	 * @param userRating - the movie's score rating (0.0 - 5.0)
	 */
	public void setUserRating(float userRating) 
	{
		if (userRating >=0 && userRating <= 5)
		{
			this.userRating = userRating;
		}
		else
		{
			System.err.println("Invalid score was entered. Please put a score between 0.0 and 5.0.");
		}
	}
	
}
