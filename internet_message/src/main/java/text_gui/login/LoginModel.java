package text_gui.login;

// The model for the login screen
// Runs the login method, which will first check the user's name in the database
// If a returning user, display a short welcome message
public class LoginModel 
{
	public LoginView View;
	
	
	// 
	public void login(String username)
	{
		System.out.println(username);
	}
}