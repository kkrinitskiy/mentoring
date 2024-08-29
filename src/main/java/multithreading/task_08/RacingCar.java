package multithreading.task_08;

import java.util.concurrent.atomic.AtomicBoolean;

public class RacingCar extends Thread{

    private  final int distance; // длина гоночного трека в метрах
    private int currentDistance = 0;
    private static volatile AtomicBoolean hasWinner = new AtomicBoolean(false);
    public static RacingCar winner;

    private final String mark;
    private final String model;
    private final String number;
    private final int maxSpeed = 83; // максимальная скорость в метрах в секунду - приблизительно 300 км/ч
    private int currentSpeed = 0; // скорость в метрах в секунду
    private final int speedUp = 20; // увеличение скорости за секунду


    public RacingCar(String number, String model, String mark, int distance) {
        this.number = number;
        this.model = model;
        this.mark = mark;
        this.distance = distance;
    }


    /**
     * Каждый автомобиль в цикле проверяет свое состояние в гонке
     * При достижении одной из машин финиша гонка завершается
     */
    @Override
    public void run() {
        while (!hasWinner.get()) {
           if (currentDistance >= distance && hasWinner.get()) {
               Main.timer.cancel();
               return;
           }
           if (currentDistance >= distance && !hasWinner.get()){
               hasWinner.compareAndSet(false, true);
               winner = this;
               Main.timer.cancel();
               return;
           }
        }
    }

    public void increaseSpeed(){
        if((currentSpeed + speedUp) <= maxSpeed) {
            currentSpeed += speedUp;
        } else {
            currentSpeed = maxSpeed;
        }
    }

    public void decreaseSpeed(int n){
        if((currentSpeed - n) >= 0){
            currentSpeed -= n;
        }else {
            currentSpeed = 0;
        }
    }

    public int getSpeedUp(){
        return speedUp;
    }

    public int getCurrentDistance(){
        return currentDistance;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "RacingCar{" +
                "currentDistance=" + currentDistance +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", currentSpeed=" + currentSpeed +
                ", speedUp=" + speedUp +
                '}';
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     *  Изменение пройденной дистанции на величиниу, которую проехал автомобиль за четверть секунды
     */
    public void moveCar(){
        currentDistance += currentSpeed / 4;
    }
}
