import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    final int dimension;
    private int[] board; // stores queens' positions - index is row, value is column
    int diagonalCount; // number of diagonals in a single upward or downward direction

    // index represents diagonal, value represents the number of queens on that diagonal
    int[] upwardDiagonals; // index 0 is upper left diagonal, index n - 1 is lower right diagonal
    int[] downwardDiagonals; // index 0 is upper right diagonal, index n - 1 is lower left diagonal

    public Chessboard(int dimension) {

        this.dimension = dimension;
        buildChessboard();
        buildDiagonalArrays();
    }

    /*
    Creates a board with randomly placed queens. Array representation of the board ensures there will never be any
    horizontal or vertical attacks.

    Input: none
    Output: none
     */
    private void buildChessboard() {

        board = new int[dimension];
        diagonalCount = 2 * dimension - 1;

        List<Integer> queenLocations = new ArrayList<>(); // index is queen's row, value is queen's column
        for (int i = 0; i < dimension; i++) {
            queenLocations.add(i);
        }

        Collections.shuffle(queenLocations); // ensures queens are randomly placed

        // loads queens' positions into board
        for (int i = 0; i < dimension; i++) {
            board[i] = queenLocations.get(i);
        }
    }

    /*
    Fills diagonal arrays with appropriate values corresponding to the board.

    Input: none
    Output: none
     */
    private void buildDiagonalArrays() {

        int c;
        upwardDiagonals = new int[diagonalCount];
        downwardDiagonals = new int[diagonalCount];

        // iterates through queen positions and properly marks down their diagonals
        for (int r = 0; r < dimension; r++) {

            c = board[r];

            upwardDiagonals[r + c]++;
            downwardDiagonals[r - c + (dimension - 1)]++;
        }
    }

    /*
    Swaps the columns of two specified queens on the board.

    Input: the corresponding rows of the queens to be swapped
    Output: none
     */
    public void swapQueenColumns(int queen1Row, int queen2Row) {

        int queen1Column = board[queen1Row];
        int queen2Column = board[queen2Row];
        int tempColumn = queen1Column;

        // removes queens from diagonal arrays
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

        // adds queens back to the diagonal arrays in their new positions
        upwardDiagonals[queen1Row + queen1Column]++;
        downwardDiagonals[queen1Row - queen1Column + (dimension - 1)]++;

        upwardDiagonals[queen2Row + queen2Column]++;
        downwardDiagonals[queen2Row - queen2Column + (dimension - 1)]++;
    }

    /*
    Checks if a queen is attacked and returns the corresponding boolean.

    Input: the row of the queen to be checked
    Output: true if the queen is attacked, false if the queen is not attacked
     */
    public boolean queenIsAttacked(int queenRow) {
        int queenColumn = board[queenRow];

        // if more than 1 queen is on the specified queen's diagonal, it must be getting attacked
        return upwardDiagonals[queenRow + queenColumn] > 1 || downwardDiagonals[queenRow - queenColumn + (dimension - 1)] > 1;
    }

    /*
    Prints a 2D representation of the chessboard. Empty spaces are 0s and queens are 1s.

    Input: none
    Output: none - prints to console
     */
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

    /*
    Checks to see if swapping two queens will reduce the number of attacks. Only performs the simulated swap in the
    diagonal arrays, which is undone.

    Input: the rows of the queens to be checked
    Output: true if the swapping of queens reduces the number of attacks, false if it remains the same or increases
     */
    public boolean swapReducesAttacks(int queen1Row, int queen2Row) {

        int totalAttacksBefore = 0;
        int totalAttacksAfter = 0;

        int queen1Column = board[queen1Row];
        int queen2Column = board[queen2Row];

        // if the number of queens on a diagonal exceeds 1, the number of attacks is added to the pre-swap attack count
        totalAttacksBefore += (upwardDiagonals[queen1Row + queen1Column] > 1)
                ? upwardDiagonals[queen1Row + queen1Column] - 1 : 0;
        totalAttacksBefore += (downwardDiagonals[queen1Row - queen1Column + (dimension - 1)] > 1)
                ? downwardDiagonals[queen1Row - queen1Column + (dimension - 1)] - 1 : 0;

        totalAttacksBefore += (upwardDiagonals[queen2Row + queen2Column] > 1)
                ? upwardDiagonals[queen2Row + queen2Column] - 1 : 0;
        totalAttacksBefore += (downwardDiagonals[queen2Row - queen2Column + (dimension - 1)] > 1)
                ? downwardDiagonals[queen2Row - queen2Column + (dimension - 1)] - 1 : 0;

        // removes queens from their current diagonal
        upwardDiagonals[queen1Row + queen1Column]--;
        downwardDiagonals[queen1Row - queen1Column + (dimension - 1)]--;

        upwardDiagonals[queen2Row + queen2Column]--;
        downwardDiagonals[queen2Row - queen2Column + (dimension - 1)]--;

        // adds queens to their new diagonal after the simulated swap
        upwardDiagonals[queen1Row + queen2Column]++;
        downwardDiagonals[queen1Row - queen2Column + (dimension - 1)]++;

        upwardDiagonals[queen2Row + queen1Column]++;
        downwardDiagonals[queen2Row - queen1Column + (dimension - 1)]++;

        // if the number of queens on a diagonal exceeds 1, the number of attacks is added to the post-swap attack count
        totalAttacksAfter += (upwardDiagonals[queen1Row + queen2Column] > 1)
                ? upwardDiagonals[queen1Row + queen2Column] - 1 : 0;
        totalAttacksAfter += (downwardDiagonals[queen1Row - queen2Column + (dimension - 1)] > 1)
                ? downwardDiagonals[queen1Row - queen2Column + (dimension - 1)] - 1 : 0;

        totalAttacksAfter += (upwardDiagonals[queen2Row + queen1Column] > 1)
                ? upwardDiagonals[queen2Row + queen1Column] - 1 : 0;
        totalAttacksAfter += (downwardDiagonals[queen2Row - queen1Column + (dimension - 1)] > 1)
                ? downwardDiagonals[queen2Row - queen1Column + (dimension - 1)] - 1 : 0;

        // removes queens from their new diagonal
        upwardDiagonals[queen1Row + queen2Column]--;
        downwardDiagonals[queen1Row - queen2Column + (dimension - 1)]--;

        upwardDiagonals[queen2Row + queen1Column]--;
        downwardDiagonals[queen2Row - queen1Column + (dimension - 1)]--;

        // adds queens back to their original diagonal, effectively undoing the simulated swap
        upwardDiagonals[queen1Row + queen1Column]++;
        downwardDiagonals[queen1Row - queen1Column + (dimension - 1)]++;

        upwardDiagonals[queen2Row + queen2Column]++;
        downwardDiagonals[queen2Row - queen2Column + (dimension - 1)]++;

        return totalAttacksAfter < totalAttacksBefore;
    }

    /*
    Returns the current number of queen attacks on the board.

    Input: none
    Output: int representing the number of queen attacks
     */
    public int getTotalAttacks() {

        int totalAttacks = 0;

        // iterates through diagonals and adds attacks to the count if there is more than 1 queen on a given diagonal
        for (int i = 0; i < diagonalCount; i++) {

            if (upwardDiagonals[i] > 1) {
                totalAttacks += upwardDiagonals[i] - 1;
            }

            if (downwardDiagonals[i] > 1) {
                totalAttacks += downwardDiagonals[i] - 1;
            }
        }

        return totalAttacks;
    }
}
