package text_gui.main;

import javafx.application.Application;
import javafx.stage.Stage;
import text_gui.login.*;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage window) throws IOException
	{
		// To start with, starts the server and opens an instance of the login GUI
		//startServer();
		startLoginGUI();
		
	}

	
	// Starts the page for a user to type in their username
	private void startLoginGUI()
	{
		// Instantiates the View, Model and Controller of the login page
		
		LoginView l_view = new LoginView();
		LoginController l_controller = new LoginController();
		LoginModel l_model = new LoginModel();
		
		l_view.Controller = l_controller;
		l_controller.Model = l_model;
		l_model.View = l_view;
		
		l_view.start(new Stage());
	}

	
	// Starts the messaging server
//	private void startServer()
//	{
//
//	}

}