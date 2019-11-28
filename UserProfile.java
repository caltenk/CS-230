/**
 * A class that describes each User.
 * @author Sean Beck
 * @version 1.0
 */
public class UserProfile {

    private String name;
    private int highestLevel;

    /**
     * Create a user profile with a given name and their highest level.
     * @param name The name of the user.
     * @param highestLevel The highest level achieved by the user.
     */
    public UserProfile(String name, int highestLevel) {
        this.name = name;
        this.highestLevel = highestLevel;
    }

    /**
     * Sets the name of the user.
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the highest level achieved by the user.
     * @param highestLevel The highest level achieved by the user.
     */
    public void setHighestLevel(int highestLevel) {
        this.highestLevel = highestLevel;
    }

    /**
     * Get the highest level achieved by the user.
     * @return highestLevel The highest level achieved by the user.
     */
    public int getHighestLevel() {
        return highestLevel;
    }
}