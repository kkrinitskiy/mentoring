package stuff.multithreading_concurrency.excercises._1_create_threads;

public class RunnableImpl implements Runnable {

    private final String message;

    public RunnableImpl(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            System.out.println(message);
            Thread.yield();
        }
    }
}
