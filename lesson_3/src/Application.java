import executors.CustomerThreadPool;

import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {

        var threadPool = new CustomerThreadPool(4);

        threadPool.execute(() -> {
            System.out.println("First task started - " + LocalDateTime.now());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("First task finished - " + LocalDateTime.now());
        });

        threadPool.execute(() -> {
            System.out.println("Second task started - " + LocalDateTime.now());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Second task finished - " + LocalDateTime.now());
        });

        threadPool.execute(() -> {
            System.out.println("Third task started - " + LocalDateTime.now());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Third task finished - " + LocalDateTime.now());
        });

        threadPool.execute(() -> {
            System.out.println("Fourth task started - " + LocalDateTime.now());
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fourth task finished - " + LocalDateTime.now());
        });

        threadPool.execute(() -> {
            System.out.println("Fifth task started - " + LocalDateTime.now());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fifth task finished - " + LocalDateTime.now());
        });

        threadPool.execute(() -> {
            System.out.println("Sixth task started - " + LocalDateTime.now());
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Sixth task finished - " + LocalDateTime.now());
        });

        threadPool.shutdown();

        threadPool.execute(() -> {
            System.out.println("Seven task started - " + LocalDateTime.now());
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Seven task finished - " + LocalDateTime.now());
        });
    }
}
