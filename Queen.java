public class Queen {
    private int incomingAttacks = 0;
    private int[][] board;
    private int rowPosition;
    private int columnPosition;

    public Queen(int[][] board, int rowPosition, int columnPosition) {
        this.board = board;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    int getIncomingDiagonalAttacks() {

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

    int getRowPosition() {
        return rowPosition;
    }

    int getColumnPosition() {
        return columnPosition;
    }
}