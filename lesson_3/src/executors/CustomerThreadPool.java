package executors;

import java.util.ArrayList;
import java.util.LinkedList;

public class CustomerThreadPool {

    private final LinkedList<Runnable> execPool = new LinkedList<>();
    private final ArrayList<Worker> workerPool = new ArrayList<>();
    private volatile boolean isShutdown = false;

    public CustomerThreadPool(int countThreadWorkers) {
        if (countThreadWorkers < 0) {
            throw new IllegalArgumentException("Worker threads count can't be less 1");
        }

        for (int i = 0; i < countThreadWorkers; i++) {
            var worker = new Worker();
            worker.start();
            workerPool.add(worker);
        }
    }

    public void execute(Runnable r) {
        if (r == null) {
            throw new NullPointerException("Runnable cannot be null");
        }

        synchronized (execPool) {
            if (isShutdown) {
                throw new IllegalStateException("Pool is shutdown");
            }
            execPool.add(r);
            execPool.notify();
        }
    }

    public void shutdown() {
        synchronized (execPool) {
            isShutdown = true;
            execPool.notifyAll();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (Worker worker : workerPool) {
            worker.join();
        }
    }

    private class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                Runnable r;
                synchronized (execPool) {
                    while (execPool.isEmpty() && !isShutdown) {
                        try {
                            execPool.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (isShutdown && execPool.isEmpty()) {
                        break;
                    }
                    r = execPool.poll();
                }

                if (r != null) {
                    try {
                        r.run();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }
}
