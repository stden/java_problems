package interview;

import java.util.Stack;

public class FootTest implements Runnable {

    private final String name;
    Object foots = new Object();

    public FootTest(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());

        new Thread(new FootTest("left")).start();
        new Thread(new FootTest("right")).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void step() throws InterruptedException {
        synchronized (foots) {
            System.out.println("Step " + name);
            foots.wait();
            foots.notify();
        }
    }
}
