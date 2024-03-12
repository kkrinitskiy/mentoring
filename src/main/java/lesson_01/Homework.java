package lesson_01;

public class Homework {
    public static void main(String[] args) {
        int[] ints_01 = {100, 90, 80, 70, 60};                      // 5
        int[] ints_02 = {100, 90, 80, 88, -1, -2, -3, -4, -5};      // 6
        int[] ints_03 = {100, 90, 80, 70, 80, -1, -2};              // 4
        int[] ints_04 = {100, 90, 80, 70, 80, -2, -1};              // 4
        int[] ints_05 = {-1, -2, 15, 14, 100, 90, 80, 70, 60};      // 5

        System.out.println(
                getLengthOfNumbersInDecreasingSequence(ints_05)
        );

    }

    public static int getLengthOfNumbersInDecreasingSequence(int[] ints){

        int result = 0;

        for (int i = 0; i < ints.length - 1; i++) {
            int length = 1;
            for (int j = i; j < ints.length - 1; j++) {
                if(ints[j] > ints[j+1]){
                    length += 1;
                    if (length > result) {
                        result = length;
                        }
                } else {
                    if (length > result) {
                        result = length;
                    }
                    i = j;
                    break;
                }
            }
        }
        return result;
    }
}
