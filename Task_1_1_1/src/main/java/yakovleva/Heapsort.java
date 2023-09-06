package yakovleva;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Класс пирамидальной сортировки с функциями heapsort  и sift.
 *
 * @author Яковлева Валерия
 * @version 1.0
 */
public class Heapsort {
    /**
     * Функция для получения отсортированного по неубыванию массива.
     *
     * @param arr - входной массив
     * @return возвращает отсортированный массив
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

    /**
     * Функция для просеивания более тяжелых элементов в корни поддеревьев.
     *
     * @param arr - входной массив
     * @param n - размер кучи
     * @param i - наибольшая вершина
     */
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

    /**
     * Функция для запуска.
     *
     * @param args - стандартный входной параметр
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input: ");
        String in = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(in);

        if (matcher.find()) {
            String[] elements = matcher.group(1).split(",");
            int[] arr = new int[elements.length];

            for (int i = 0; i < elements.length; i++) {
                arr[i] = Integer.parseInt(elements[i].trim());
            }
            int[] result = Heapsort.heapsort(arr);

            System.out.println("Output: " + Arrays.toString(result));
        }
        else {
            System.out.println("Invalid in format.");
        }

        scanner.close();
    }

}