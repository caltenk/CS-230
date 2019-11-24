import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {

    Stage main;
    Scene scene1, scene2;
    public void start(Stage primaryStage) throws Exception {
        main = primaryStage;
        Label label1 = new Label("Hello!");
        Button button1 = new Button("Play");
        button1.setOnAction(e -> main.setScene(scene2));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 600, 300);

        Button button2 = new Button("Help");
        button2.setOnAction(e -> main.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        main.setScene(scene1);
        main.setTitle("Hello Friends");
        main.show();

    }


    public static void main(String args[]){
        launch(args);
    }
}
