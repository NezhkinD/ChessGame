package ChessPieces;

import ChessPieces.data.TestCase;
import app.ChessBoard;
import app.ChessPieces.ChessPiece;
import app.ChessPieces.Horse;
import app.ChessPieces.King;
import app.ChessPieces.Pawn;
import app.Entity.CoordinatesEntity;
import app.Entity.MoveEntity;
import app.Exception.CannotMoveException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;

/**
 * @deprecated
 */
@RunWith(DataProviderRunner.class)
public class KingTest extends BaseChessPiecesTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test()
    public void jsonTest() throws IOException, CannotMoveException {
        String userDirectoryPath = System.getProperty("user.dir");
        File file = new File(userDirectoryPath + "/src/test/java/ChessPieces/data/pawn.json");
        List<TestCase> pawnMoves = objectMapper.readValue(file, new TypeReference<>(){});
        ChessBoard board = createEmptyBoard(Optional.empty());


        for (TestCase moves : pawnMoves) {
            try {
                CoordinatesEntity xy = new CoordinatesEntity(moves.current, moves.to);
                MoveEntity moveEntity = new MoveEntity(xy);

                King king = new King(moves.color, xy.currentLine, xy.currentColumn);

                Assert.assertTrue(king.canMoveToPosition(board, moveEntity));
            } catch (Throwable t){
                Assert.assertFalse("ID:" + moves.id + " " + t.getMessage(), moves.canMove);
            }

        }

        System.out.println("DDDDDD");
    }

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
            CoordinatesEntity coordinatesEntity = new CoordinatesEntity(currentLine, currentColumn, toLine, toColumn);
            MoveEntity moveEntity = new MoveEntity(coordinatesEntity);
            //boolean chessPieceOnMove = pawn.canMoveToPosition(board, moveEntity);

            Optional<ChessPiece> chessPiece = board.getChessPiece(4, 4);
            if (chessPiece.isEmpty()){
                Assert.assertTrue(true);
            }

            board.printBoard();
//            Assert.assertEquals(expectedIsPieceOnMove, chessPieceOnMove);

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
