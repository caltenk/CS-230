
/**
 * holds the top 3 completion times of a level by users.
 *
 * @author Daniel Rothwell
 * @version 1.0
 */
public class LeaderBoard {

    //stored in order of leaderTimes[], quickest in [0], slowest in [2]
    private UserProfile[] leaders = new UserProfile[3];
    private double[] leaderTimes = new double[3];


    /**
     * constructs from a string as it would be stored in persistent files.
     *
     * @param leaderData
     */
    public LeaderBoard(String leaderData) {
        String[] splitData = leaderData.split(":");
        if (leaderData != "-") {
            if (splitData.length == 6) {
                for (int i = 0; i < 3; i++) {
                    if (!splitData[i * 2].equals("-")) {
                        leaders[i] = FileHandling.loadUser(splitData[i * 2]);
                        leaderTimes[i] = Float.parseFloat(splitData[i * 2 + 1]);
                    }
                }
            } else {
                System.out.println("ERROR - leaderboard construction failure, "
                        + "incorrect input format: " + leaderData + " :");
            }
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
     * times, the leaderboard stores leaders in order of their completion time.
     *
     * @param user
     * @param time
     * @return whether or not the user achieved a top 3 time.
     */
    public boolean addleader(UserProfile user, double time) {
        if (time > 0 && user != null) {
            if (leaderTimes[0] == 0 || leaderTimes[0] > time) {
                leaders[2] = leaders[1];
                leaderTimes[2] = leaderTimes[1];
                leaders[1] = leaders[0];
                leaderTimes[1] = leaderTimes[0];
                leaders[0] = user;
                leaderTimes[0] = time;
                return true;
            } else if (leaderTimes[1] == 0 || leaderTimes[1] > time) {
                leaders[2] = leaders[1];
                leaderTimes[2] = leaderTimes[1];
                leaders[1] = user;
                leaderTimes[1] = time;
                return true;
            } else if (leaderTimes[2] == 0 || leaderTimes[1] > time) {
                leaders[2] = user;
                leaderTimes[2] = time;
                return true;
            } else {
                return false;
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
                leadData += "-:";
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
    
    /**
     * Gets the array containing the leaders on the leaderboard.
     * @return The array of leaders.
     */
    public UserProfile[] getLeaders() {
        return this.leaders;
    }
    
    /**
     * Gets the array containing the times of the leaderboard.
     * @return The array containing the leader's times.
     */
    public double[] getLeaderTimes() {
        return this.leaderTimes;
    }
}
