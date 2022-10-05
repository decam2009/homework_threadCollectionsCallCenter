
import java.util.concurrent.BlockingQueue;

public class CallCenterWorker implements Runnable {
    private final int CAPACITY;
    private final BlockingQueue<String> MESSAGES;
    private static final int SLEEPTIME = 2000;

    public CallCenterWorker(BlockingQueue<String> messages, int capacity) {
        this.MESSAGES = messages;
        this.CAPACITY = capacity;
    }

    @Override
    public void run() {
        for (int i = 0; i < CAPACITY; i++) {
            try {
                Thread.sleep(SLEEPTIME);
                System.out.println("Обработали звонок " + MESSAGES.take());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
