package stuff.multithreading_concurrency.race_condition;

import java.util.concurrent.atomic.AtomicInteger;

/**
*   Способы предотвращения гонки потоков:
 * <p>Синхронизация: Использование ключевого слова synchronized или блоков синхронизации.
 * <p>Атомарные операции: Использование классов из пакета java.util.concurrent.atomic.
 * <p>Блокировки: Использование объектов Lock из пакета java.util.concurrent.locks.
 * <p>Потокобезопасные коллекции: Использование коллекций из пакета java.util.concurrent.
 * <p>Правильное проектирование: Минимизация общих изменяемых состояний между потоками.
*/
public class Counter {
    private AtomicInteger count = new AtomicInteger(0);
    public void increment() {
        count.incrementAndGet();
    }
    public int getCount() {
        return count.get();
    }
}
