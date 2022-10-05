import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final int MESSAGESCOUNT = 5;
    private static final int POOLSIZE = 3;
    private static final BlockingQueue<String> MESSAGES = new ArrayBlockingQueue<>(MESSAGESCOUNT);
    private static final ExecutorService EXECUTORSERVICE = Executors.newFixedThreadPool(POOLSIZE);
    private static final int SLEEPTIME = 1000;
    private final static List<Future<?>> futureList = new ArrayList<>();

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < MESSAGESCOUNT; i++) {
                MESSAGES.add("Сообщение " + i);
                System.out.println("Поступило сообщение " + i);
                try {
                    Thread.sleep(SLEEPTIME);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();

        for (int i = 0; i < POOLSIZE; i++) {
            EXECUTORSERVICE.execute(new CallCenterWorker(MESSAGES, MESSAGESCOUNT));
        }
        EXECUTORSERVICE.shutdown();
    }
}
