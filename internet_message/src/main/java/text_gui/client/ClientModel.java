package text_gui.client;

import java.util.ArrayList;

import javafx.application.Platform;
import text_gui.utilities.Utilities;

// Interfaces with the Client class to send messages to the server
// When the Client GUI is opened, a connection is made to the server in the Client class
// This stays open while the user is logged in to the app
// Messages are passed to the stream writer of the client 
// and read in from the reader

public class ClientModel
{
	public ClientView View;
	public Client cl;
	public String text;
	
	// ArrayLists to hold the messages and active users
	public ArrayList<String> messageList;
	public ArrayList<String> activeUsers;
	
	// When the model is constructed, instantiate the messageList
	public ClientModel()
	{
		messageList = new ArrayList<String>();
		activeUsers = new ArrayList<String>();
	}
	
	// Invoked when the client is started- attempts to connect to the server
	public void connectToServer()
	{
		cl.connectToServer("localhost", Utilities.port);
		
		Platform.runLater(() -> {
			View.update(messageList);
		});
	}
	
	// Sends the message from the user to the server
	public void sendMessage(String message, String username)
	{
		// Sends message to server, clears the text box
		cl.sendMessage(message);
		
		// JavaFX only runs on one thread, all updates must be done on the JavaFX Application Thread
		//Platform.runLater creates a new runnable that will be run (at some point) in the javaFX thread
		// This prevents data synchronisation issues
		Platform.runLater(() -> {
			View.clearTextBox();
		});
	}
	
	// Handles incoming messages from the server
	public void receiveMessage(String message)
	{
		messageList.add(message + "\n");
		Platform.runLater(() -> {
			View.update(messageList);
		});
		
		// if message starts with [JOIN] or [LEFT], update the user list
		
		if (message.contains("[JOIN]"))
		{
			
		}
		
	}

	
		// When a message is received, update the user's display so they can see it
}

