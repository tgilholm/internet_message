package text_gui.client;

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
	
	// Invoked when the client is started- attempts to connect to the server
	public void connectToServer()
	{
		cl.connectToServer("localhost", Utilities.port);
	}
	
	// Sends the message from the user to the server
	public void sendMessage(String message)
	{
		cl.sendMessage(message);
	}
	
	// Handles incoming messages from the server
	public void receiveMessage(String message)
	{
		// When a message is received, update the user's display so they can see it
	}
	
	// get message from client method
	
}

