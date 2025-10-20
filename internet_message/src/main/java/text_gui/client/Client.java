package text_gui.client;

import java.io.*;
import java.net.*;

import text_gui.utilities.Utilities;

// Client class, directly interfaces with the server
public class Client
{
	private static final String host = "localhost";
	private static final int port = Utilities.port;

	String username = "";
	public ClientModel Model;
	public Socket clSocket;

	// Allows I/O between the server and client
	private PrintWriter ostream;
	private BufferedReader istream;

	public Client(String _username)
	{
		username = _username;
	}

	// Connects this client to the server and opens the reader thread
	public void connectToServer(String h, int p)
	{
		try
		{
			clSocket = new Socket(h, p);
			ostream = new PrintWriter(clSocket.getOutputStream(), true);
			istream = new BufferedReader(new InputStreamReader(clSocket.getInputStream()));

			// On connecting, send the username to the server
			ostream.println(username);

			// Receives server responses in a new thread
			new Thread(() -> {
				try {
					String serverOutput;
					while (clSocket.isConnected() && !((serverOutput = istream.readLine()) == null))
					{
						Model.receiveMessage(serverOutput);
					}
				}
				catch (IOException e){
					e.printStackTrace();
				}
				finally {
					try
					{
						clSocket.close();
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();

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
			System.out.println("Sending message to server: " + input);
			ostream.println(input);
		} else
		{
			System.out.println("Cannot send message until client is connected to server");
		}
	}

}