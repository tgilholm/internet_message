package text_gui.login;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

import text_gui.utilities.*;


/* Basic shell of a login page. Needs features:
 * 1. 	Implement login method when button pressed
 * 		at the moment just a username will do
 * 2.	Take the user to the next page where they can send messages
 * 3.	Move constants to a record & tidy up CSS
 */

public class LoginView 
{
	public LoginController Controller;
	private HBox hbRoot;
	private VBox loginPage;
	private Label lbPageTitle;
	TextField tfUsername;
	Button btnLogin;
	
	
	// Sets up the page where users type in a username they will chat with
	public void start(Stage window)
	{
		loginPage = createLoginPage();
		hbRoot = new HBox(loginPage);
		hbRoot.setAlignment(Pos.CENTER);
		
        Scene scene = new Scene(hbRoot, Utilities.loginScreenWidth, Utilities.loginScreenHeight);
        window.setScene(scene);
        window.setTitle("Login");
        window.show();
	}
	
	// Creates a VBox to sit within the window & display title, text box and login button
	private VBox createLoginPage() 
	{
		lbPageTitle = new Label("Enter your username");
		lbPageTitle.setStyle(Utilities.titleStyle);
		lbPageTitle.setAlignment(Pos.CENTER);
		
		tfUsername = new TextField();
		btnLogin = new Button("Login");
		btnLogin.setOnAction(event -> {
			try
			{
				buttonClicked(event);
			} catch (IOException e)
			{
				System.out.println("error entering username");
				e.printStackTrace();
			}
		});
		
		VBox vbLoginPage = new VBox(15, lbPageTitle, tfUsername, btnLogin);
		vbLoginPage.setAlignment(Pos.CENTER);
		vbLoginPage.setStyle(Utilities.paddingStyle);
		return vbLoginPage;
	}
	
	// Handle what happens when the user types in a username and presses the button
	private void buttonClicked(ActionEvent event) throws IOException
	{
		String username = tfUsername.getText();
		Controller.loginPressed(username);
	}
	
	
}