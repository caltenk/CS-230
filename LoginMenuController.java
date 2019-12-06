import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class LoginMenuController {

    private Stage stage;

    @FXML
    private Button loginButton;

    @FXML
    private Button newUserButton;


    @FXML
    private void handleLoginButton(ActionEvent event) {
        Login.fxmlval.add("loginmenu.fxml");
        stage = (Stage)loginButton.getScene().getWindow();
        Login.stageval.add(stage);
        new LoginCreateUser(stage, false);

    }

    @FXML
    private void handleNewUserButton(ActionEvent event) {
        Login.fxmlval.add("loginmenu.fxml");
        stage = (Stage)loginButton.getScene().getWindow();
        Login.stageval.add(stage);
        new LoginCreateUser(stage, true);
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
