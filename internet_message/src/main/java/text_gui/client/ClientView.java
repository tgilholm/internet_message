package text_gui.client;

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
	private TextField textInput;
	private TextArea activeUserList;
	private Button sendButton;

	public void start(Stage window)
	{
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

	private VBox createTextScreen()
	{
		VBox vbTextScreen = new VBox();
		vbTextScreen.setPrefWidth(Utilities.clientScreenWidth / 2);
		return vbTextScreen;
	}

}