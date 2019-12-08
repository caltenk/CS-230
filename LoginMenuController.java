import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class to manage the login/main menu
 *
 * @author Cameron Altenkirch
 * @version 1.0
 */
public class LoginMenuController {

    private Stage stage;

    @FXML
    private Button loginButton;

    @FXML
    private Button newUserButton;


    /**
     * Method to handle what happens when the login button is clicked
     */
    @FXML
    private void handleLoginButton() {
        //These lines are for the BackButton class to log history of menus
        Login.fxmlval.add("loginmenu.fxml");
        stage = (Stage) loginButton.getScene().getWindow();
        Login.stageval.add(stage);
        new LoginCreateUser(stage, false);
    }

    /**
     * Method to handle what happens when the new user button is clicked.
     */
    @FXML
    private void handleNewUserButton() {
        Login.fxmlval.add("loginmenu.fxml");
        stage = (Stage) loginButton.getScene().getWindow();
        Login.stageval.add(stage);
        new LoginCreateUser(stage, true);
    }

    /**
     * Method called when a new scene is created to pass in the current stage to this class.
     * The FXML initialize method did not necessarily work for this purpose as it fired just after
     * all FXML elements were injected
     *
     * @param stage Current stage where scene should be displayed
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
