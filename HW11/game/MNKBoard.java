package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int m, n, k;

    public MNKBoard(int m, int n, int k) {
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.m = m;
        this.n = n;
        this.k = k;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        int empty = 0;
        for (int u = 0; u < m; u++) {
            for (int v = 0; v < n; v++) {
                int inRow = 0;
                int inColumn = 0;
                int inDiag1 = 0;
                int inDiag2 = 0;
                for (int w = 0; w < k; w++) {
                    if (v + w < n && cells[u][v + w] == turn) {
                        inRow++;
                    }
                    if (u + w < m && cells[u + w][v] == turn) {
                        inColumn++;
                    }
                    if (u + w < m && v + w < n && cells[u + w][v + w] == turn) {
                        inDiag1++;
                    }
                    if (u + w < m && v - w > 0 && cells[u + w][v - w] == turn) {
                        inDiag2++;
                    }
                }
                if (inRow == k || inColumn == k) {
                    return Result.WIN;
                }
                if (inDiag1 == k || inDiag2 == k) {
                    return Result.WIN;
                }
                if (cells[u][v] == Cell.E) {
                    empty++;
                }
            }
        }
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int c = 0; c < n; c++) {
            sb.append(c);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
