
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Class for building and controlling the level select interface
 * This UI was not built using FXML due to it not being static
 * @author George Manning, Cameron Altenkirch
 * @version 2.0
 */
public class LevelSelect extends Application {

    private UserProfile user;
    private Stage stage;

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private static final int BUTTON_HEIGHT = 50;
    private static final int LEVEL_BUTTON_WIDTH = 400;
    private static final int LEADERBOARD_BUTTON_WIDTH = 100;

    private static final int LB_WINDOW_WIDTH = 200;
    private static final int LB_WINDOW_HEIGHT = 200;

    /**
     * constructor for the UI
     *
     * @param stage The stage the UI is shown on.
     * @param user The user of the UI.
     */
    public LevelSelect(Stage stage, UserProfile user) {
        this.user = user;
        this.stage = stage;
        start(stage);
    }

    /**
     * The start method for the application.
     */
    public void start(Stage primaryStage) {

        ScrollPane root = buildGUI();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setTitle("Level select");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Builds the GUI.
     *
     * @return The pane containing the GUI.
     */
    private ScrollPane buildGUI() {
        ScrollPane root = new ScrollPane();
        Button themeButton = new Button("Select Theme");
        Button deleteButton = new Button("Delete current profile");
        themeButton.setOnAction(e -> {
            setTheme();
        });

        deleteButton.setOnAction(e -> {
            showPopup();
        });

        VBox levelsAndLeaderboards = new VBox();
        Label messageOfTheDay = new Label(setMessageOfTheDay());
        levelsAndLeaderboards.getChildren().addAll(messageOfTheDay, deleteButton);

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

            hbox.getChildren().addAll(levelButton, leaderboardButton);
            levelsAndLeaderboards.getChildren().add(hbox);

            levelButtonNum++;
        }

        levelsAndLeaderboards.getChildren().add(themeButton);
        root.setContent(levelsAndLeaderboards);
        return root;
    }

    /**
     * Builds a popup window to confirm user deletion of profile
     */
    private void showPopup() {
        Button yesSure = new Button("Yes");
        Button noSure = new Button("No");
        Label sureLabel = new Label("Are you sure?");
        Popup confirmSure = new Popup();
        GridPane surePane = new GridPane();

        yesSure.setOnAction(e -> {
            FileHandling.deleteUser(this.user);
            BackButton bb = new BackButton();
            try {
                bb.back("loginmenu.fxml", this.stage);
            } catch(Exception ex) {
                System.out.println(ex);
                System.out.println("Could not load controller");
            }
            confirmSure.hide();
        });

        noSure.setOnAction(e -> {
            confirmSure.hide();
        });
        surePane.add(sureLabel, 0, 0);
        surePane.add(yesSure, 0, 1);
        surePane.add(noSure, 1,1);
        confirmSure.getContent().addAll(surePane);
        confirmSure.centerOnScreen();
        confirmSure.show(this.stage);

    }

    /**
     * Gets the message of the day.
     *
     * @return The message of the day.
     */
    private String setMessageOfTheDay() {
        MessageOfTheDay message = new MessageOfTheDay();
        return message.getMessage();
    }

    /**
     * Sets the user's themes to the selected value.
     */
    private void setTheme() {
        Stage newStage = new Stage();
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "dev",
                        "Synth"
                //place new theme names here or store em somewhere	
                );

        ComboBox<String> themes = new ComboBox<String>(options);
        themes.setValue("dev");
        BorderPane newWindow = new BorderPane();
        newWindow.setCenter(themes);

        Button selectButton = new Button("Select theme");
        selectButton.setOnAction(e -> {
            this.user.setTheme(themes.getValue());
            newStage.close();
        });

        newWindow.setBottom(selectButton);
        Scene secondScene = new Scene(newWindow, 230, 100);

        newStage.setTitle("Theme Select");
        newStage.setScene(secondScene);

        // Set position of second window, related to primary window.
        newStage.setX(stage.getX() + 200);
        newStage.setY(stage.getY() + 100);

        newStage.show();

    }

    /**
     * Loads a requested level.
     *
     * @param str The value of the level button.
     */
    private void loadLevel(String str) {

        char[] chrArray = str.toCharArray();
        int levelNum = Character.getNumericValue(chrArray[chrArray.length - 1]);
        //Loads the new level
        Level level = FileHandling.loadLevel(levelNum);
        try {
            Level savedLevel = FileHandling.loadProgress(user);
            if (savedLevel.getLevelNum() == level.getLevelNum()) {
                loadSavedLevel(savedLevel, level);
            } else {
                loadLevel(level);
            }
        } catch (NumberFormatException e) {
            loadLevel(level);
        }

    }

    /**
     * Asks the user if they want to load a saved state (if they have one).
     *
     * @param savedLevel The saved level state.
     * @param level The reset level state.
     */
    private void loadSavedLevel(Level savedLevel, Level level) {
        Stage newStage = new Stage();

        BorderPane newWindow = new BorderPane();
        Label message = new Label("A save state exits for this level."
                + "\nWould you like to continue where you left off?");

        Button yesButton = new Button("yes");
        Button noButton = new Button("no");

        yesButton.setOnAction(e -> {
            loadLevel(savedLevel);
            newStage.close();
        });

        noButton.setOnAction(e -> {
            loadLevel(level);
            newStage.close();
        });

        VBox centre = new VBox();
        centre.getChildren().addAll(message, yesButton, noButton);
        newWindow.setCenter(centre);
        Scene secondScene = new Scene(newWindow, 230, 100);

        // New window (Stage)
        newStage.setTitle("Continue?");
        newStage.setScene(secondScene);

        // Set position of second window, related to primary window.
        newStage.setX(stage.getX() + 200);
        newStage.setY(stage.getY() + 100);

        newStage.show();

    }

    /**
     * Load the levelUI for the requested level.
     *
     * @param level The level to be loaded and played.
     */
    private void loadLevel(Level level) {
        level.setUser(this.user);

        if (user.getTheme() != null) {
            level.setTheme(user.getTheme());
        } else {
            level.setTheme("Synth");
        }

        new LevelUI(stage, level, this.user);
    }

    /**
     * Loads a visual representation of a requested leaderboard.
     *
     * @param str The value of the leaderboard button.
     */
    private void loadLeaderBoard(String str) {
        char[] chrArray = str.toCharArray();
        int levelNum = Character.getNumericValue(chrArray[chrArray.length - 1]);
        LeaderBoard leaderboard = FileHandling.loadLeaders(levelNum);

        VBox newWindow = new VBox();
        for (int i = 0; i < leaderboard.getLeaders().length; i++) {
            UserProfile user = leaderboard.getLeaders()[i];
            Label label;

            if (user == null) {
                label = new Label("______ : ______");
            } else {
                label = new Label(user.getName() + ": " + leaderboard.getLeaderTimes()[i]);
            }
            label.setMinSize(LB_WINDOW_WIDTH, LB_WINDOW_HEIGHT / (leaderboard.getLeaders().length));

            newWindow.getChildren().add(label);
        }

        Scene secondScene = new Scene(newWindow, LB_WINDOW_WIDTH, LB_WINDOW_HEIGHT);

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
