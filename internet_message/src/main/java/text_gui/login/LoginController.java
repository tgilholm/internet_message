package text_gui.login;

import java.io.IOException;

public class LoginController
{
	public LoginModel Model;

	public void loginPressed(String username) throws IOException
	{
		if (!(username.equals("")))
		{
			Model.login(username);
		}
	}
}