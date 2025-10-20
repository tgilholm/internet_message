package text_gui.client;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import text_gui.utilities.Utilities;

public class ClientView
{
	public ClientController Controller;
	
	private final int columnWidth = Utilities.clientScreenWidth / 2;
	
	private Label lbPageTitle;
	private Label lbActiveUsers;
	private HBox hbRoot;
	private VBox activeUsers;
	private VBox textScreen;
	private TextArea activeUserList;
	private TextArea messageHistory;
	private TextField textInput;
	private Button sendButton;
	private String username;
	
	public ClientView(String _username)
	{
		username = _username;
	}
	

	public void start(Stage window)
	{
		// First, connect the client to the server
		Controller.connectToServer();
		
		
		// Instantiate both halves of the screen
		activeUsers = createActiveUsersWindow();
		textScreen = createTextScreen();

		// Divide the screen into halves with a line 
		Line line = new Line(0, 0, 0, Utilities.clientScreenHeight);
		line.setStrokeWidth(4);
		line.setStroke(Color.BLACK);

		// Put the line into its own VBox
		VBox lineBox = new VBox(line);
		lineBox.setPrefWidth(4);
		lineBox.setAlignment(Pos.CENTER);

		// Add all the constituent parts to the HBox that makes up the layout
		hbRoot = new HBox(10, activeUsers, lineBox, textScreen);
		hbRoot.setAlignment(Pos.CENTER);

		// Add the scene to the window & start
		Scene scene = new Scene(hbRoot, Utilities.clientScreenWidth, Utilities.clientScreenHeight);
		window.setScene(scene);
		window.setTitle("Messaging Client");
		window.setResizable(false);
		window.show();

	}

	// Left side of the screen- title, number of active users, names of those users in a list (with colours)
	private VBox createActiveUsersWindow()
	{
		// Put the title in the top left of the screen
		lbPageTitle = new Label("Text Messenger Client");
		lbPageTitle.setStyle(Utilities.titleStyle);

		// New HBox with a label and TextArea
		lbActiveUsers = new Label("Active Users: ");
		lbActiveUsers.setStyle(Utilities.normalStyle);

		// Create a textArea to display active users
		activeUserList = new TextArea();
		activeUserList.setEditable(false);
		activeUserList.setPrefSize(columnWidth / 2, Utilities.clientScreenHeight - 150);
		
		// Add the layout elements to a VBox & return it
		VBox vbActiveUsersWindow = new VBox(15, lbPageTitle, lbActiveUsers, activeUserList);
		
		// Formatting stuff
		vbActiveUsersWindow.setPrefWidth(columnWidth);
		vbActiveUsersWindow.setAlignment(Pos.TOP_CENTER);
		vbActiveUsersWindow.setStyle("-fx-padding:15px");
		return vbActiveUsersWindow;
	}

	// Right side of the screen- message history, text input & "send" button
	private VBox createTextScreen()
	{
		// TextArea displaying the messages of all the users
		messageHistory = new TextArea();
		messageHistory.setEditable(false);
		messageHistory.setPrefSize(columnWidth / 2, Utilities.clientScreenHeight - 100);
		
		// Display the user input and send button next to each other
		textInput = new TextField();
		textInput.setPromptText("Send a message!");
		textInput.setStyle(Utilities.normalStyle);
		textInput.setPrefWidth(columnWidth - 125);
		
		sendButton = new Button("Send");
		sendButton.setStyle(Utilities.normalStyle);
		sendButton.setOnAction(event -> {
			try
			{
				sendMessage(event);
			} catch (IOException e)
			{
				System.out.println("Unable to send message");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		HBox sendBar = new HBox(10, textInput, sendButton);
	
		// Add the layout elements to the VBox
		VBox vbTextScreen = new VBox(15, messageHistory, sendBar);
		vbTextScreen.setPrefWidth(Utilities.clientScreenWidth / 2);
		vbTextScreen.setAlignment(Pos.TOP_CENTER);
		vbTextScreen.setStyle("-fx-padding:15px");
		return vbTextScreen;
	}
	
	// Handle what happens when the "send" button is pressed
	private void sendMessage(ActionEvent event) throws IOException
	{
		String message = textInput.getText();
		Controller.sendMessage(message, username);
	}
	
	// Refreshes the message history
	public void update(ArrayList<String> _messageHistory)
	{
		// Empties the message history, replaces it with the new one
		messageHistory.clear();
		for (String i : _messageHistory)
		{
			messageHistory.appendText(i);
		}
	}

}