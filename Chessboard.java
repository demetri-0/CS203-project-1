import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    final int dimension;
    private int[][] board;
    private List<Queen> queens;

    public Chessboard(int dimension) {

        this.dimension = dimension;
        this.board = buildChessboard(dimension);
        this.queens = getQueens();
    }

    private int[][] buildChessboard(int dimension) {

        int[][] board = new int[dimension][dimension];
        List<Integer> queenRowLocations = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            queenRowLocations.add(i);
        }

        Collections.shuffle(queenRowLocations);

        for (int j = 0; j < dimension; j++) {

            int queenIndex = queenRowLocations.get(j);
            board[queenIndex][j] = 1;
        }

        return board;
    }

    public boolean hasQueensUnderAttack() {

        for (Queen queen : queens) {
            if (queen.getIncomingDiagonalAttacks() != 0) {
                return false;
            }
        }
        return true;
    }

    public Queen getMostAttackedQueen() {

        Queen mostAttackedQueen = null;
        int mostAttacksReceived = 0;

        for (Queen queen : queens) {
            if (queen.getIncomingDiagonalAttacks() > mostAttacksReceived) {
                mostAttackedQueen = queen;
                mostAttacksReceived = queen.getIncomingDiagonalAttacks();
            }
        }

        return mostAttackedQueen;
    }

    public List<Queen> getQueens() {

        List<Queen> queens = new ArrayList<>();

        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                if (board[r][c] == 1) {
                    Queen queen = new Queen(board, r, c);
                    queens.add(queen);
                }
            }
        }

        return queens;
    }

    public int[][] getBoard() {
        return board;
    }
}
