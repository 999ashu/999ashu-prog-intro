package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Tournament {
    int m, n, k;

    public Tournament() {
        while (true) {
            try {
                Scanner sc1 = new Scanner(System.in);
                System.out.print("Enter m, n, k for your tournament: ");
                this.m = sc1.nextInt();
                this.n = sc1.nextInt();
                this.k = sc1.nextInt();
                if (m < 1 || n < 1 || k < 1) {
                    System.out.println("Incorrect m, n, k. Try again.");
                } else {
                    playTournament();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect m, n, k. Try again.");
            }
        }
    }

    private void playTournament() {
        while (true) {
            try {
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Enter a number of players: ");
                int number = sc2.nextInt();
                System.out.print("Enter a number of bots: ");
                int bots = sc2.nextInt();
                if (number + bots < 2) {
                    System.out.println("You entered an incorrect number.");
                } else {
                    List<Integer> players = new ArrayList<>();
                    for (int i = 0; i < number + bots; i++) {
                        players.add(i + 1);
                    }
                    int rounds = 0;
                    int power = 1;
                    while (power < number + bots) {
                        rounds++;
                        power *= 2;
                    }
                    for (int i = rounds; i > 0; i--) {
                        Round round = new Round(players, number, m, n, k);
                        if (power == 2) {
                            System.out.println();
                            System.out.println("===================");
                            System.out.println("    Final match!   ");
                            System.out.println("===================");
                        } else {
                            System.out.println();
                            System.out.println("-------------------");
                            System.out.println("    1/" + (power / 2) + " finals!");
                            System.out.println("-------------------");
                        }
                        if (players.size() % 2 == 0) {
                            players = round.playRound(0);
                        } else {
                            players = round.playRound(1);
                        }
                        power /= 2;
                    }
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please, enter a natural number.");
            }
        }
    }
}
