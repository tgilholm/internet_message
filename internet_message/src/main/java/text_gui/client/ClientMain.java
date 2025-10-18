// standalone Client screen for testing
// need to export javaFX to this package

package text_gui.client;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class ClientMain extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage window) throws IOException
	{
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
