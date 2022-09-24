
import java.util.concurrent.BlockingQueue;

public class CallCenterWorker implements Runnable {
    private final int capacity;
    private final BlockingQueue<String> messages;
    private static int SLEEPTIME = 3000;

    public CallCenterWorker(BlockingQueue<String> messages, int capacity) {
        this.messages = messages;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        for (int i = 0; i < capacity; i++) {
            try {
                Thread.sleep(SLEEPTIME);
                System.out.println("Специалист работает над вашим вопросм.");
                System.out.println("Обработали звонок " + messages.take());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
