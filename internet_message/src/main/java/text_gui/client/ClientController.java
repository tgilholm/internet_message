package text_gui.client;

public class ClientController
{
	public ClientModel Model;
	
	// Handles user input on the ClientView
	
	// When the user presses "send", pass the message to the model
	public void sendMessage(String message, String username)
	{
		if (!(message.equals("")))
		{
			Model.sendMessage(message, username);
		}
	}
	
	// tells the model to connect to the server
	public void connectToServer()
	{
		Model.connectToServer();
	}
	
}