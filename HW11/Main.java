import game.*;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        Scanner conditions = new Scanner(System.in);
        final Game game = new Game(false, new HumanPlayer(), new RandomPlayer());
        int result;
        do {
            System.out.print("Enter m, n, k: ");
            int m = conditions.nextInt();
            int n = conditions.nextInt();
            int k = conditions.nextInt();
            result = game.play(new TicTacToeBoard(m, n, k));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
