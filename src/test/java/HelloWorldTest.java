
import org.junit.Assert;
import org.junit.Test;


import app.ChessBoard;
import app.ChessPieces.*;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

@RunWith(DataProviderRunner.class)
public class HelloWorldTest {


    @Test
    @UseDataProvider("loadDataProvider")
    public void dataTest(String color, int currentLine, int currentColumn, int toLine, int toColumn, boolean expected) {
        ChessBoard chessBoard = buildBoard(color);

        Pawn pawn = new Pawn(color, currentLine, currentColumn);
    }

    @DataProvider
    public static Object[][] loadDataProvider() {
        return new Object[][]{
                {
                        ChessPiece.COLOR_WHITE,
                        1, // currentLine
                        0, // currentColumn
                        2, // toLine
                        0,  // toColumn
                        true // pieceOnMove
                },
                {
                        ChessPiece.COLOR_BLACK,
                        6, // currentLine
                        3, // currentColumn
                        4, // toLine
                        5,  // toColumn
                        false // pieceOnMove
                },
                {
                        ChessPiece.COLOR_WHITE,
                        1, // currentLine
                        0, // currentColumn
                        4, // toLine
                        0,  // toColumn
                        false // pieceOnMove
                }
        };
    }


    public static ChessBoard buildBoard(String color) {

        ChessBoard board = new ChessBoard(color);

        board.board[0][0] = new Rook(ChessPiece.COLOR_WHITE, 0, 0);
        board.board[0][1] = new Horse(ChessPiece.COLOR_WHITE, 0, 1);
        board.board[0][2] = new Bishop(ChessPiece.COLOR_WHITE, 0, 2);
        board.board[0][3] = new Queen(ChessPiece.COLOR_WHITE, 0, 3);
        board.board[0][4] = new King(ChessPiece.COLOR_WHITE, 0, 4);
        board.board[0][5] = new Bishop(ChessPiece.COLOR_WHITE, 0, 5);
        board.board[0][6] = new Horse(ChessPiece.COLOR_WHITE, 0, 6);
        board.board[0][7] = new Rook(ChessPiece.COLOR_WHITE, 0, 7);
        board.board[1][0] = new Pawn(ChessPiece.COLOR_WHITE, 1, 0);
        board.board[1][1] = new Pawn(ChessPiece.COLOR_WHITE, 1, 1);
        board.board[1][2] = new Pawn(ChessPiece.COLOR_WHITE, 1, 2);
        board.board[1][3] = new Pawn(ChessPiece.COLOR_WHITE, 1, 3);
        board.board[1][4] = new Pawn(ChessPiece.COLOR_WHITE, 1, 4);
        board.board[1][5] = new Pawn(ChessPiece.COLOR_WHITE, 1, 5);
        board.board[1][6] = new Pawn(ChessPiece.COLOR_WHITE, 1, 6);
        board.board[1][7] = new Pawn(ChessPiece.COLOR_WHITE, 1, 7);

        board.board[7][0] = new Rook(ChessPiece.COLOR_BLACK, 7, 0);
        board.board[7][1] = new Horse(ChessPiece.COLOR_BLACK, 7, 1);
        board.board[7][2] = new Bishop(ChessPiece.COLOR_BLACK, 7, 2);
        board.board[7][3] = new Queen(ChessPiece.COLOR_BLACK, 7, 3);
        board.board[7][4] = new King(ChessPiece.COLOR_BLACK, 7, 4);
        board.board[7][5] = new Bishop(ChessPiece.COLOR_BLACK, 7, 5);
        board.board[7][6] = new Horse(ChessPiece.COLOR_BLACK, 7, 6);
        board.board[7][7] = new Rook(ChessPiece.COLOR_BLACK, 7, 7);
        board.board[6][0] = new Pawn(ChessPiece.COLOR_BLACK, 6, 0);
        board.board[6][1] = new Pawn(ChessPiece.COLOR_BLACK, 6, 1);
        board.board[6][2] = new Pawn(ChessPiece.COLOR_BLACK, 6, 2);
        board.board[6][3] = new Pawn(ChessPiece.COLOR_BLACK, 6, 3);
        board.board[6][4] = new Pawn(ChessPiece.COLOR_BLACK, 6, 4);
        board.board[6][5] = new Pawn(ChessPiece.COLOR_BLACK, 6, 5);
        board.board[6][6] = new Pawn(ChessPiece.COLOR_BLACK, 6, 6);
        board.board[6][7] = new Pawn(ChessPiece.COLOR_BLACK, 6, 7);
        return board;
    }
}
