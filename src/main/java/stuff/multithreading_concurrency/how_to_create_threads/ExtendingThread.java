package stuff.multithreading_concurrency.how_to_create_threads;

public class ExtendingThread extends Thread{

    @Override
    public void run() {
        System.out.println("Running ExtendingThread");
    }
}
