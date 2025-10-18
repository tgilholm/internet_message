package text_gui.login;

import java.io.IOException;

public class LoginController
{
	public LoginModel Model;

	
	// Receives the username typed in from the LoginView
	public void loginPressed(String username) throws IOException
	{
		// If not empty, accept the username and pass it to the login method
		// Could potentially add a password system?
		if (!(username.equals("")))
		{
			Model.login(username);
		}
	}
}