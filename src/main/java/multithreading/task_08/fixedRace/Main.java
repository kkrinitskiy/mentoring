package multithreading.task_08.fixedRace;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    private static volatile int track = 200;
    private static final ScheduledExecutorService race = Executors.newScheduledThreadPool(2);
    private static Random r = new Random();
    private static volatile AtomicBoolean hasWinner = new AtomicBoolean(false);
    private static volatile AtomicReference<RacingCar> winner = new AtomicReference<>(null);


    public static void main(String[] args) {

        List<RacingCar> cars = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            cars.add(new RacingCar("mark_" + i, "model_" + i, i));
        }

        // увеличиваем скорость каждую секунду
        race.scheduleAtFixedRate(() -> {
            if (!hasWinner.get()) {
                for (RacingCar car : cars) {
                    car.increaseSpeed();
                }
            } else {
                race.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);

        // уменьшаем скорость у рандомной машины 4 раза в секунду и выполняем движение всех автомобилей
        race.scheduleAtFixedRate(() -> {
                if (!hasWinner.get()) {
                    cars.forEach( car -> {
                        if(car.getCurrentDistance() >= track){
                            winner.set(car);
                            hasWinner.set(true);
                            race.shutdown();
                        }
                    });
                    cars.forEach(RacingCar::moveCar);
                    int randomCar = r.nextInt(0, cars.size());
                    RacingCar car = cars.get(randomCar);
                    car.decreaseSpeed(r.nextInt(0, car.getSpeedUp()));
                    for (RacingCar racingCar : cars) {
                        System.out.println("Car #" + racingCar.getNumber()
                                + ", скорость: " + racingCar.getCurrentSpeed()
                                + ", осталось до финиша: "
                                + (track - racingCar.getCurrentDistance()));
                    }
                }else {
                    race.shutdown();
                }

        }, 0, 250, TimeUnit.MILLISECONDS);

        // Ожидаем завершения гонки
        while (true) {
            try {
                boolean b = race.awaitTermination(1, TimeUnit.SECONDS);// Устанавливаем разумный таймаут для ожидания
                if (b) {
                    System.out.println("Победитель - " + winner.get());
                    return;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
