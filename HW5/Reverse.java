import java.io.IOException;
import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) {
        try {
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            FScanner sc = new FScanner(System.in);
            while (sc.ready()) {
                ArrayList<Integer> line = new ArrayList<>();
                while (sc.hasNext("digit")) {
                    line.add(sc.nextInt());
                }
                matrix.add(line);
            }
            for (int i = matrix.size() - 1; i >= 0; i--) {
                for (int j = matrix.get(i).size() - 1; j >= 0; j--) {
                    System.out.print(matrix.get(i).get(j) + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Input problem: " + e);
        }
    }
}