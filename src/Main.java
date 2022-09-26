import java.util.concurrent.*;

public class Main {

    private static final int MESSAGESCOUNT = 60;
    private static final BlockingQueue<String> MESSAGES = new ArrayBlockingQueue<>(MESSAGESCOUNT);
    private static final ExecutorService EXECUTORSERVICE = Executors.newFixedThreadPool(4);
    private static final int SLEEPTIME = 1000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

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

        Future<?> future = EXECUTORSERVICE.submit(new CallCenterWorker(MESSAGES, MESSAGESCOUNT));
        System.out.println(future.get());
        EXECUTORSERVICE.shutdown();
    }
}
