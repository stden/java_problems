package tf;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//@ThreadSafe
public class AsyncLogger implements Logger {
    // Array - массив (ограничена)
    // Linked - в списке (не ограничена) - <---
    //    OutOfMemory -
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public AsyncLogger() {
        Thread printThread = new Thread(() -> {
            try {
                while (true) {
                    System.out.println(queue.take());
                }
            } catch (InterruptedException ex) {
                return;
            }
        });
        printThread.setDaemon(true);
        printThread.start();
    }

    public static void main(String[] args) {
        Logger logger = new AsyncLogger();
        for (int i = 1; i <= 9; i++) {
            logger.log("Str" + i);
        }
    }

    @Override
    public void log(String str) {
        queue.add(str);
    }
}