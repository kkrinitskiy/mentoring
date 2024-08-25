package stuff.multithreading_concurrency.how_to_create_threads;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "CallableTask result";
    }
}
