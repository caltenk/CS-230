import com.sun.javafx.scene.traversal.Direction;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController {

    @FXML
    private Canvas gameCanvas;

    private Scene scene;
    private Game game;
    private Player player;


    public void initialize() {
        System.out.println(gameCanvas);
        player = new Player(gameCanvas);
        game = new Game(gameCanvas, player);

        player.setPlayerX(0);
        player.setPlayerY(0);

        game.drawGame();
    }

    public void setScene (Scene scene) {
        this.scene = scene;
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> buttonPressed(event));
    }

    private void buttonPressed (KeyEvent e) {
        switch (e.getCode()) {
            case RIGHT:
                player.move(Direction.RIGHT);
                break;
            case DOWN:
                player.move(Direction.DOWN);
                break;
            case UP:
                player.move(Direction.UP);
                break;
            case LEFT:
                player.move(Direction.LEFT);
                break;
        }

        game.drawGame();
        e.consume();
    }


}
