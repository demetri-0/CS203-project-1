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

        List<Chessboard.Queen> queens = chessboard.getQueens();

        int totalAttacks;
        int totalAttacksAfterSwap;

        boolean swapPerformed;
        int swapCount = 0;
        int boardShuffleCount = 0;

//        chessboard.printBoard();
//        for (Chessboard.Queen queen : queens) {
//            System.out.println("R: " + queen.getRowPosition() + " <> C: " + queen.getColumnPosition());
//        }
//        System.out.println(chessboard.getTotalAttacks());
//        chessboard.swapQueenColumns(queens.get(0), queens.get(1));
//        chessboard.printBoard();
//        for (Chessboard.Queen queen : queens) {
//            System.out.println("R: " + queen.getRowPosition() + " <> C: " + queen.getColumnPosition());
//        }
//        System.out.println(chessboard.getTotalAttacks());


        while (chessboard.hasQueensUnderAttack()) {

            totalAttacks = chessboard.getTotalAttacks();
            swapPerformed = false;

            for (Chessboard.Queen queen : queens) {
                if (queen.isAttacked()) {

                    for (Chessboard.Queen comparisonQueen : queens) {

                        if (!comparisonQueen.equals(queen)) {

                            chessboard.swapQueenColumns(queen, comparisonQueen);
                            totalAttacksAfterSwap = chessboard.getTotalAttacks();

                            //System.out.println("Before swap: " +  totalAttacks + "\nAfter swap: " +  totalAttacksAfterSwap);
                            if (totalAttacksAfterSwap < totalAttacks) {
                                swapPerformed = true;
                                //System.out.println("swap");
                                swapCount++;
                            }
                            else {
                                chessboard.swapQueenColumns(queen, comparisonQueen);
                            }
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
