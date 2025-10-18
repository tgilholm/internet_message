package text_gui.login;

import javafx.stage.Stage;
import text_gui.client.ClientController;
import text_gui.client.ClientModel;
import text_gui.client.ClientView;

// The model for the login screen
// Runs the login method, which will first check the user's name into the database
// If a returning user, display a short welcome message
public class LoginModel 
{
	public LoginView View;
	
	// 
	public void login(String username)
	{
		System.out.println(username);
		
		// Start up a client window by linking together the view, model and controller
		ClientView c_view = new ClientView();
		ClientController c_controller = new ClientController();
		ClientModel c_model = new ClientModel();
		
		c_view.Controller = c_controller;
		c_controller.Model = c_model;
		c_model.View = c_view;
		
		c_view.start(new Stage());
	}
}