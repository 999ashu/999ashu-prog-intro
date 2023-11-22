import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int start = -710 * 25000;
        while (n > 0) {
            start += 710;
            System.out.println(start);
            n--;
        }
    }
}