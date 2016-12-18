package interview;

public class FootTest implements Runnable {

    private final String name;
    private int count;

    private FootTest(String name) {
        this.name = name;
        count = 0;
    }

    public static void main(String[] args) {
        new Thread(new FootTest("left")).start();
        new Thread(new FootTest("right")).start();
    }

    @Override
    public void run() {
        // В оригинале был бесконечный цикл while(true),
        // но я решил что лучше чтобы цикл кончался когда-нибудь :)
        for (int i = 0; i < 100000; i++) {
            try {
                step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void step() throws InterruptedException {
        synchronized (FootTest.class) {
            System.out.println("Step " + name + " #" + (++count));
            // Пробуждаем остальные потоки
            FootTest.class.notify();
            // Сами становимся в очередь
            FootTest.class.wait();
        }
    }
}
