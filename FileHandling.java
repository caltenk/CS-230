
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

/**
 * a class to carry out all file handling and directly related data processing.
 *
 * @author Daniel Rothwell
 * @version 2.0
 */
public class FileHandling {

    private static final File USER_PROFILES = new File("src" + File.separator +  "testFiles" + File.separator + "userProfiles.txt");
    private static final File GAME_LEVELS = new File("src" + File.separator +  "testFiles" + File.separator +  "levels.txt");

    private static FileReader reader;
    private static BufferedReader buffRead;
    private static FileWriter writer;
    private static BufferedWriter buffWrite;

    /**
     * a few basic tests.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(createUser(new UserProfile("dan", 1, "dev")));
        System.out.println(createUser(new UserProfile("bob", 1, "dev")));
        System.out.println(createUser(new UserProfile("jeff", 1, "dev")));
        System.out.println(loadUser("bob").getName());
        System.out.println(deleteUser(new UserProfile("jeff", 1, "dev")) + "\n");

        System.out.println(loadLevel(1));
        System.out.println(completeLevel(new UserProfile("bob", 1, "dev"), 2, 5.3));
        System.out.println(loadLeaders(1) + "\n");

        //System.out.println(saveProgress(new Level(""), new UserProfile("dan", 1, "dev")));
        System.out.println(loadProgress(new UserProfile("bob", 0, "dev")));
        levelTest();
    }

    /**
     * a basic level saving test
     */
    public static void levelTest() {
        Cell[][] cells;
        Board board;
        Player player;
        Enemy[] enemies;
        Level originalLevel;
        Level savedLevel;

        String levelSave;

        System.out.println("\n Level test:\n");

        CellType[] cellTypes = CellType.values();
        EnemyType[] enemyTypes = EnemyType.values();

        cells = new Cell[4][4];
        for (int i = 0; i < 14; i++) {
            cells[i / 4][i % 4] = new Cell(cellTypes[i]);
        }
        cells[3][2] = new TokenDoor(5);
        cells[3][3] = new Teleporter(2, 3);
        board = new Board(cells, 4, 4, 2, 3);

        enemies = new Enemy[4];
        enemies[0] = new StraightLineEnemy(1, 2, Direction.DOWN);
        enemies[1] = new WallFollowingEnemy(1, 2);
        enemies[2] = new DumbTargetingEnemy(1, 2, null);
        enemies[3] = new SmartTargetingEnemy(1, 2, null);

        player = new Player(4, 3);
        player.giveStuff();

        originalLevel = new Level(board, player, enemies, null);

        levelSave = originalLevel.toString();

        savedLevel = new Level(levelSave);

        System.out.println(levelSave.equals(savedLevel.toString()));

        System.out.println("\n End of level test");
    }

    /**
     * writes a new user record in USER_PROFILES if the username is original.
     *
     * @param username username the user wants to use.
     * @return success/failure.
     */
    public static boolean createUser(UserProfile user) {
        if (searchFile(USER_PROFILES, user.getName()) == null) {
            if (!appendRecord(USER_PROFILES, user.getName() + "," + user.getHighestLevel() + "," + user.getTheme() + ",-")) {
                System.out.println("ERROR - user creation failure, "
                        + "check user: " + user.getName() + " :");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * loads a UserProfile from USER_PROFILES if it exists there.
     *
     * @param username username of the user being searched for.
     * @return requested UserProfile or null if not found.
     */
    public static UserProfile loadUser(String username) {
        String[] userRecord = searchFile(USER_PROFILES, username).split(",");

        if (userRecord == null) {
            System.out.println("ERROR - user search failure.");
            return null;
        } else {
            return new UserProfile(userRecord[0], Integer.parseInt(userRecord[1]), userRecord[2]);
        }
    }

    /**
     * deletes a user record from USER_PROFILES if they exist there.
     *
     * @param user UserProfile of the user to delete
     * (note: deletes all with matching usernames).
     */
    public static boolean deleteUser(UserProfile user) {
        boolean success;
        if (!(success = editFile(USER_PROFILES, user.getName(), null))) {
            System.out.println("ERROR - delete user failure, "
                    + "check user: " + user.getName() + " :");
        }
        return success;
    }

    /**
     * loads a Level from GAME_LEVELS if it exists there.
     *
     * @param levelNum
     * @return the requested Level if found, null if not.
     */
    public static Level loadLevel(int levelNum) {
        String[] levelRecord = searchFile(GAME_LEVELS, Integer.toString(levelNum)).split(",");
        if (levelRecord.length < 3) {
            System.out.println("ERROR - search level failure.");
            return null;
        } else {
            return new Level(levelRecord[2]);
        }
    }

    /**
     * checks if the user has advanced a level and updates their
     * 'level reached' in USER_PROFILES (assuming the input UserProfile already
     * has their 'level reached' updated), checks if they have set a high score
     * for the level and updates the leader board in GAME_LEVELS if so.
     *
     * @param user UserProfile of the user who completed the level.
     * @param levelNum which level they completed.
     * @param time the time in which the level was completed.
     */
    public static boolean completeLevel(UserProfile user, int levelNum, double time) {

        String[] oldUserRecord = searchFile(USER_PROFILES, user.getName()).split(",");
        String newUserRecord;

        String[] levelRecord = searchFile(GAME_LEVELS, Integer.toString(levelNum)).split(",");
        LeaderBoard leaders;
        Level completedLevel;
        String newLevelRecord;
        if (oldUserRecord.length > 1 && levelRecord.length > 1) {
            leaders = new LeaderBoard(levelRecord[1]);
            completedLevel = new Level(levelRecord[2]);
            //update user originalLevel reached if applicable
            if (user.getHighestLevel() == levelNum - 1) {

                newUserRecord = oldUserRecord[0] + ","
                        + Integer.toString(user.getHighestLevel()) + ","
                        + oldUserRecord[2] + ","
                        + oldUserRecord[3];

                if (!editFile(USER_PROFILES, user.getName(), newUserRecord)) {
                    System.out.println("ERROR - update user higest level failure "
                            + "check file: " + USER_PROFILES.getPath() + " :"
                            + "for user: " + user.getName() + " :");
                    return false;
                }
            }

            //update originalLevel leaderboard if applicable
            if (leaders.addleader(user, time)) {

                newLevelRecord = Integer.toString(levelNum) + ",";
                newLevelRecord += leaders.toString() + ",";
                newLevelRecord += completedLevel.toString();

                if (!editFile(GAME_LEVELS, Integer.toString(levelNum), newLevelRecord)) {
                    System.out.println("ERROR - update leader failure "
                            + "check file: " + GAME_LEVELS.getPath() + " :");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * save the input level as the user's 'level save' in USER_PROFILES,
     * overwriting the previous save if there is one.
     *
     * @param level level state to be saved (may be changed through gameplay)
     * @param user UserProfile who is saving their progress.
     */
    public static boolean saveProgress(Level level, UserProfile user) {
        if (level.getBoard() != null) {
            return editFile(USER_PROFILES, user.getName(), user.getName() + ","
                    + user.getHighestLevel() + ","
                    + user.getTheme() + ","
                    + level.toString());
        } else {
            return editFile(USER_PROFILES, user.getName(), user.getName() + ","
                    + user.getHighestLevel() + ","
                    + user.getTheme() + ","
                    + "-");
        }
    }

    /**
     * load a user's saved level state from their user record in the 
     * USER_PROFILES file if there is one,
     * (note: each player can have only one save state).
     *
     * @param user the UserProfile to load the save state of.
     * @return the Level if found, null if not.
     */
    public static Level loadProgress(UserProfile user) {
        String userRecord = searchFile(USER_PROFILES, user.getName());
        if (userRecord != null) {
            String[] userData = userRecord.split(",");
            return new Level(userData[3]);
        } else {
            return null;
        }
    }

    /**
     * loads the leaderboard for a level from the GAME_LEVELS file.
     *
     * @param levelNum which level's leaderboard is needed
     * @return the leaderboard if found, null if not.
     */
    public static LeaderBoard loadLeaders(int levelNum) {
        String[] levelRecord = searchFile(GAME_LEVELS, Integer.toString(levelNum)).split(",");
        return new LeaderBoard(levelRecord[1]);
    }

    /**
     * searches a file for the first record matching the search condition, that
     * being that the first part (assuming CSV format) of matchData matches the
     * first part of the record.
     *
     * @param file the file being searched
     * @param matchData primary key of the requested record.
     * @return the whole record if found, null if not.
     */
    private static String searchFile(File file, String matchData) {
        String peekRecord = null;
        if (file.exists()) {
            try {
                reader = new FileReader(file);
                buffRead = new BufferedReader(reader);

                peekRecord = buffRead.readLine();
                while (peekRecord != null && !peekRecord.split(",")[0].equals(matchData)) {
                    peekRecord = buffRead.readLine();
                }

                buffRead.close();
                reader.close();
            } catch (Exception e) {
                System.out.println("ERROR - search failure, "
                        + "check file: " + file.getPath()
                        + " :for: " + matchData + " :");
            }
            return peekRecord;
        } else {
            return null;
        }
    }

    /**
     * appends a record to a file.
     *
     * @param file the file being added to.
     * @param record the record being added.
     * @return success/failure.
     */
    private static boolean appendRecord(File file, String record) {
        try {
            writer = new FileWriter(file, true);
            buffWrite = new BufferedWriter(writer);

            buffWrite.append(record);
            buffWrite.newLine();

            buffWrite.close();
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR - append record failure, "
                    + "check file: " + file.getPath() + " :");
        }
        return false;
    }

    /**
     * rewrites a file with any records matching starting with matchData being 
     * overwritten with newRecord (or deleted if newRecord = null) if any error 
     * occurs, the changes are undone
     *(note: edits/deletes all matching records).
     * @param file file being edited.
     * @param matchData primary key of the record being edited/deleted.
     * @param newRecord new record to replace matching records with/null to delete.
     * @return success/failure.
     */
    private static boolean editFile(File file, String matchData, String newRecord) {
        File tempCopy = fileTempCopy(file);
        boolean success;
        try {
            file.renameTo(tempCopy);

            reader = new FileReader(tempCopy);
            buffRead = new BufferedReader(reader);

            writer = new FileWriter(file, true);
            buffWrite = new BufferedWriter(writer);

            if (editRecord(matchData.split(",")[0], newRecord)) {
                success = true;
            } else {
                success = false;
            }

            buffRead.close();
            reader.close();

            buffWrite.close();
            writer.close();

            if (success) {
                tempCopy.delete();
            } else {
                file.delete();
                tempCopy.renameTo(file);
            }

        } catch (Exception e) {
            System.out.println("ERROR - file edit failure,"
                    + "check files: " + file.getPath()
                    + " :and: " + tempCopy.getName() + " :");
            success = false;
        }

        return success;
    }

    /**
     * copies records from one file to another, editing/deleting them if they
     * match conditions.
     *
     * @param matchData primary key/record starting with the primary key.
     * @param newRecord whole record to replace matching records with (null if deleting).
     * @return success/failure.
     */
    private static boolean editRecord(String matchData, String newRecord) {
        try {
            String peekRecord = buffRead.readLine();
            while (peekRecord != null) {

                if (peekRecord.split(",")[0].equals(matchData)) {
                    if (newRecord != null) {
                        buffWrite.append(newRecord);
                        buffWrite.newLine();
                    }
                } else {
                    buffWrite.append(peekRecord);
                    buffWrite.newLine();
                }
                peekRecord = buffRead.readLine();
            }
        } catch (Exception e) {
            System.out.println("ERROR - edit record failure");
            return false;
        }
        return true;
    }

    /**
     * generates a File reference identical to the original but with "TEMP"
     * before the file type.
     *
     * @param original the file to generate a temporary file ref from.
     * @return the changed reference if successful, null if not.
     */
    private static File fileTempCopy(File original) {
        String[] oldName = original.getName().split("\\.");
        String newName = oldName[0] + "TEMP." + oldName[1];

        try {
            return new File(original.getParent() + File.separator + newName);
        } catch (Exception e) {
            System.out.println("ERROR - file rename failure, "
                    + "check file: " + original.getPath() + " :");
            return null;
        }
    }
}
