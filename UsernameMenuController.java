import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Controller class to manage the username/login menu
 *
 * @author Cameron Altenkirch
 * @version 1.0
 */
public class UsernameMenuController {
    private Stage stage;
    private Boolean newUser;

    @FXML
    private TextField textField;

    @FXML
    private Button submitButton;

    @FXML
    private GridPane root;

    /**
     * Method to pass in current stage and run some initialization code.
     *
     * @param stage   Current stage to display scene on
     * @param newUser Boolean, true means this user is being created
     */
    public void setStage(Stage stage, Boolean newUser) {
        this.stage = stage;
        this.newUser = newUser;

        if (newUser) {
            textField.setPromptText("Choose your username");
        } else {
            textField.setPromptText("Enter your username");
        }
        submitButton.setDefaultButton(true);
    }

    /**
     * Method to handle what happens when submit button clicked
     */
    @FXML
    private void handleSubmitButton() {
        submitButtonAction(root, textField.getText());
    }

    /**
     * Code to be executed when submit button is clicked
     *
     * @param root     The pane of this button
     * @param username String entered in input box
     */
    private void submitButtonAction(Pane root, String username) {
        //the default number of levels a new user has completed.
        final int DEFAULT_LEVELS_COMPLETE = 0;
        //The row of the gridpane the error message is in.
        final int ERROR_MESS_ROW = 2;
        //The column of the gridpane the error message is in.
        final int ERROR_MESS_COL = 0;

        Label errorMess = new Label();
        GridPane.setConstraints(errorMess, ERROR_MESS_COL, ERROR_MESS_ROW);
        if (username.equals("")) {
            errorMess.setText("please enter a username");
            root.getChildren().add(errorMess);
        } else if (newUser) {

            UserProfile user = new UserProfile(username, DEFAULT_LEVELS_COMPLETE, "dev");

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
     *
     * @param username The users username.
     * @throws NullPointerException If user doesn't exist.
     */
    private void loadUser(UserProfile user) throws NullPointerException {
        new LevelSelect(stage, user);
    }

    /**
     * Method for handling behaviour of back button. Works with BackButton class.
     */
    @FXML
    private void handleBackButton() {
        BackButton bb = new BackButton();
        try {
            bb.back(Login.fxmlval.get(Login.fxmlval.size() - 1),
                    Login.stageval.get(Login.stageval.size() - 1));
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error occurred in back button");
        }
        Login.fxmlval.remove(Login.fxmlval.size() - 1);
        Login.stageval.remove(Login.stageval.size() - 1);
    }
}
