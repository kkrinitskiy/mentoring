package stuff.multithreading_concurrency.how_to_create_threads;

public class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Running RunnableTask");
    }
}
