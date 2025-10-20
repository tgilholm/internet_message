package text_gui.server;

import java.io.*;
import java.net.*;

import text_gui.utilities.Utilities;

// The server class for the text messaging client
// Keeps track of the amount of users connected, updates clients with new messages and updates the database periodically
// The server will open a new thread for each client that connects
public class Server
{
	public void startServer()
	{
		try
		{
			main(null);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		int port = Utilities.port;
		Socket clientSocket = null;
		ServerSocket serverSocket = null;

		DataInputStream istream = null;
		DataOutputStream ostream = null;

		System.out.println("Messaging server started");
		try
		{
			serverSocket = new ServerSocket(port);
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Error in opening a server socket");
		}

		while (true)
		{
			// Listens for new clients, handles each one in a separate thread
			try
			{
				clientSocket = serverSocket.accept();
				System.out.println("Connected to client");
				ServerThread s = new ServerThread(clientSocket);
				s.start();
			} catch (IOException e)
			{
				e.printStackTrace();
				System.out.println("Error in connecting to client");
			}
		}
	}
}

// Each server thread needs to read the messages from the client it is connected to
// Send the message history back to the user
// Then (with synchronisation) save the message to the database

// Then, when another thread processes a message from the user, it first has to send the
// message history to the user

// Just an echo server for now though!

class ServerThread extends Thread
{
	Socket s = null;
	String in;

	// Uses a BufferedReader for reading from stream and a PrintWriter to write to it
	BufferedReader istream = null;
	PrintWriter ostream = null;

	// Constructor for new ServerThread object, sets port number
	public ServerThread(Socket _s)
	{
		s = _s;
	}

	// Starts a new thread
	public void run()
	{
		// Open read/writers
		try
		{
			istream = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ostream = new PrintWriter(s.getOutputStream());

		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating read/writer");
		}

		// Read data from the client
		try
		{
			in = istream.readLine(); // Initial Read

			// use an interrobang as the "quit" character for now, Unicode U+203D
			String escape = new String(Character.toChars(0x203D));
			while (!(in == escape))
			{
				ostream.println(in); // sends the line back to the client, waits for another message
				ostream.flush();
				System.out.println("Server Response: " + in);
				in = istream.readLine();
			}

		} catch (IOException e)
		{

			e.printStackTrace();
			System.out.println("I/O Error in thread");
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
		// When user quits

	}
}
