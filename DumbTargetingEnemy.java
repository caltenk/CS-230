
public class DumbTargetingEnemy extends TargetingEnemy {

    private int longestDistanceToPlayer;

    /**
     * suggested to allow fileHandling -Dan
     *
     * @param enemyData
     */
    public DumbTargetingEnemy(String dumbEnemyData) {
        super(dumbEnemyData);
    }

    /**
     * suggested to allow fileHandling -Dan
     *
     * @param enemyData
     */
    @Override
    public String toString() {
        return EnemyType.DUMB_TARGETING + ";" + super.toString();
    }

    public int getLongestDistanceToPlayer() {
        return longestDistanceToPlayer;
    }

    public void setLongestDistanceToPlayer(int longestDistanceToPlayer) {
        this.longestDistanceToPlayer = longestDistanceToPlayer;
    }
}
