/*
== Project 1 ==

Author: Demetri Karras
Class: CS203
Due: May 11th, 2025
 */

import java.util.Random;
import java.util.Scanner;

public class SolverNQueens {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the dimension of the chess board (n > 3): ");
        int dimension = scan.nextInt();

        int[][] board = buildInitialChessBoard(dimension);
    }

    private static int[][] buildInitialChessBoard(int dimension) {

        Random rand = new Random();

        int[][] board = new int[dimension][dimension];

        for (int j = 0; j < dimension; j++) {

            int queenIndex = rand.nextInt(dimension);
            board[queenIndex][j] = 1;
        }

        return board;
    }
}
