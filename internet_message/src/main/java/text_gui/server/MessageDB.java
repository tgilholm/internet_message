package text_gui.server;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MessageDB
{
	// The message database itself
	private ArrayList<String> messageHistory;
	String filename = "message_history.txt";
	
	// Constructor- instantiates the ArrayList
	public MessageDB() throws IOException
	{
		messageHistory = new ArrayList<String>();
		messageHistory = readFromFile();
	}

	public void fileExists(File f) throws IOException
	{
		if (!f.exists())
		{
			f.createNewFile();
		}
	}

	// Reads the list of messages & saves them to the ArrayList
	public ArrayList<String> readFromFile() throws IOException
	{
		ArrayList<String> out = new ArrayList<String>();
		try
		{
			File f = new File(filename);
			fileExists(f);
			Scanner s = new Scanner(f);
			while (s.hasNextLine())
			{
				out.add(s.nextLine());
			}

		} catch (IOException e)
		{
			System.out.println("Error occurred reading from file");
		}

		return out;
	}

	// Saves a line to the file
	public void saveToFile(String in) throws IOException
	{
		fileExists(new File(filename));
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(in);
		writer.close();

	}

	// Attempts to write a new message into the history
	public synchronized void saveToHistory(String message) throws IOException
	{
		messageHistory.add(message);
		saveToFile(message);
	}
}
