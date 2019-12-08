import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for handling behaviour of back button.
 *
 * @author Cameron Altenkirch
 * @version 1.0
 */
public class BackButton {
    /**
     * Main method for this class.
     *
     * @param instance The name of the fxml file that the user is switching back to
     * @param stage    The current stage to display the previous menu on
     * @throws IOException If FXML file is not found
     */
    public void back(String instance, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(instance));
        Pane root = (Pane) loader.load();
        Scene scene = new Scene(root, Consts.WINDOW_WIDTH, Consts.WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}
