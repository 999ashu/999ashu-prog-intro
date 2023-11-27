package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner gameMode = new Scanner(System.in);
            System.out.print("Do you want to start the tournament? (y/n): ");
            String answer = gameMode.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                tournamentGame();
                break;
            } else if (answer.equalsIgnoreCase("n")) {
                baseGame();
                break;
            } else {
                System.out.println("Please, answer in format (y/n)");
            }
        }
    }

    private static void tournamentGame() {
        while (true) {
            try {
                Scanner sc1 = new Scanner(System.in);
                System.out.print("Enter m, n, k for your tournament: ");
                int m = sc1.nextInt();
                int n = sc1.nextInt();
                int k = sc1.nextInt();
                if (m < 1 || n < 1 || k < 1) {
                    System.out.println("Incorrect m, n, k. Try again.");
                } else {
                    while (true) {
                        try {
                            Scanner sc2 = new Scanner(System.in);
                            System.out.print("Enter a number of players: ");
                            int number = sc2.nextInt();
                            if (number < 2) {
                                System.out.println("You entered an incorrect number.");
                            } else {
                                List<Integer> players = new ArrayList<>();
                                List<Integer> winners = new ArrayList<>();
                                for (int i = 0; i < number; i++) {
                                    players.add(i + 1);
                                }
                                int rounds = 0;
                                int power = 1;
                                while (power < number) {
                                    rounds++;
                                    power *= 2;
                                }
                                for (int i = rounds; i > 0; i--) {
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
                                        playRound(0, players, winners, m, n, k);
                                    } else {
                                        playRound(1, players, winners, m, n, k);
                                        System.out.println("Auto-win: " + players.get(players.size() - 1));
                                        winners.add(players.get(players.size() - 1));
                                    }
                                    players = winners;
                                    winners = new ArrayList<>();
                                    power /= 2;
                                }
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please, enter a natural number.");
                        }
                    }
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect m, n, k. Try again.");
            }
        }
    }

    private static void playRound(int extraPlayer, List<Integer> players, List<Integer> winners, int m, int n, int k) {
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
            Game game = new Game(false, new RandomPlayer(), new RandomPlayer());
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
