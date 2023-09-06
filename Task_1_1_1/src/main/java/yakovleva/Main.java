package yakovleva;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс запуска.
 *
 * @author Яковлева Валерия
 * @version 1.0
 */
public class Main {

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
        } else {
            System.out.println("Invalid in format.");
        }

        scanner.close();
    }
}