import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UI which asks the user if they wish to login as an existing user
 * or create a new user.
 * @author George Manning
 * @version 1.0
 */
public class Login extends Application {
	
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;

	public static List<String> fxmlval = new ArrayList<String>();
	public static List<Stage>stageval = new ArrayList<Stage>();

	
	//The stage this application and all the connected application will perform(?) on.
	private Stage stage;

	private MediaPlayer player;
	
	/**
	 * The start method for this applciaton.
	 * @param primaryStage The stage this application is shown on.
	 */
	public void start(Stage primaryStage) throws IOException {
		String musicFile = "src/music.mp3";
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
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
