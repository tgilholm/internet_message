package text_gui.client;

// Interfaces with the Client class to send messages to the server
// When the Client GUI is opened, a connection is made to the server in the Client class
// This stays open while the user is logged in to the app
// Messages are passed to the stream writer of the client 
// and read in from the reader

public class ClientModel
{
	public ClientView View;
	public String text;
	
	// Invoked when the client is started- attempts to connect to the server
	public void connectToServer()
	{
		
	}
	
	public void sendMessage(String message)
	{
		
	}
	
}

