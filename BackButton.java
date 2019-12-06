import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BackButton {
    public void back(String instance, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(instance));
        Pane root = (Pane) loader.load();
        Scene scene = new Scene(root, Consts.WINDOW_WIDTH, Consts.WINDOW_HEIGHT);


        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
