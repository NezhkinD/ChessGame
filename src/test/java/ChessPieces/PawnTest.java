package ChessPieces;

import app.Entity.CoordinatesEntity;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotMoveException;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.Test;


import app.ChessBoard;
import app.ChessPieces.*;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;

import java.util.Optional;

import static app.Exception.CannotMoveException.*;

/**
 * @deprecated
 */
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
            boolean expectedIsPieceOnMove,
            String exceptionMessage
    ) {

        try {
            Pawn pawn = new Pawn(color, currentLine, currentColumn);
            CoordinatesEntity coordinatesEntity = new CoordinatesEntity(currentLine, currentColumn, toLine, toColumn);
            MoveEntity moveEntity = new MoveEntity(coordinatesEntity);
            boolean canMoveToPosition = pawn.canMoveToPosition(board, moveEntity);
            Assert.assertEquals(expectedIsPieceOnMove, canMoveToPosition);

            // todo debug
            board.printBoard();

        } catch (CannotMoveException e) {
            Assert.assertTrue(e.getMessage().contains(exceptionMessage));
        }
    }

    @DataProvider
    public static Object[][] loadDataForIsChessPieceOnMove() {
        return new Object[][]{
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        2, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false, // pieceOnMove
                        MESSAGE_CURRENT_LOCATION_EQ_TO_LOCATION // exceptionMessage
                },
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        3, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        true, // pieceOnMove
                        "NULL", // exceptionMessage
                },
                {
                        ChessPiece.COLOR_WHITE,
                        1, // currentLine
                        0, // currentColumn
                        2, // toLine
                        2,  // toColumn
                        createBoard(), //board
                        false, // pieceOnMove
                        MESSAGE_NOT_POSSIBLE_MOVE, // exceptionMessage
                },
                {
                        ChessPiece.COLOR_WHITE,
                        1, // currentLine
                        0, // currentColumn
                        4, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false, // pieceOnMove
                        MESSAGE_NOT_POSSIBLE_MOVE, // exceptionMessage
                },
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        4, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        false, // pieceOnMove
                        MESSAGE_NOT_POSSIBLE_MOVE_PAWN, // exceptionMessage
                },
                {
                        ChessPiece.COLOR_WHITE,
                        2, // currentLine
                        0, // currentColumn
                        3, // toLine
                        0,  // toColumn
                        createBoard(), //board
                        true, // pieceOnMove
                        "NULL", // exceptionMessage
                },
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
