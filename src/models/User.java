package models;

public class User 
{
	//Fields
	private int userID;
	private String firstName, lastName, username, password;
	
	//Constructor
	public User(int userID, String firstName, String lastName, String username, String password) 
	{
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	//Getters
	/**
	 * Gets the User ID
	 * @return user ID
	 */
	public int getUserID() 
	{
		return userID;
	}
	/**
	 * Gets the first name of the User
	 * @return the user's first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	/**
	 * Gets the last name of the User
	 * @return the user's first name
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * Gets the username of the User
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * Gets the passwrod of the User account
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}

	//Setters
	/**
	 * Sets the username of the user
	 * @param username
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * Updates the user's last name
	 * @param lastName - the user's new last name
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	/**
	 * Updates the user's first name. Cannot be empty.
	 * @param firstName - the user's new first name
	 */
	public void setFirstName(String firstName)
	{
		if (!firstName.equals(""))
		{
			this.firstName = firstName;
		}
		else
		{
			System.err.println("Invalid operation. First name cannot be empty.");
		}
	}
	/**
	 * Sets the User's ID. Cannot be negative or 0.
	 * @param userID - the new User ID
	 */
	public void setUserID(int userID)
	{
		if (userID > 0)
		{
			this.userID = userID;
		}
		else
		{
			System.err.println("Invalid operation. User ID must be greater than zero.");
		}
	}
	/**
	 * Sets the user's account password. Cannot be empty or shorter than 6 characters.
	 * @param password - the new password
	 */
	public void setPassword(String password) 
	{
		if (!password.equals("") && password.length()>=6)
		{
			this.password = password;
		}
		else
		{
			System.err.println("Invalid operation. Password cannot be empty or shorter than 6 characters long..");
		}
	}
}
