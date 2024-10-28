package ChessPieces;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.Test;


import app.ChessBoard;
import app.ChessPieces.*;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;

import java.util.Optional;

@RunWith(DataProviderRunner.class)
public class PawnTest extends BaseChessPiecesTest {

    @Test
    @UseDataProvider("loadDataForIsChessPieceOnMove")
    public void isChessPieceOnMoveTest(
            String color,
            int currentLine,
            int currentColumn,
            int toLine,
            int toColumn,
            ChessBoard board,
            boolean expectedIsPieceOnMove
    ) {
        Pawn pawn = new Pawn(color, currentLine, currentColumn);
        boolean chessPieceOnMove = pawn.isChessPieceOnMove(board, toLine, toColumn);
        Assert.assertEquals(expectedIsPieceOnMove, chessPieceOnMove);
        board.printBoard();
    }

    @DataProvider
    public static Object[][] loadDataForIsChessPieceOnMove() {
        return new Object[][]{
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        5, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        true // pieceOnMove
                },
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        4, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        true // pieceOnMove
                },
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        3, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false // pieceOnMove
                },
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        3, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false // pieceOnMove
                },
                {
                        ChessPiece.COLOR_BLACK,
                        4, // currentLine
                        0, // currentColumn
                        3, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false // pieceOnMove
                },
                {
                        ChessPiece.COLOR_BLACK,
                        4, // currentLine
                        0, // currentColumn
                        2, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        true // pieceOnMove
                },
                {
                        ChessPiece.COLOR_BLACK,
                        4, // currentLine
                        0, // currentColumn
                        1, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        true // pieceOnMove
                },
                {
                        ChessPiece.COLOR_BLACK,
                        4, // currentLine
                        0, // currentColumn
                        5, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false // pieceOnMove
                }
        };
    }

    protected static ChessBoard createBoard() {

        ChessBoard board = createBaseBoard(Optional.empty());

        board.board[1][0] = null;
        board.board[2][0] = new Pawn(ChessPiece.COLOR_WHITE, 2, 0);

        board.board[6][0] = null;
        board.board[4][0] = new Pawn(ChessPiece.COLOR_BLACK, 4, 0);

        return board;
    }
}
