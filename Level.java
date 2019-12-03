
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

/**
 * Class which defines a level which can be played by the user
 *
 * @author Sean Beck, George Manning
 * @version 1.0
 */
public class Level {

    //TODO: Need to implement saving.
    private Board board;
    private Player player;
    private Enemy[] enemies;
    private int levelNum;

    public Level(Board board, Player player, Enemy[] enemies) {
        this.board = board;
        this.player = player;
        this.enemies = enemies;
    }

    /**
     * suggested to allow filehandling -Dan note: not compiled with Enemy class,
     * Enemy class may require its own constructor and toString methods.
     *
     * @param levelData
     */
    public Level(String levelData) {
        String[] splitData = levelData.split(":");
        String[] enemyData;
        EnemyType enemyType;

        levelNum = Integer.parseInt(splitData[0]);

        if (splitData.length >= 3) {
            board = new Board(splitData[1]);
            player = new Player(splitData[2]);

        } else {
            System.out.println("ERROR - level construction failure");
        }

        if (splitData.length > 3) {
            enemies = new Enemy[splitData.length - 3];
            for (int i = 3; i < splitData.length; i++) {
                enemyData = splitData[i].split(";");
                enemyType = EnemyType.valueOf(enemyData[0]);
                switch (enemyType) {
                    case STRAIGHT_LINE:
                        enemies[i - 3] = new StraightLineEnemy(splitData[i]);
                        break;

                    case WALL_FOLLOWING:
                        enemies[i - 3] = new WallFollowingEnemy(splitData[i]);
                        break;

                    case DUMB_TARGETING:
                        enemies[i - 3] = new DumbTargetingEnemy(splitData[i]);
                        break;

                    case SMART_TARGETING:
                        enemies[i - 3] = new SmartTargetingEnemy(splitData[i]);
                        break;

                    default:
                        System.out.println("ERROR - enemy construction failure");
                }
            }
        }
    }

    /**
     * generates a level for testing of normal play, has no enemies.
     */
    public Level() {
        Cell[][] cells = Board.blankBoard(11, 9);

        cells[2][2] = new Cell(CellType.FIREBOOTS);
        cells[2][4] = new Cell(CellType.FIRE);

        cells[4][2] = new Cell(CellType.FLIPPERS);
        cells[4][4] = new Cell(CellType.WATER);

        cells[6][2] = new Cell(CellType.RED_KEY);
        cells[6][4] = new Cell(CellType.RED_DOOR);

        cells[8][2] = new Teleporter(8, 4);
        cells[8][4] = new Teleporter(8, 2);

        cells[9][3] = new Cell(CellType.GOAL);

        cells[2][6] = new Cell(CellType.TOKEN);
        cells[3][6] = new Cell(CellType.TOKEN);
        cells[4][6] = new Cell(CellType.TOKEN);
        cells[5][6] = new Cell(CellType.TOKEN);
        cells[6][6] = new Cell(CellType.TOKEN);

        cells[7][6] = new TokenDoor(3);
        cells[8][6] = new TokenDoor(1);

        board = new Board(cells, 11, 9, 9, 3);
        player = new Player(1, 3);
        enemies = null;
    }

    public static void main(String[] args) {
        Level level = new Level();
        String test = level.toString();
        Level compare = FileHandling.loadLevel(1);
        System.out.println(test.equals(compare.toString()));
    }

    public void setTheme(String theme) {
        board.setTheme(theme);
        player.setImage(new Image(theme + "//PLAYER.png"));
        
    }

    /**
     * suggested to allow filehandling -Dan note: not compiled with Enemy class,
     * Enemy class may require its own constructor and toString methods.
     *
     * @param levelData
     */
    @Override
    public String toString() {
        String levelData = Integer.toString(levelNum) + ":";
        if (board != null) {
            levelData += board.toString() + ":";
        } else {
            return null;
        }

        if (player != null) {
            levelData += player.toString() + ":";
        } else {
            return null;
        }

        if (enemies != null) {
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    levelData += enemies[i].toString();
                }
                if (i < enemies.length - 1) {
                    levelData += ":";
                }
            }
        }
        return levelData;
    }

    /**
     * Plays one rotation of the level. Using the user inputed direction.
     *
     * @param event The key key pressed by the user to move the player.
     */
    public void play(KeyEvent event) {
        switch (event.getCode()) {

            case RIGHT:
                play(Direction.RIGHT);
                break;
            case LEFT:
                play(Direction.LEFT);
                break;
            case UP:
                play(Direction.UP);
                break;
            case DOWN:
                play(Direction.DOWN);
                break;
            default:
                // Do nothing
                break;
        }
    }

    /**
     * Works out if the has been killed.
     *
     * @return True is player has been killed, false otherwise.
     */
    public boolean isPlayerDead() {
        for (Enemy elem : enemies) {
            if (elem.getXCoord() == player.getXCoord()
                    && elem.getYCoord() == player.getYCoord()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Works out if the player has reached the goal.
     *
     * @return True if the player has reached the goal false otherwise.
     */
    public boolean hasPlayerWon() {
        return (player.getXCoord() == board.getGoalX()
                && player.getYCoord() == board.getGoalY());
    }

    public void setLevelNum(int num) {
        this.levelNum = num;
    }

    public int getLevelNum() {
        return this.levelNum;
    }

    /**
     *
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Enemy[] getEnemies() {
        return this.enemies;
    }

    /**
     * Preforms one turn of the level
     *
     * @param playerDirection The direction the player will move in
     */
    private void play(Direction playerDirection) {
        player.move(playerDirection, board);
        for (Enemy elem : enemies) {
            Direction direction = elem.calculateDirection(board);
            elem.move(direction);
        }

        updateBoard();
    }

    /**
     * Updates the board to turn the cell the player is standing on to a ground
     * cell, where apllicable
     */
    private void updateBoard() {
        CellType playerCell = board.getCell(player.getXCoord(), player.getYCoord()).getType();
        switch (playerCell) {
            case RED_DOOR:
            case BLUE_DOOR:
            case GREEN_DOOR:
            case TOKEN_DOOR:
            case TOKEN:
            case RED_KEY:
            case BLUE_KEY:
            case GREEN_KEY:
            case FLIPPERS:
            case FIREBOOTS:
                board.updateCell(player.getXCoord(), player.getYCoord());
                break;
            default:
                break; //no nothing
        }

    }
}
