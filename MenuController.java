import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button newButton;

    private Scene scene;
    private Stage stage;

    public void initialize() {
    }

    public void setScene (Scene scene) {
        this.scene = scene;
    }



    public void newGame () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GameController controller = new GameController();
        loader.setController(controller);
        Parent gamePane = loader.load();
        Scene gameScene = new Scene(gamePane);
        controller.setScene(gameScene);
        stage = (Stage) newButton.getScene().getWindow();
        stage.setScene(gameScene);

    }
}
