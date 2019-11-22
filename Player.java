import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Player {
    private int playerX;
    private int playerY;
    private Image image;
    private Game game;
    private Canvas canvas;

    private int boardSize;
    private int cellSize = 70;

    public Player(Canvas canvas) {
        this.image = new Image("character.png");
        this.canvas = canvas;
        this.boardSize = (int) canvas.getHeight();
    }

    public void move(Direction direction) {
        int newPlayerX;
        int newPlayerY;
        switch(direction) {
            case RIGHT:
                newPlayerX = playerX + 1;
                if (isMoveValid(newPlayerX)) {
                    playerX += 1;
                }
                else {
                    break;
                }
                break;
            case DOWN:
                newPlayerY = playerY + 1;
                if (isMoveValid(newPlayerY)) {
                    playerY += 1;
                }
                else {
                    break;
                }
                break;
            case LEFT:
                newPlayerX = playerX - 1;
                if (isMoveValid(newPlayerX)) {
                    playerX -= 1;
                }
                else {
                    break;
                }
                break;
            case UP:
                newPlayerY = playerY - 1;
                if (isMoveValid(newPlayerY)) {
                    playerY -= 1;
                }
                else {
                    break;
                }
                break;
        }
    }

    private boolean isMoveValid(int newPlayerCoord) {
        if ((newPlayerCoord * cellSize) > (boardSize - 10) || (newPlayerCoord * cellSize) < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getPlayerX() {
        return this.playerX;
    }

    public int getPlayerY() {
        return this.playerY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public Image getImage() {
        return this.image;
    }

}
