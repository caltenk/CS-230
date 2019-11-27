/**
 * Class which defines a level which can be played by the user
 * @author Sean Beck, George Manning
 * @version 1.0
 */
public class Level {

    //TODO: Need to implement saving.
    private Board board;
    private Player player;
    private Enemy[] enemies;

    public Level(Board board, Player player, Enemy[] enemies){
        this.board = board;
        this.player = player;
        this.enemies = enemies;
    }

    /**
     * Preforms one turn of the level
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
