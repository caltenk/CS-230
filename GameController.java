import com.sun.javafx.scene.traversal.Direction;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;



public class GameController {

    @FXML
    private BorderPane gamePane;

    private Scene scene;
    private Game game;
    private Player player;
    private ImageView playerView;
    private Pane pane;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private final int speed = 70;


    public void initialize() {
        player = new Player();
        game = new Game(gamePane, player);

        player.setPlayerX(2);
        player.setPlayerY(2);

        //game.drawGame();
    }

    public void setScene (Scene scene) {
        this.scene = scene;
        //scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> buttonPressed(event));
        setupBackground();

        scene.setOnKeyPressed(e -> processKey(e.getCode(), true));
        scene.setOnKeyReleased(e -> processKey(e.getCode(), false));

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = -1;
            @Override
            public void handle(long now) {
                long elapsedNanos = now - lastUpdate ;
                if (lastUpdate < 0) {
                    lastUpdate = now ;
                    return ;
                }
                double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
                double deltaX = 0 ;
                double deltaY = 0 ;
                if (right) deltaX += speed ;
                if (left) deltaX -= speed ;
                if (down) deltaY += speed ;
                if (up) deltaY -= speed ;
                playerView.setX(clampRange(playerView.getX() + deltaX * elapsedSeconds, 0, pane.getWidth() - 70));
                playerView.setY(clampRange(playerView.getY() + deltaY * elapsedSeconds, 0, pane.getHeight() - 70));
                lastUpdate = now ;
            }
        };

        timer.start();

    }

    private void processKey(KeyCode code, boolean on) {
        switch (code) {
            case LEFT:
                left = on ;
                break ;
            case RIGHT:
                right = on ;
                break ;
            case UP:
                up = on ;
                break ;
            case DOWN:
                down = on ;
                break ;
            default:
                break ;
        }
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

        playerView.setX((int) clampRange(playerView.getX() + (player.getPlayerX() * 70), 0, pane.getWidth() - player.getImage().getWidth()));
        playerView.setY((int) clampRange(playerView.getY() + (player.getPlayerY() * 70), 0, pane.getHeight() - player.getImage().getHeight()));
        game.drawGame();
        System.out.println(player.getPlayerY() + ", " + player.getPlayerX());
        e.consume();
    }

    private void setupBackground() {
        pane = game.drawGame();
        gamePane.setCenter(pane);
        Rectangle clip = new Rectangle();
        playerView = new ImageView(player.getImage());
        playerView.setX(((9 * 70) / 2));
        playerView.setY((9 * 70) / 2);



        pane.getChildren().add(playerView);

        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());

        clip.xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getX() - scene.getWidth() / 2, 0 , pane.getWidth() - scene.getWidth()),
                playerView.xProperty(), scene.widthProperty()
        ));
        clip.yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getY() - scene.getHeight() / 2, 0, pane.getHeight() - scene.getHeight()),
                playerView.yProperty(), scene.heightProperty()
        ));

        pane.setClip(clip);
        pane.translateXProperty().bind(clip.xProperty().multiply(-1));
        pane.translateYProperty().bind(clip.yProperty().multiply(-1));

    }

    private double clampRange(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }


}
