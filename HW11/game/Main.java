package game;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner gameMode = new Scanner(System.in);
        System.out.println("Do you want to start tournament? (y/n)");
        String answer = gameMode.nextLine();
        /*if (answer.equalsIgnoreCase("n")) {
            
        }*/
    }

    private static void tournamentGame() {

    }

    private static void baseGame() {
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result;
        do {
            try {
                Scanner conditions = new Scanner(System.in);
                System.out.print("Enter m, n, k: ");
                int m = conditions.nextInt();
                int n = conditions.nextInt();
                int k = conditions.nextInt();
                if (m < 2 || n < 2 || k < 2) {
                    System.out.println("Incorrect m, n, k. Try again.");
                    result = -1;
                    continue;
                }
                result = game.play(new MNKBoard(m, n, k));
                System.out.println("Game result: " + result);
            } catch (InputMismatchException e) {
                System.out.println("Incorrect m, n, k. Try again.");
                result = -1;
            }
        } while (result != 0);
    }
}
