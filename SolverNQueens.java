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

        int totalAttacks;
        int totalAttacksAfterSwap;

        boolean swapPerformed;
        int swapCount = 0;
        int boardShuffleCount = 0;

//        int[] board = chessboard.getBoard();
//        chessboard.printBoard();
//        for (int r = 0; r < dimension; r++) {
//            System.out.println("R: " + r + " <> C: " + board[r]);
//        }
//        System.out.println(Arrays.toString(chessboard.upwardDiagonals));
//        System.out.println(Arrays.toString(chessboard.downwardDiagonals));
//        System.out.println(chessboard.getTotalAttacks());
//
//        chessboard.swapQueenColumns(0, 1);
//        chessboard.printBoard();
//        for (int r = 0; r < dimension; r++) {
//            System.out.println("R: " + r + " <> C: " + board[r]);
//        }
//        System.out.println(Arrays.toString(chessboard.upwardDiagonals));
//        System.out.println(Arrays.toString(chessboard.downwardDiagonals));
//        System.out.println(chessboard.getTotalAttacks());

        while (chessboard.getTotalAttacks() > 0) {

            totalAttacks = chessboard.getTotalAttacks();
            swapPerformed = false;

            for (int queen1Row = 0; queen1Row < dimension; queen1Row++) {

                    for (int queen2Row = queen1Row + 1; queen2Row < dimension; queen2Row++) {

                        if (chessboard.queenIsAttacked(queen1Row) || chessboard.queenIsAttacked(queen2Row)) {

                            chessboard.swapQueenColumns(queen1Row, queen2Row);
                            totalAttacksAfterSwap = chessboard.getTotalAttacks();

                            //System.out.println("Before swap: " +  totalAttacks + "\nAfter swap: " +  totalAttacksAfterSwap);
                            if (totalAttacksAfterSwap < totalAttacks) {
                                swapPerformed = true;
                                //System.out.println("swap");
                                swapCount++;
                            }
                            else {
                                chessboard.swapQueenColumns(queen1Row, queen2Row);
                            }
                        }
                    }
            }

            if (!swapPerformed) {
                chessboard = new Chessboard(dimension);
                boardShuffleCount++;
            }
        }

        System.out.println(swapCount + " total swaps were performed. The board needed to be shuffled " + boardShuffleCount + " times.");
    }

}
