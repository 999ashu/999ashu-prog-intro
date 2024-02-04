import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);;
        double a = sc.nextInt();
        double b = sc.nextInt();
        double n = sc.nextInt();
        double division = Math.ceil((n - b) / (b - a));
        int formula = (int) (2 * (division) + 1);
        System.out.println(formula);
    }
}