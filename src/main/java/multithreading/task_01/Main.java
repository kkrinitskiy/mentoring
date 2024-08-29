package multithreading.task_01;

public class Main {
    public static void main(String[] args) {

        int[] arr = new int[]{0, 1, 2, 3, 4, 99, 17, 213, 546, -32};

        Thread findMin = new Thread(() -> {
            System.out.println(arr[minValue(arr)]);
        });
        Thread findMax = new Thread(() -> {
            System.out.println(arr[maxValue(arr)]);
        });

        findMin.start();
        findMax.start();


    }

    public static int minValue(int[] arr) {
        if(arr.length == 0){
            return -1;
        }
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[index]){
                index = i;
            }
        }

        return index;
    }

    public static int maxValue(int[] arr) {
        if(arr.length == 0){
            return -1;
        }
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[index]){
                index = i;
            }
        }

        return index;
    }
}
