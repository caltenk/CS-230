import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Game {

    private GraphicsContext gc;
    private Canvas canvas;
    private Player player;
    private int cellSize = 70;
    private Image floor;

    public Game(Canvas canvas, Player player) {
        this.canvas = canvas;
        this.player = player;
        this.gc = canvas.getGraphicsContext2D();
        this.floor = new Image("floor.png", 70, 70, true, true);
    }

    public void drawGame() {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        for(int x = 0; x <= 6; x++) {
            for(int y = 0; y <= 6; y++) {
                gc.drawImage(floor, x * cellSize, y * cellSize);
            }
        }
        gc.drawImage(player.getImage(),(player.getPlayerX() * cellSize), (player.getPlayerY() * cellSize));
    }

    public Canvas getCanvas() {
        return this.canvas;
    }
}
