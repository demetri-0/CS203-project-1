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
            if (queen.getIncomingDiagonalAttacks() > 0) {
                return true;
            }
        }
        return false;
    }

    public void swapQueenColumns(Queen queen1, Queen queen2) {

        board[queen1.getRowPosition()][queen1.getColumnPosition()] = 0;
        board[queen1.getRowPosition()][queen2.getColumnPosition()] = 1;

        board[queen2.getRowPosition()][queen2.getColumnPosition()] = 0;
        board[queen2.getRowPosition()][queen1.getColumnPosition()] = 1;

        int tempColumn = queen1.getColumnPosition();
        queen1.setColumnPosition(queen2.getColumnPosition());
        queen2.setColumnPosition(tempColumn);
    }

    public void printBoard() {
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public int getTotalAttacks() {

        int totalAttacks = 0;

        for (Queen queen : getQueens()) {
            totalAttacks += queen.getIncomingDiagonalAttacks();
        }

        return totalAttacks;
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
                    Queen queen = new Queen(r, c);
                    queens.add(queen);
                }
            }
        }

        return queens;
    }

    public int[][] getBoard() {
        return board;
    }

    public class Queen {
        private int rowPosition;
        private int columnPosition;

        public Queen(int rowPosition, int columnPosition) {
            this.rowPosition = rowPosition;
            this.columnPosition = columnPosition;
        }

        boolean isAttacked() {
            return getIncomingDiagonalAttacks() > 0;
        }

        int getIncomingDiagonalAttacks() {

            getQueens(); // updates queens' current position
            int incomingAttacks = 0;
            int dimension = board.length;
            int r = rowPosition;
            int c = columnPosition;

            while (r > 0 && c > 0) {
                r--;
                c--;
                if (board[r][c] == 1) {
                    incomingAttacks++;
                }
            }
            r = rowPosition;
            c = columnPosition;

            while (r < dimension - 1 && c < dimension - 1) {
                r++;
                c++;
                if (board[r][c] == 1) {
                    incomingAttacks++;
                }
            }
            r = rowPosition;
            c = columnPosition;

            while (r > 0 && c < dimension - 1) {
                r--;
                c++;
                if (board[r][c] == 1) {
                    incomingAttacks++;
                }
            }
            r = rowPosition;
            c = columnPosition;

            while (r < dimension - 1 && c > 0) {
                r++;
                c--;
                if (board[r][c] == 1) {
                    incomingAttacks++;
                }
            }

            return incomingAttacks;
        }

        public boolean equals(Queen queen) {
            if (queen.getRowPosition() == rowPosition && queen.getColumnPosition() == columnPosition) {
                return true;
            }
            return false;
        }

        int getRowPosition() {
            return rowPosition;
        }

        void setRowPosition(int rowPosition) {
            this.rowPosition = rowPosition;
        }

        int getColumnPosition() {
            return columnPosition;
        }

        void setColumnPosition(int columnPosition) {
            this.columnPosition = columnPosition;
        }
    }
}
