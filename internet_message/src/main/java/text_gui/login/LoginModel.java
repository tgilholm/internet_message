package text_gui.login;


import javafx.stage.Stage;
import text_gui.client.*;

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
		ClientModel c_model = new ClientModel(); // Model for the Controller class
		
		c_view.Controller = c_controller;
		c_model.View = c_view;
		c_controller.Model = c_model;
		
		// Links together the client and the model
		Client cl = new Client();
		cl.Model = c_model;
		c_model.cl = cl;
		
		c_view.start(new Stage());
	}
}