import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;

/**
 * The UI which asks the user if they wish to login as an existing user
 * or create a new user.
 * @author George Manning
 * @version 1.0
 */
public class Login extends Application {
	
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	
	//The number of buttons displayed on the menu;
	private static final int NUM_OF_BUTTONS = 2;
	
	//The stage this application and all the connected application will perform(?) on.
	private Stage stage;

	private MediaPlayer player;
	
	/**
	 * The start method for this applciaton.
	 * @param primaryStage The stage this application is shown on.
	 */
	public void start(Stage primaryStage) throws IOException {
		String musicFile = "music1.mp3";
		Media media = new Media(new File(musicFile).toURI().toString());
		player = new MediaPlayer(media);

		stage = primaryStage;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("loginmenu.fxml"));
		Pane root = (Pane) loader.load();
		LoginMenuController controller = (LoginMenuController)loader.getController();
		controller.setStage(stage);

		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		player.play();
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	/**
	 * Builds the UI for this application.
	 * @return The root pane for the stage.
	 */
	private Pane buildGUI() {
		VBox root = new VBox();
		root.setPrefWidth(WINDOW_WIDTH);

		
		
		Button loginButton = new Button("Login for a previous user");
		loginButton.setMinWidth(WINDOW_WIDTH);
		loginButton.setMinHeight(WINDOW_HEIGHT / NUM_OF_BUTTONS);
		
		loginButton.setOnAction(e -> {
			new LoginCreateUser(stage, false);
		});
		
		Button newUserButton = new Button("New user");
		newUserButton.setMinWidth(WINDOW_WIDTH);
		newUserButton.setMinHeight(WINDOW_HEIGHT / NUM_OF_BUTTONS);
		
		newUserButton.setOnAction(e -> {
			new LoginCreateUser(stage, true);
		});
		
		root.getChildren().addAll(loginButton, newUserButton);
		
		return root;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
