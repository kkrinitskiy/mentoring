package multithreading.task_08;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private final static int RACE_DISTANCE = 2000;
    private static Random r = new Random();
    public static Timer timer = new Timer();
    private static int number = 0;
    private static String model = "model_%d";
    private static String mark = "mark_%d";
    private static List<RacingCar> cars = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        // Создаем автомобили
        for (int i = 0; i < 5; i++) {
            number++;
            cars.add(new RacingCar(
                    String.valueOf(number),
                    model.formatted(number),
                    mark.formatted(number),
                    RACE_DISTANCE));
        }


        // Задание для таймера - увеличиваем скорость каждую секунду
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (RacingCar car : cars) {
                    car.increaseSpeed();
                }
            }
        }, 0, 1000);

        // Задание для таймера - уменьшаем скорость у рандомной машины 4 раза в секунду и выполняем движение всех автомобилей
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cars.forEach(RacingCar::moveCar);
                int randomCar = r.nextInt(0, cars.size());
                RacingCar car = cars.get(randomCar);
                car.decreaseSpeed(r.nextInt(0, car.getSpeedUp()));
                for (RacingCar racingCar : cars) {
                    System.out.println("Car #" + racingCar.getNumber()
                            + ", скорость: " + racingCar.getCurrentSpeed()
                            + ", осталось до финиша: "
                            + (RACE_DISTANCE - racingCar.getCurrentDistance()));
                }
            }
        }, 0, 250);

        // Старт!
        for (RacingCar car : cars) {
            car.start();
        }

        for (RacingCar car : cars) {
            try{
                car.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Победитель: " + RacingCar.winner);

    }
}
