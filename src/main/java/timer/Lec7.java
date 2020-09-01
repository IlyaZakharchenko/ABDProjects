package timer;

public class Lec7 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        new Thread(timer).start();
        MessageWriter secWriter = new MessageWriter(timer);
        MessageWriter fiveSecWriter = new MessageWriter(timer, 5, "Message for 5 sec");
        MessageWriter sevenSecWriter = new MessageWriter(timer, 7, "Message for 7 sec");
        new Thread(secWriter).start();
        new Thread(fiveSecWriter).start();
        new Thread(sevenSecWriter).start();
    }
}
