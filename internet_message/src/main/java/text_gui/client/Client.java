package text_gui.client;

import java.io.*;
import java.net.*;

import text_gui.utilities.Utilities;


// Client class, directly interfaces with the server
public class Client
{
	public ClientModel Model;
	
	public static void main(String[] args) throws IOException
	{
		int port = Utilities.port;
		Socket clSocket;
		String host = "localhost";
		clSocket = new Socket(host, port);

		// Reading/writing to the stream 
		DataInputStream istream = new DataInputStream(clSocket.getInputStream());
		DataOutputStream ostream = new DataOutputStream(clSocket.getOutputStream());
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


		// Launches the istream in a different thread so as not to have to wait for
		// user input to receive server input
		// Passes the 
		Thread thread = new Thread()
		{
			public void run()
			{
				String s = "";
				System.out.println("istream reader thread started");
				try
				{
					while (clSocket.isConnected() && !((s = istream.readUTF()) == null))
					{
						System.out.println(s);
					}
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		thread.start();

		// While the server is open, send all input to the server
		while (true)
		{
			
		}
	}
	
	// Set the text to be sent to the server
	public void sendText(String input, DataOutputStream os)
	{
		try
		{
			os.writeUTF(input);
			os.flush();

		} catch (IOException e)
		{
			System.err.println("Couldn't get I/O");
		}
	}
}
