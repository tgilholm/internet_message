package text_gui.server;

import java.io.*;
import java.net.*;

// The server class for the text messaging client
// Keeps track of the amount of users connected, updates clients with new messages and updates the database periodically
// The server will open a new thread for each client that connects
public class Server
{
	public static void main(String[] args) throws IOException
	{
		int port = 1234; //placeholder
		
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		
		DataInputStream istream = null;
		DataOutputStream ostream = null;
		
		System.out.println("Messaging server started");
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Error in opening a server socket");
		}
		
		
		while (true)
		{
			// Listens for new clients, handles each one in a separate thread
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Connected to client");
				ServerThread s = new ServerThread(clientSocket);
				s.start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.out.println("Error in connecting to client");
			}
			
			
			// fill in 
			break;
		}
		
		clientSocket.close();
		serverSocket.close();
		istream.close();
		ostream.close();
		System.out.println("Closing messaging server");
	}
}

class ServerThread extends Thread
{
	Socket s = null;

	// Constructor for new ServerThread object, sets port number
	public ServerThread(Socket _s)
	{
		s = _s;
	}
	
	// Starts a new thread
	public void run() 
	{
		
	}
}
