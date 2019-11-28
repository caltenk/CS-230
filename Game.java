import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.swing.border.Border;

public class Game {

    private GraphicsContext gc;
    private Canvas canvas;
    private Player player;
    private int cellSize = 70;
    private final int GRID_SIZE = 9;
    private Image floor;
    private BorderPane gamePane;

    private Cell floorCell = new Cell(CellType.FLOOR);
    private Cell wallCell = new Cell(CellType.WALL);
    private Cell[][] board = {
            {wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell}
    };


    public Game(BorderPane gamePane, Player player) {
        this.gamePane = gamePane;
        this.player = player;
        this.floor = new Image("floor.png", 70, 70, true, true);
        player.setGame(this);
    }

    public Pane drawGame() {
        Canvas canvas = new Canvas(9 * 70, 9 * 70);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane pane = new Pane(canvas);

        pane.setMinSize(9 * 70, 9 * 70);
        pane.setPrefSize(9 * 70, 9 * 70);
        pane.setMaxSize(9 * 70, 9 * 70);

        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[0].length; x++) {
                gc.drawImage(board[x][y].getImage(), x * cellSize, y * cellSize);
            }
        }

        return pane;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
}
