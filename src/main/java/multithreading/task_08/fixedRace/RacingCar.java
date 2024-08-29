package multithreading.task_08.fixedRace;


public class RacingCar {
    private String mark;
    private String model;
    private int number;
    private int maxSpeed = 83;
    private int speedUp = 20;
    private int currentSpeed = 0;
    private int currentDistance = 0;


    public RacingCar(String mark, String model, int number) {
        this.mark = mark;
        this.model = model;
        this.number = number;
    }

    public void moveCar() { currentDistance += currentSpeed / 4;}

    public void increaseSpeed() {
        if((currentSpeed + speedUp) <= maxSpeed) {
            currentSpeed += speedUp;
        } else {
            currentSpeed = maxSpeed;
        }
    }

    public void decreaseSpeed(int n) {
        if((currentSpeed - n) >= 0){
            currentSpeed -= n;
        }else {
            currentSpeed = 0;
        }
    }

    @Override
    public String toString() {
        return "RacingCar{" +
                "mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", number=" + number +
                ", currentSpeed=" + currentSpeed +
                ", currentDistance=" + currentDistance +
                '}';
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getSpeedUp() {
        return speedUp;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }
}
