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
        while (dimension <= 3) {
            System.out.print("Dimension too small. Try again (n > 3): ");
            dimension = scan.nextInt();
        }

        long startTime = System.nanoTime();
        Chessboard chessboard = new Chessboard(dimension);

        int totalSwapCount = 0; // number of swaps performed leading to a solved board
        int currentSwapCount = 0; // number of swaps performed on current iteration
        int restartCount = 0; // number of times the algorithm runs on a "new" board
        int boardShuffleCount = 0; // number of times a new board needed to be randomly generated

        /*
        The outermost loop runs while there are still colliding queens on the chessboard. All possible queen swaps are
        iterated through, checking if they reduce the number of attacks on the board. If attacks are reduced, the swap
        is performed. If after the first round of swaps the board is not solved, the entire process repeats until
        the board is solved. If no swaps were performed on the outermost iteration, the board is randomly reshuffled.
         */
        while (chessboard.getTotalAttacks() > 0) {

            currentSwapCount = 0;

            for (int queen1Row = 0; queen1Row < dimension; queen1Row++) {

                for (int queen2Row = queen1Row + 1; queen2Row < dimension; queen2Row++) {

                    if (chessboard.queenIsAttacked(queen1Row) || chessboard.queenIsAttacked(queen2Row)) {

                        if (chessboard.swapReducesAttacks(queen1Row, queen2Row)) {

                            chessboard.swapQueenColumns(queen1Row, queen2Row);
                            currentSwapCount++;
                            totalSwapCount++;
                        }
                    }
                }
            }

            // if the board is unsolved, it may be reshuffled
            if (chessboard.getTotalAttacks() > 0) {

                if (currentSwapCount == 0) { // algorithm is "stuck", new board is randomly generated
                    chessboard = new Chessboard(dimension);
                    boardShuffleCount++;
                }

                restartCount++;
            }
        }

        long endTime = System.nanoTime();

        System.out.println(totalSwapCount + " total swaps were performed.");
        System.out.println("The algorithm ran " + restartCount + " additional time(s).");
        System.out.println("The board was reshuffled " + boardShuffleCount + " time(s).");
        System.out.println("Execution Time: " + (endTime - startTime) / 1000000 + " ms");
    }
}
