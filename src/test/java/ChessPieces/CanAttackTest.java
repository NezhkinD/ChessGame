package ChessPieces;

import ChessPieces.data.attack.TestCase;
import app.ChessBoard;
import app.ChessPieces.*;
import app.Entity.CoordinatesEntity;
import app.Entity.MoveEntity;
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

@RunWith(DataProviderRunner.class)
public class CanAttackTest extends BaseChessPiecesTest {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonTestCasePath = "/src/test/java/ChessPieces/data/attack/";
    @Test()
    @UseDataProvider("loadData")
    public void jsonTest(ChessPiece chessPiece, String fileName) throws IOException {
        File file = new File(System.getProperty("user.dir") + jsonTestCasePath + fileName);
        List<TestCase> chessPieceAttacks = objectMapper.readValue(file, new TypeReference<>() {
        });
        ChessBoard board = createBaseBoardBlackOnly(Optional.empty());

        for (TestCase moves : chessPieceAttacks) {
            try {
                CoordinatesEntity xy = new CoordinatesEntity(moves.current, moves.to);
                MoveEntity moveEntity = new MoveEntity(xy);

                chessPiece.color = moves.color;
                chessPiece.currentLine = xy.currentLine;
                chessPiece.currentColumn = xy.currentColumn;

                Assert.assertTrue(chessPiece.canAttack(board, moveEntity));
            } catch (Throwable t) {
                Assert.assertFalse("ID:" + moves.id + " " + t.getMessage(), moves.canAttack);
            }
        }
    }

    @DataProvider
    public static Object[][] loadData() {
        return new Object[][]{
                {
                        new Pawn(ChessPiece.COLOR_WHITE, 0, 0),
                        "pawn.json"
                },
                {
                        new King(ChessPiece.COLOR_WHITE, 0, 0),
                        "king.json"
                },
                {
                        new Horse(ChessPiece.COLOR_WHITE, 0, 0),
                        "horse.json"
                },
                {
                        new Queen(ChessPiece.COLOR_WHITE, 0, 0),
                        "queen.json"
                },
                {
                        new Rook(ChessPiece.COLOR_WHITE, 0, 0),
                        "rook.json"
                },
                {
                        new Bishop(ChessPiece.COLOR_WHITE, 0, 0),
                        "bishop.json"
                },

        };
    }
}
