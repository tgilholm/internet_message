package text_gui.server;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;


import text_gui.utilities.Utilities;

// The server class for the text messaging client
// Keeps track of the amount of users connected, updates clients with new messages and updates the database periodically
// The server will open a new thread for each client that connects
public class Server
{
	// public MessageDB mdb;

	// Uses a CopyOnWriteArrayList- there are more read than write ops,
	// So this is ideal- it makes a new copy of the array for each change
	private static CopyOnWriteArrayList<ClientThread> clientList = new CopyOnWriteArrayList<>();

	public void runServer() throws IOException
	{
		Server.main(null);
	}
	
	public static void main(String[] args) throws IOException
	{
		try
		{
			int port = Utilities.port;
			Socket clientSocket = null;

			// Open the server socket to receive new clients
			ServerSocket serverSocket;
			serverSocket = new ServerSocket(port);
			System.out.println("Messaging server started");

			// Thread to handle admin messages
			new Thread(() -> {
				// Type messages into the console to broadcast a message to all clients
				Scanner scanner = new Scanner(System.in); // Starts a new scanner that reads from the user input

				while (true) // Infinite loop to constantly handle input
				{
					String svMessage = scanner.nextLine();
					broadcastMessage("[admin]: " + svMessage, null);
				}
				
			}).start();

			while (true)
			{
				// Listens for new clients, handles each one in a separate thread
				clientSocket = serverSocket.accept();
				System.out.println(String.format("Connected to client: %s", clientSocket)); // Displays ID of connected
																							// client

				// Adds the client to the working list
				ClientThread s = new ClientThread(clientSocket);
				clientList.add(s);
				new Thread(s).start(); // Starts a new client thread to handle the client
			}
		} catch (IOException e)
		{
			System.out.println("Error starting server");
			e.printStackTrace();
		}
	}

	public static void broadcastMessage(String message, ClientThread sender)
	{
		// For each client in the list, send them the message
		for (ClientThread t : clientList)
		{
			//if (t != sender) // If the client is not the one who sent the message, send it to them
			//{
				t.sendMessage(message);
			//}
		}
	}
	


// Each server thread needs to read the messages from the client it is connected to
// Send the message history back to the user
// Then (with synchronisation) save the message to the database

// Then, when another thread processes a message from the user, it first has to send the
// message history to the user

// Each server thread is able to access the global message history
// When a user sends a message to it, it provisionally sends it back to the user to display it on their screen
// It then attempts to save the message to the global history, which is a synchronised method.
// As multiple threads may be trying to save to the history simultaneously, this needs to be handled.
// The message history can be pulled from the database every couple of seconds
// If there is a change in the database, then refresh it. Otherwise, simply wait

	private static class ClientThread implements Runnable
	{
		private Socket s = null;
		private PrintWriter ostream; // For writing messages
		private BufferedReader istream; // For reading messages
		private String username;

		// Constructor for new ClientThread object, sets port number
		public ClientThread(Socket _s)
		{
			s = _s;
			try
			{
				ostream = new PrintWriter(s.getOutputStream(), true); // auto flush true
				istream = new BufferedReader(new InputStreamReader(s.getInputStream()));

			} catch (IOException e)
			{
				e.printStackTrace();
				System.out.println("IO error in new ClientThread");
			}
		}

		// Method for sending messages to a client
		public void sendMessage(String message)
		{
			ostream.println(message);
		}
		
		// Starts a new thread

		// Need to get username from client here
		@Override 
		public void run()
		{
			System.out.println("Server thread launched " + this);
			
			// Receive the username from the client
			try
			{
				username = istream.readLine(); // Waits for the client's username
				
				// Sends a message in the chat telling users that a new user has joined
				String svMessage = String.format(("User %s has joined the chat"), username);
				broadcastMessage("[JOIN]: " + svMessage, null);
				
				// Once the user has joined, handle their input
				String input;
				while ((input = istream.readLine()) != null) // until user disconnects, read line
				{
					// Sends the message back to all users in the format @user: message
					System.out.println("Sending message to all users: " + String.format("@%s: %s", username, input));
					broadcastMessage(String.format("@%s: %s", username, input), this);
				}
				
				// After the user disconnects, remove them from the list
				clientList.remove(this);
				svMessage = String.format("User %s has left the chat", username);
				broadcastMessage("[LEFT]: " + svMessage, null);
			} catch (IOException e)
			{
				e.printStackTrace();
				System.out.println("Server error");
			}

			finally // always executed after try even if catch
			{
				// Close connection to client
				try
				{
					System.out.println("Closing connection to client");
					istream.close();
					ostream.close();
					s.close();
				} catch (IOException e)
				{
					e.printStackTrace();
					System.out.println("Error closing socket");
				}
			}
		}
	}
}
