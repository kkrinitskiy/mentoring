package lesson_02;

import java.util.Arrays;

public class Algorithms {
    public static void main(String[] args) {
        int[] ints = {64, 42, 73, 41, 31, 53, 16, 24, 57, 42, 74, 55, 36, 66};


        System.out.println("before sort: " + Arrays.toString(ints));
        long start = System.nanoTime();


//        findMin(ints);
//        binarySearch(16, ints, 0,ints.length);
//        bubbleSort(ints);
//        selectionSort(ints);
//        quickSort(ints, 0, ints.length - 1);
        sorted(ints);

        long end =  System.nanoTime();
        System.out.println("after sort: " + Arrays.toString( sorted(ints)));
        System.out.println("execution time: " + (end - start));
    }

    public static int findMin(int[] ints){
        int index = 0;
        int min = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if(ints[i] < min){
                min = ints[i];
                index = i;
            }
        }
        return index;
    }

    public static int binarySearch(int find, int[] ints, int start, int end){

        int index = -1;
        int middle = (end - start)/2;

        if(find > ints[middle]){
            index = binarySearch(find, ints, middle, end);
        }
        if(find < ints[middle]){
            index = binarySearch(find, ints, start, middle);
        }
        if(find == ints[middle]){
            index = middle;
        }

        return index;
    }

    private static void bubbleSort(int[] ints){
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 1; i < ints.length; i++) {
                if (ints[i] < ints[i - 1]) {

                    int storage = ints[i];
                    ints[i] = ints[i - 1];
                    ints[i - 1] = storage;
                    sorted = false;
                }
            }
        }
    }

    private static void selectionSort(int[] ints){

        for (int i = 0; i < ints.length; i++) {
            int min = ints[i];
            for (int j = i; j < ints.length; j++) {
                if(ints[j] < min){
                    min = ints[j];
                    ints[j] = ints[i];
                    ints[i] = min;
                }
            }
        }
    }

//    private static void quickSort(int[] ints, int from, int to){
//        if((to - from) < 1){
//            return;
//        }
//
//        int m = partition(ints, from, to);
//        quickSort(ints, from, m);
//        quickSort(ints, m, to);
//
//    }
//
//    private static int partition(int[] ints, int from, int to) {
//        if((to - from) < 1){
//            return from;
//        }
//
//        int i = from;
//        int j = to - 1;
//        int m = median(ints, from, to);
//
//        while (i < j){
//            while (ints[i] < m){
//                i++;
//            }
//            while (ints[j] > m){
//                j--;
//            }
//            if(i <= j){
//                swap(ints, i, j);
//                i++;
//                j--;
//            } else {
//                break;
//            }
//        }
//
//        return i;
//    }

    private static void swap(int[] ints, int leftIndex, int rightIndex){
        int storage = ints[leftIndex];
        ints[leftIndex] = ints[rightIndex];
        ints[rightIndex] = storage;
    }

    private static int[] sorted(int[] a){
        if(a.length < 2){
            return a;
        }

        int m = a.length / 2;

        return merge(
                sorted(Arrays.copyOfRange(a, 0, m)),
                sorted(Arrays.copyOfRange(a, m, a.length))
        );
    }

    private static int[] merge(int[] a, int[] b){
        int[] result = new int[a.length + b.length];

        int p1 = 0, p2 = 0;
        int rp = 0;

        while ((p1 < a.length) || (p2 < b.length)){

            if(p1 == a.length){
                result[rp++] = b[p2++];
                continue;
            }
            if(p2 == b.length){
                result[rp++] = a[p1++];
                continue;
            }
            if(a[p1] < b[p2]){
                result[rp++] = a[p1++];
            }else {
                result[rp++] = b[p2++];
            }

        }

        return result;
    }




























}
