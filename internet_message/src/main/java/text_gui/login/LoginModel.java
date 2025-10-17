package text_gui.login;

// The model for the login screen
// Receive the username entered by the user into the View, and open the client screen
public class LoginModel 
{
	public LoginView View;
	
	public void login(String username)
	{
		System.out.println(username);
	}
}