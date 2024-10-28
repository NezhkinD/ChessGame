package ChessPieces;

import app.ChessBoard;
import app.ChessPieces.*;
import app.Exception.CannotMoveException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.util.Optional;

import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;

@RunWith(DataProviderRunner.class)
public class HorseTest extends BaseChessPiecesTest {

    @Test()
    @UseDataProvider("loadDataForIsChessPieceOnMove")
    public void canMoveToPositionTest(
            String color,
            int currentLine,
            int currentColumn,
            int toLine,
            int toColumn,
            ChessBoard board,
            boolean expectedIsPieceOnMove,
            String exceptionMessage
    ) {
        try {
            Horse pawn = new Horse(color, currentLine, currentColumn);
            boolean chessPieceOnMove = pawn.canMoveToPosition(board, toLine, toColumn);
            board.printBoard();
            Assert.assertEquals(expectedIsPieceOnMove, chessPieceOnMove);

        } catch (CannotMoveException e){
            Assert.assertTrue(e.getMessage().contains(exceptionMessage));
        }
    }

    @DataProvider
    public static Object[][] loadDataForIsChessPieceOnMove() {
        return new Object[][]{
                {
                        ChessPiece.COLOR_WHITE,
                        0, // currentLine
                        6, // currentColumn
                        1, // toLine
                        4,  // toColumn
                        createBoard(), //board
                        false, // canMoveToPosition
                        MESSAGE_NOT_POSSIBLE_MOVE
                },
                {
                        ChessPiece.COLOR_WHITE,
                        0, // currentLine
                        6, // currentColumn
                        2, // toLine
                        5,  // toColumn
                        createBoard(), //board
                        true, // canMoveToPosition,
                        "null"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        0, // currentLine
                        6, // currentColumn
                        2, // toLine
                        7,  // toColumn
                        createBoard(), //board
                        true, // canMoveToPosition
                        "null"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        0, // currentLine
                        1, // currentColumn
                        2, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false, // canMoveToPosition
                        MESSAGE_NOT_POSSIBLE_MOVE
                },
//                {
//                        ChessPiece.COLOR_WHITE,
//                        0, // currentLine
//                        1, // currentColumn
//                        2, // toLine
//                        2,  // toColumn
//                        createBoard(), //board
//                        true, // canMoveToPosition
//                        ""
//                },
//                {
//                        ChessPiece.COLOR_WHITE,
//                        0, // currentLine
//                        1, // currentColumn
//                        1, // toLine
//                        3,  // toColumn
//                        createBoard(), //board
//                        false, // canMoveToPosition
//                        ""
//                }
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
