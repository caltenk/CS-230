public class Stopwatch {

    private long start;
    private long stop;
    private static final int CONVERT_NANOSECONDS_CONSTANT = 1000000000;

    public void startStopwatch() {
        this.start = System.nanoTime();
    }

    public void stopStopwatch() {
        this.stop = System.nanoTime();
    }

    public long calculateTime() {
        long time;
        time = ((stop - start) / CONVERT_NANOSECONDS_CONSTANT);
        return time;
    }
}