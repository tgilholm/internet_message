package text_gui.main;

// For testing purposes- open a client and a server

import javafx.application.Application;
import javafx.stage.Stage;
import text_gui.login.*;
import text_gui.server.Server;

import java.io.IOException;

/**
 * JavaFX App Simplified chatroom program- Allows users to chat to one another.
 * Users connect to the server and send messages. The server broadcasts the
 * messages to all the users connected
 */
public class RunClientAndServer extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage window) throws IOException
	{
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
		Server s = new Server();

		new Thread(() -> {
			try
			{
				s.runServer();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}
}