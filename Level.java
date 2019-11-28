
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
        if (splitData.length >= 2) {
            board = new Board(splitData[0]);
            player = new Player(splitData[1]);
            for (int i = 2; i < splitData.length; i++) {
                enemyData = splitData[i].split(";");
                enemyType = EnemyType.valueOf(enemyData[0]);
                switch (enemyType) {
                    case STRAIGHT_LINE:
                        enemies[i - 2] = new StraightLineEnemy(splitData[i]);
                        break;

                    case WALL_FOLLOWING:
                        enemies[i - 2] = new WallFollowingEnemy(splitData[i]);
                        break;

                    case DUMB_TARGETING:
                        enemies[i - 2] = new DumbTargetingEnemy(splitData[i]);
                        break;

                    case SMART_TARGETING:
                        enemies[i - 2] = new SmartTargetingEnemy(splitData[i]);
                        break;

                    default:
                        System.out.println("ERROR - enemy construction failure");

                }

            }
        } else {
            System.out.println("ERROR - level construction failure");
        }
    }

    /**
     * suggested to allow filehandling -Dan note: not compiled with Enemy class,
     * Enemy class may require its own constructor and toString methods.
     *
     * @param levelData
     */
    @Override
    public String toString() {
        String levelData = "";
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

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                levelData += enemies[i].toString();
            }
            if (i < enemies.length - 1) {
                levelData += ":";
            }
        }
        return levelData;
    }

    /**
     * Preforms one turn of the level
     *
     * @param playerDirection The direction the player will move in
     */
    public void play(Direction playerDirection) {
        player.move(playerDirection, board);
        for (Enemy elem : enemies) {
            Direction direction = elem.calculateDirection(board);
            elem.move(direction);
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

    /**
     *
     * @return
     */
    public Board getBoard() {
        return this.board;
    }
}
