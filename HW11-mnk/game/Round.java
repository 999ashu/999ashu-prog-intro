package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Round {
    int number, m, n, k;
    List<Integer> players, winners;

    public Round(List<Integer> players, int number, int m, int n, int k) {
        this.number = number;
        this.players = players;
        this.winners = new ArrayList<>();
        this.m = m;
        this.n = n;
        this.k = k;
    }

    public List<Integer> playRound(int extraPlayer) {
        Random random = new Random();
        int p1, p2;
        for (int j = 0; j < players.size() - 1 - extraPlayer; j += 2) {
            boolean flag = random.nextBoolean();
            if (flag) {
                p1 = j;
                p2 = j + 1;
            } else {
                p1 = j + 1;
                p2 = j;
            }
            System.out.println("Player " + players.get(p1) + " is playing for X | Player " + players.get(p2) + " is playing for O");
            Game game = new Game(false, getPlayerType(p1, number), getPlayerType(p2, number));
            int result = -1;
            if (flag) {
                while (result < 1) {
                    result = game.play(new MNKBoard(m, n, k));
                }
            } else {
                while (result < 1) {
                    result = 3 - game.play(new MNKBoard(m, n, k));
                }
            }
            System.out.println("Game result: " + players.get(j + result - 1));
            winners.add(players.get(j + result - 1));
        }
        if (extraPlayer == 1) {
            System.out.println("Auto-win: " + players.get(players.size() - 1));
            winners.add(players.get(players.size() - 1));
        }
        return winners;
    }

    private Player getPlayerType(int x, int number) {
        if (x + 1 > number) {
            return new RandomPlayer(m, n);
        } else {
            return new HumanPlayer();
        }
    }
}
