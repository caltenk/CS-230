/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * holds the top 3 completion times of a level by users.
 *
 * @author 984644
 * @version 1.0
 */
public class LeaderBoard {

    private UserProfile[] leaders = new UserProfile[3];
    private double[] leaderTimes = new double[3];

    /**
     * some basic tests.
     *
     * @param args
     */
    public static void main(String args[]) {
        LeaderBoard test = FileHandling.loadLeaders(1);
        System.out.println(test);
        System.out.println(new LeaderBoard(test.toString()));
    }

    /**
     * constructs from a string as it would be stored in persistent files.
     *
     * @param leaderData
     */
    public LeaderBoard(String leaderData) {
        String[] splitData = leaderData.split(":");
        if (splitData.length == 6) {
            for (int i = 0; i < 3; i++) {
                if (splitData[i * 2] != " ") {
                    leaders[i] = FileHandling.loadUser(splitData[i * 2]);
                }
                if (splitData[i * 2 + 1] != "0") {
                    leaderTimes[i] = Float.parseFloat(splitData[i * 2 + 1]);
                }
            }
        } else {
            System.out.println("ERROR - leaderboard construction failure, "
                    + "incorrect input format: " + leaderData + " :");
        }
    }

    /**
     * constructs an empty leaderBoard (UserProfiles are null, times are 0).
     */
    public LeaderBoard() {
    }

    /**
     * returns the longest completion time of the given level.
     *
     * @return longest completion time (0 if empty).
     */
    public double getSlowest() {
        for (int i = 2; i >= 0; i--) {
            if (leaderTimes[i] != 0) {
                return leaderTimes[i];
            }
        }
        return 0;
    }

    /**
     * adds a leader to the leaderboard if they have achieved a top 3 completion
     * times.
     *
     * @param user
     * @param time
     * @return whether or not the user achieved a top 3 time.
     */
    public boolean addleader(UserProfile user, double time) {
        if (time > 0 && user != null) {
            for (int i = 0; i < 3; i++) {
                if (leaderTimes[i] == 0 || leaderTimes[i] < time) {
                    leaders[i] = user;
                    leaderTimes[i] = time;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * generates a string to be stored in persistent files and loaded from at a
     * future point.
     *
     * @return the representing string.
     */
    @Override
    public String toString() {
        String leadData = "";

        for (int i = 0; i < 3; i++) {

            if (leaders[i] != null) {
                leadData += leaders[i].getName() + ":";
            } else {
                leadData += " ";
            }

            if (leaderTimes[i] != 0) {
                leadData += String.format("%.4g", leaderTimes[i]);
            } else {
                leadData += 0;
            }

            if (i < 2) {
                leadData += ":";
            }
        }

        return leadData;
    }
}
