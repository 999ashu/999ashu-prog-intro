import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        int[] res = new int[tests];
        for (int t = 0; t < tests; t++) {
            int counter = 0;
            int n = sc.nextInt();
            int[] costs = new int[n];
            for (int i = 0; i < n; i++) {
                costs[i] = sc.nextInt();
            }
            Map<Integer, Integer> C = new HashMap<>();
            for (int j = n - 2; j > 0; j--) {
                C.put(costs[j + 1], C.getOrDefault(costs[j + 1], 0) + 1);
                for (int i = 0; i < j; i++) {
                    int k = 2 * costs[j] - costs[i];
                    if (C.containsKey(k)) {
                        counter += C.get(k);
                    }
                }
            }
            res[t] = counter;
        }
        for (int i = 0; i < tests; i++) {
            System.out.println(res[i]);
        }
    }
}