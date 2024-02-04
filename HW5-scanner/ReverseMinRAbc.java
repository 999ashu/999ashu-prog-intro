import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReverseMinRAbc {
    static final HashMap<Character, Integer> charMap = new HashMap<>();
    static final String goodChars = "abcdefghij";

    public static void main(String[] args) {
        for (char ch = 'a'; ch <= 'j'; ch++) {
            charMap.put(ch, ch - 'a');
        }
        try {
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            FScanner sc = new FScanner(System.in);
            while (sc.ready()) {
                ArrayList<Integer> line = new ArrayList<>();
                while (sc.hasNext("abc")) {
                    line.add(toDigit(sc.next("abc")));
                }
                matrix.add(line);
            }
            for (ArrayList<Integer> line : matrix) {
                for (int i = 0; i < line.size(); i++) {
                    if (i == 0) {
                        System.out.print(toAbc(line.get(i)) + " ");
                        continue;
                    }
                    if (line.get(i) > line.get(i - 1)) {
                        line.set(i, line.get(i - 1));
                    }
                    System.out.print(toAbc(line.get(i)) + " ");
                }
                newLine(line, matrix);
            }
        } catch (IOException e) {
            System.out.println("Input problem: " + e);
        }
    }

    public static void newLine(List<Integer> line, ArrayList<ArrayList<Integer>> matrix) {
        if (System.lineSeparator().length() == 2) {
            if (line != matrix.get(matrix.size() - 1)) {
                System.out.println();
            }
        } else {
            System.out.println();
        }
    }

    private static int toDigit(String num) {
        StringBuilder dec = new StringBuilder();
        int i = 0;
        if (num.charAt(0) == '-') {
            dec.append('-');
            i++;
        }
        while (i < num.length()) {
            dec.append(charMap.get(num.charAt(i)));
            i++;
        }
        return Integer.parseInt(dec.toString());
    }

    private static String toAbc(Integer num) {
        StringBuilder dec = new StringBuilder();
        String abc = num.toString();
        int i = 0;
        if (num < 0) {
            dec.append('-');
            i++;
        }
        while (i < abc.length()) {
            dec.append(goodChars.charAt(abc.charAt(i) - '0'));
            i++;
        }
        return dec.toString();
    }
}
