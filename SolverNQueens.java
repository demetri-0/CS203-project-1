/*
== Project 1 ==

Author: Demetri Karras
Class: CS203
Due: May 11th, 2025
 */

import java.util.*;

public class SolverNQueens {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the dimension of the chess board (n > 3): ");
        int dimension = scan.nextInt();

        Chessboard chessboard = new Chessboard(dimension);

        int swapCount = 0; // number of swaps performed
        int boardShuffleCount = 0; // number of boards that have been created (not including the first)

        /*
        The outermost loop runs while there are still colliding queens on the chessboard. All possible queen swaps are
        iterated through, checking if they reduce the number of attacks on the board. If attacks are reduced, the swap
        is performed. If the board is not solved after checking and performing swaps, it is randomly reshuffled, and the
        process repeats.
         */
        while (chessboard.getTotalAttacks() > 0) {

            for (int queen1Row = 0; queen1Row < dimension; queen1Row++) {

                for (int queen2Row = queen1Row + 1; queen2Row < dimension; queen2Row++) {

                    if (chessboard.queenIsAttacked(queen1Row) || chessboard.queenIsAttacked(queen2Row)) {

                        if (chessboard.swapReducesAttacks(queen1Row, queen2Row)) {

                            chessboard.swapQueenColumns(queen1Row, queen2Row);
                            swapCount++;
                        }
                    }
                }
            }

            // if all collisions have not been eliminated after all performed swaps, the board is reshuffled
            if (chessboard.getTotalAttacks() > 0) {
                chessboard = new Chessboard(dimension);
                boardShuffleCount++;
            }
        }

        System.out.println(swapCount + " total swaps were performed. The board needed to be shuffled " + boardShuffleCount + " times.");
    }

}
