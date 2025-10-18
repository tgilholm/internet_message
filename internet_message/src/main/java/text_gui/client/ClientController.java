package text_gui.client;

public class ClientController
{
	public ClientModel Model;
	
	// Handles user input on the ClientView
	
	// When the user presses "send", pass the message to the model
	public void sendMessage(String message)
	{
		if (!(message.equals("")))
		{
			Model.sendMessage(message);
		}
	}
	
}