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

        // prints board
//        int[][] board = chessboard.getBoard();
//        for (int i = 0; i < dimension; i++) {
//            for (int j = 0; j < dimension; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        List<Queen> queens = chessboard.getQueens();
//        for (Queen queen : queens) {
//            System.out.println(queen.getIncomingDiagonalAttacks(board));
//        }

        Queen mostAttackedQueen;
        int mostAttacksReceived;

        while (chessboard.hasQueensUnderAttack()) {

            mostAttackedQueen = chessboard.getMostAttackedQueen();
            mostAttacksReceived = mostAttackedQueen.getIncomingDiagonalAttacks();

        }
    }






}
