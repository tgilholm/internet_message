package text_gui.client;

// Holds the values of each user- for example their username, colour etc.
public class User
{
	private String username;
	//private Color color;

	// User Constructor
	public User(String _username)
	{
		username = _username;
	}

	// Get & set methods for username & colour;
	public String getUser()
	{
		return username;
	}

	public void setUser(String newUsername)
	{
		username = newUsername;
	}

	//	public String getColour() {return colour;}
	//	public void setColour(Color newColour) {colour = newColour}
}
