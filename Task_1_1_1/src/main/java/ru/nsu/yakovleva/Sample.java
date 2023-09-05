package ru.nsu.yakovleva;
import java.util.Scanner;


public class Sample {
    /**
     *
     * @param arr
     * @return
     */
    public static int[] heapsort(int[] arr) {

        int n = arr.length;

        for (int i=n/2-1; i>=0; i--){
            sift(arr,n,i);
        }

        for (int i = n-1; i>0; i--){
            int tmp = arr[0];
            arr[0]=arr[i];
            arr[i]=tmp;

            sift(arr,i,0);
        }

        return arr;

    }

    public static void sift(int[] arr, int n, int i){

        int max = i;
        int left = 2*i+1;
        int right = 2*i+2;


        if (right < n && arr[right] > arr[max]) {
            max = right;
        }

        if (left<n && arr[left] > arr[max]){
            max = left;
        }

        if (i != max) {
            int tmp = arr[i];
            arr[i] = arr[max];
            arr[max] = tmp;

            sift(arr, n, max);
        }

    }



}