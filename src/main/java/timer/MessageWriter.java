package timer;

public class MessageWriter implements Runnable {

    private final Timer timer;
    private final String message;
    private final int period;

    public MessageWriter(Timer timer, int period, String message) {
        this.timer = timer;
        this.message = message;
        this.period = period;
    }

    public MessageWriter(Timer timer) {
        this(timer, 1, null);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (timer) {
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getLocalizedMessage());
                }
                int time = timer.getTimePassed();
                if (time % period == 0) {
                    System.out.println(message != null ? message : time + " sec");
                }
            }
        }
    }
}
