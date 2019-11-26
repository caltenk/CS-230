/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

/**
 *
 * @author 984644
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

    public String toString() {
        return name + ":" + highestLevel;
    }

    /**
     * Outputs the result of to string
     * @return //TODO: figure our what to put here lol
     */
    public String fromString() {
        String[] output = toString().split(":");
        for (String word : output) {
            System.out.println(word);
        }
        return "";
    }

    public static void main(String[] args) {
        UserProfile t = new UserProfile("Jimmy", 5);
        System.out.println(t.fromString());
    }
}