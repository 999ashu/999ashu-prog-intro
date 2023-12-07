package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            while (true) {
                Scanner gameMode = new Scanner(System.in);
                System.out.print("Do you want to start the tournament? (y/n): ");
                String answer = gameMode.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    new Tournament();
                    break;
                } else if (answer.equalsIgnoreCase("n")) {
                    baseGame();
                    break;
                } else {
                    System.out.println("Please, answer in format (y/n)");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Game stopped by user.");
        }
    }

    private static void baseGame() {
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result = -1;
        while (result != 0) {
            try {
                Scanner conditions = new Scanner(System.in);
                System.out.print("Enter m, n, k for your game: ");
                int m = conditions.nextInt();
                int n = conditions.nextInt();
                int k = conditions.nextInt();
                if (m < 1 || n < 1 || k < 1) {
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
        }
    }
}
