import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelSelect extends Application{

	private UserProfile user;
	private Stage stage;
	
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	
	private static final int BUTTON_HEIGHT = 50;
	private static final int LEVEL_BUTTON_WIDTH = 400;
	private static final int LEADERBOARD_BUTTON_WIDTH = 100;
	
	private static final int LB_WINDOW_WIDTH = 200;
	private static final int LB_WINDOW_HEIGHT = 200;
	
	public LevelSelect(Stage stage, UserProfile user) {
		this.user = user;
		this.stage = stage;
		start(stage);
	}
	
	public void start(Stage primaryStage) {
		
		ScrollPane root = buildGUI();
		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setTitle("Level select");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private ScrollPane buildGUI() {
		ScrollPane root = new ScrollPane();
		VBox levelsAndLeaderboards = new VBox();
		Label messageOfTheDay = new Label(setMessageOfTheDay());
		levelsAndLeaderboards.getChildren().add(messageOfTheDay);
		
		int levelButtonNum = 1; 
		while (levelButtonNum <= (user.getHighestLevel() + 1)) {
			
			HBox hbox = new HBox();
			Button levelButton = new Button("level " + levelButtonNum);
			levelButton.setMinSize(LEVEL_BUTTON_WIDTH, BUTTON_HEIGHT);
			
			levelButton.setOnAction(e -> {
				loadLevel(levelButton.getText());
			});
			
			Button leaderboardButton = new Button("Leaderboard " + levelButtonNum);
			levelButton.setMinSize(LEADERBOARD_BUTTON_WIDTH, BUTTON_HEIGHT);
			
			leaderboardButton.setOnAction(e -> {
				loadLeaderBoard(leaderboardButton.getText());
			});
			
			hbox.getChildren().addAll(levelButton,leaderboardButton);
			levelsAndLeaderboards.getChildren().add(hbox);
			
			levelButtonNum ++; 
		}
		root.setContent(levelsAndLeaderboards);
		return root;
	}
	
	private String setMessageOfTheDay() {
		MessageOfTheDay message = new MessageOfTheDay();
		return message.getMessage();
	}
	private void loadLevel(String str) {

		char[] chrArray = str.toCharArray();
		int levelNum = Character.getNumericValue(chrArray[chrArray.length - 1]);
		Level level = FileHandling.loadLevel(levelNum);
		level.setLevelNum(levelNum);
		new LevelUI(stage, level, this.user);
	}
	
	private void loadLeaderBoard(String str) {
		char[] chrArray = str.toCharArray();
		int levelNum = Character.getNumericValue(chrArray[chrArray.length - 1]);
		LeaderBoard leaderboard = FileHandling.loadLeaders(levelNum);
		
        
		VBox newWindow = new VBox();
		for (int i = 0; i < leaderboard.getLeaders().length; i++) {
			UserProfile user = leaderboard.getLeaders()[i];
			
			Label label = new Label(user.getName() + ": " 
					+ leaderboard.getLeaderTimes()[i]);
			label.setMinSize(LB_WINDOW_WIDTH, LB_WINDOW_HEIGHT/(leaderboard.getLeaders().length + 1));
			
			newWindow.getChildren().add(label);
		}
		

        Scene secondScene = new Scene(newWindow, 230, 100);

        // New window (Stage)
        Stage newStage = new Stage();
        newStage.setTitle("Leaderboard");
        newStage.setScene(secondScene);

        // Set position of second window, related to primary window.
        newStage.setX(stage.getX() + 200);
        newStage.setY(stage.getY() + 100);

        newStage.show();
        
	}
	
}
