package app;

import app.ChessPieces.*;

public class ChessBoard {
    public static final int LINES = 8;
    public static final int COLUMNS = 8;
    public static final String[] columnsName = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessPiece[][] board = new ChessPiece[LINES][COLUMNS]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (!checkPosition(startLine) || !checkPosition(startColumn)){
            return false;
        }

        if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
            return false;
        }

        if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
            board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
            board[startLine][startColumn] = null; // set null to previous cell

            return true;
        }

        return false;
    }

    /**
     * Отобразить доску
     */
    public void printBoard() {
        System.out.println("Player 2(" + ChessPiece.COLOR_BLACK + ")");
        System.out.println();
        showColumnsName();

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    //System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                    System.out.print(board[i][j].getSymbol() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(" + ChessPiece.COLOR_WHITE + ")");
    }

    public boolean checkPosition(int pos) {
        return pos >= 0 && pos < LINES;
    }

    public boolean castling0() {
        return true;
    }

    public boolean castling7() {
        return true;
    }

    public void changePlayer(){
        this.nowPlayer = this.nowPlayerColor().equals(ChessPiece.COLOR_WHITE) ? ChessPiece.COLOR_BLACK : ChessPiece.COLOR_WHITE;
        System.out.print("Ход игрока " + nowPlayer + ":: ");
    }

    protected void showColumnsName() {
        System.out.println("\t" + String.join("\t", columnsName));
    }
}
