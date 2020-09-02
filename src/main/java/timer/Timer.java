package timer;

public class Timer implements Runnable {

    private static final int SECOND_IN_MILLIS = 1000;
    private int timePassed = 0;

    public int getTimePassed() {
        return timePassed;
    }

    private synchronized void incrementTimePassed() {
        timePassed++;
        this.notifyAll();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(SECOND_IN_MILLIS);
            } catch (InterruptedException e) {
                System.out.println(e.getLocalizedMessage());
            }
            incrementTimePassed();
        }
    }
}
