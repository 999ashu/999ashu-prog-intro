import java.util.Arrays;
import java.util.Scanner;

public class ReverseMinR {
    public static void main(String[] args) {
        String[] vertical = getVerticalInput();
        int[][] matrix = new int[vertical.length][];
        for (int i = 0; i < vertical.length; i++) {
            matrix[i] = getHorizontalInput(vertical[i]);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) {
                    System.out.print(matrix[i][j]);
                    System.out.print(" ");
                    continue;
                }
                matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1]);
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static String[] getVerticalInput() {
        Scanner scVertical = new Scanner(System.in);

        int arraySize = 5;
        String[] vertical = new String[arraySize];

        int i = 0;
        while (scVertical.hasNextLine()) {
            if (i == arraySize) {
                arraySize *= 2;
                vertical = Arrays.copyOf(vertical, arraySize);
            }
            vertical[i] = scVertical.nextLine();
            i++;
        }

        scVertical.close();

        return Arrays.copyOf(vertical, i);
    }

    public static int[] getHorizontalInput(String input) {
        StringBuilder horizontalInput = new StringBuilder();

        int arraySize = 5;
        int[] numbers = new int[arraySize];

        int i = 0;
        int amount = 0;
        while (i < input.length()) {
            horizontalInput.setLength(0);
            while ((i < input.length()) && !Character.isWhitespace(input.charAt(i))) {
                horizontalInput.append(input.charAt(i));
                i++;
            }
            while ((i < input.length()) && Character.isWhitespace(input.charAt(i))) {
                i++;
            }
            if (!horizontalInput.isEmpty() && (amount == arraySize)) {
                arraySize *= 2;
                numbers = Arrays.copyOf(numbers, arraySize);
            }
            numbers[amount] = Integer.parseInt(horizontalInput.toString());
            amount++;
        }

        return Arrays.copyOf(numbers, amount);
    }
}