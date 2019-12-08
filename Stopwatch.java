/**
 * Class that is used to count time taken for given level
 *
 * @author Sean Beck
 * @version 1.0
 */
public class Stopwatch {

    private long start;
    private long stop;
    //Constant for converting nanoseconds to seconds
    private static final int CONVERT_NANOSECONDS_CONSTANT = 1000000000;

    /**
     * Starts the stopwatch.
     */
    public void startStopwatch() {
        this.start = System.nanoTime();
    }

    /**
     * Stops the stopwatch.
     */
    public void stopStopwatch() {
        this.stop = System.nanoTime();
    }

    /**
     * Calculates the actual time taken of level.
     *
     * @return time The time taken to complete level in seconds.
     */
    public long calculateTime() {
        long time;
        time = ((stop - start) / CONVERT_NANOSECONDS_CONSTANT);
        return time;
    }
}