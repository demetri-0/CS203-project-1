import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    final int dimension;
    private int[] board;
    int diagonalCount;
    int[] upwardDiagonals;
    int[] downwardDiagonals;

    public Chessboard(int dimension) {

        this.dimension = dimension;
        buildChessboard();
        buildDiagonalArrays();
    }

    private void buildChessboard() {

        board = new int[dimension];
        diagonalCount = 2 * dimension - 1;

        List<Integer> queenLocations = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            queenLocations.add(i);
        }

        Collections.shuffle(queenLocations);

        for (int i = 0; i < dimension; i++) {
            board[i] = queenLocations.get(i);
        }
    }

    private void buildDiagonalArrays() {

        int c;

        upwardDiagonals = new int[diagonalCount];
        for (int r = 0; r < dimension; r++) {
            c = board[r];

            upwardDiagonals[r + c]++;
        }

        downwardDiagonals = new int[diagonalCount];
        for (int r = 0; r < dimension; r++) {
            c = board[r];

            downwardDiagonals[r - c + (dimension - 1)]++;
        }
    }

    public void swapQueenColumns(int queen1Row, int queen2Row) {

        int queen1Column = board[queen1Row];
        int queen2Column = board[queen2Row];
        int tempColumn = queen1Column;

        // reflects swap in diagonal arrays pre-swap (queens "removed" from arrays)
        upwardDiagonals[queen1Row + queen1Column]--;
        downwardDiagonals[queen1Row - queen1Column + (dimension - 1)]--;

        upwardDiagonals[queen2Row + queen2Column]--;
        downwardDiagonals[queen2Row - queen2Column + (dimension - 1)]--;

        // swaps columns
        queen1Column = queen2Column;
        queen2Column = tempColumn;

        // reflects swap in board
        board[queen1Row] = queen1Column;
        board[queen2Row] = queen2Column;

        // reflects swap in diagonal arrays post-swap (queens "added" to arrays)
        upwardDiagonals[queen1Row + queen1Column]++;
        downwardDiagonals[queen1Row - queen1Column + (dimension - 1)]++;

        upwardDiagonals[queen2Row + queen2Column]++;
        downwardDiagonals[queen2Row - queen2Column + (dimension - 1)]++;
    }

    public boolean queenIsAttacked(int queenRow) {
        int queenColumn = board[queenRow];
        return upwardDiagonals[queenRow + queenColumn] > 1 || downwardDiagonals[queenRow - queenColumn + (dimension - 1)] > 1;
    }

    public void printBoard() {

        int[][] displayBoard = new int[dimension][dimension];

        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                displayBoard[r][c] = 0;
            }
        }

        for (int r = 0; r < dimension; r++) {
            displayBoard[r][board[r]]++;
        }

        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                System.out.print(displayBoard[r][c] + " ");
            }
            System.out.println();
        }
    }

    public int getTotalAttacks() {

        int totalAttacks = 0;

        for (int i = 0; i < diagonalCount; i++) {
            if (upwardDiagonals[i] > 1) {
                totalAttacks += upwardDiagonals[i] * (upwardDiagonals[i] - 1);
            }
            if (downwardDiagonals[i] > 1) {
                totalAttacks += downwardDiagonals[i] * (downwardDiagonals[i] - 1);
            }
        }

        return totalAttacks;
    }

    public int[] getBoard() {
        return board;
    }
}
