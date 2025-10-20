package text_gui.client;

import java.io.*;
import java.net.*;

// Client class, directly interfaces with the server
public class Client
{
	int port;
	String host = "localhost";
	public ClientModel Model;
	public Socket clSocket;

	// Allows I/O between the server and client
	BufferedReader stdIn;
	DataOutputStream ostream;
	DataInputStream istream;

	// Connects this client to the server and opens the reader thread
	public void connectToServer(String h, int p)
	{
		// Attempts to open a new socket
		try
		{
			clSocket = new Socket(h, p);
			ostream = new DataOutputStream(clSocket.getOutputStream());
			stdIn = new BufferedReader(new InputStreamReader(System.in));

			// Starts the reader thread
			ReaderThread rt = new ReaderThread();
			rt.start();

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String input)
	{
		// Will not work until connected to server
		if (clSocket.isConnected())
		{
			try
			{
				ostream.writeUTF(input);
				ostream.flush();

			} catch (IOException e)
			{
				System.err.println("Couldn't get I/O");
			}
		} else
		{
			System.out.println("Cannot send message until client is connected to server");
		}
	}

	class ReaderThread extends Thread
	{
		// Opens another thread to read the messages from the server & update the model;
		public void run()
		{
			// Holds text received from the server
			String serverOutput = "";

			System.out.print("reader thread started");

			try
			{
				while (clSocket.isConnected() && !((serverOutput = stdIn.readLine()) == null))
				{
					// When a new message is read in, call the readMessage method in the model
					Model.receiveMessage(serverOutput);
					System.out.println(serverOutput);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}
}