import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * The login window for an existing user
 * @author George Manning
 * @version 1.0
 */
public class LoginCreateUser extends Application {
	
	private boolean newUser;
	private Stage stage;
	
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	
	/**
	 * The constructor for the LoginCreateUser UI.
	 * @param stage The stage this application will be shown on.
	 * @param newUser True is creating a new user, false if logging in as 
	 * existing user.
	 */
	public LoginCreateUser(Stage stage, Boolean newUser) {
		this.newUser = newUser;
		this.stage = stage;
		start(stage);
	}
	/**
	 * The start method for this application.
	 */
	public void start(Stage primaryStage) {
		Pane root = buildGUI();
		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	/**
	 * Builds the UI for this application
	 * @return The root pane for the application
	 */
	private Pane buildGUI() {
		//should make the numbers constants :(
		Label label1 = new Label("Username:");
		TextField textField = new TextField ();
		if (newUser) {
			textField.setPromptText("Choose your username");
		} else {
			textField.setPromptText("Enter your username");
		}
		
		GridPane root = new GridPane();
		
		Button submitButton = new Button("Enter");
		submitButton.setDefaultButton(true);
		
		submitButton.setOnAction(e -> {
			submitButtonAction(root, textField.getText());
		});
		GridPane.setConstraints(label1, 1, 1);
		GridPane.setConstraints(textField, 1, 2);
		GridPane.setConstraints(submitButton, 2, 2);
		
		root.getChildren().addAll(label1, textField, submitButton);
		root.setPadding(new Insets(200,100,200,100));
		
		return root;
	}
	
	/**
	 * Logs in the the user or creates a new user when the sumbmit button is pressed.
	 * @param root The root pane of the application.
	 * @param username The username the user entered.
	 */
	private void submitButtonAction(Pane root, String username) {
		//the default number of levels a new user has completed.
		final int DEFAULT_LEVELS_COMPLETE = 0;
		//The row of the gridpane the error message is in.
		final int ERROR_MESS_ROW = 4;
		//The column of the gridpane the error message is in.
		final int ERROR_MESS_COL = 1;
		
		Label errorMess = new Label();
		GridPane.setConstraints(errorMess, ERROR_MESS_COL, ERROR_MESS_ROW);
		if (username.equals("")) {
			errorMess.setText("please enter a username");
			root.getChildren().add(errorMess);
		} else if (newUser) {
			
			UserProfile user = new UserProfile(username, DEFAULT_LEVELS_COMPLETE);
			
			if (FileHandling.createUser(user)) {
				loadUser(user);
			} else {
				errorMess.setText(username + " already exists");
				root.getChildren().add(errorMess);
			}
			
		} else {
			try {
				UserProfile user = FileHandling.loadUser(username);
				new LevelSelect(stage, user);
			} catch (NullPointerException error) {
				errorMess.setText("Username not found.");
				root.getChildren().add(errorMess);
			}
		}
	}
	
	/**
	 * Loads an existing user. 
	 * @param username The users username.
	 * @throws NullPointerException If user doesn't exist.
	 */
	private void loadUser(UserProfile user) throws NullPointerException{
			new LevelSelect(stage, user);
	}
	

}
