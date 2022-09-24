import java.util.concurrent.*;

public class Main {

    private static final int messagesCount = 60;
    private static final BlockingQueue<String> messages = new ArrayBlockingQueue<>(messagesCount);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static final int SLEEPTIME = 1000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Thread(() -> {
            for (int i = 0; i < messagesCount; i++) {
                messages.add("Сообщение " + i);
                System.out.println("Поступило сообщение " + i);
                try {
                    Thread.sleep(SLEEPTIME);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();

        Future<?> future = executorService.submit(new CallCenterWorker(messages, messagesCount));
        System.out.println(future.get());
        executorService.shutdown();
    }
}
