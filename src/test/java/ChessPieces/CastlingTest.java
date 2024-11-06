package ChessPieces;

import ChessPieces.data.move.TestCase;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RunWith(DataProviderRunner.class)
public class CastlingTest extends BaseChessPiecesTest {
    @Test()
    @UseDataProvider("loadData")
    public void jsonTest(String testColor, List<ChessPiece> chessPieces, String type, boolean canCastleExpected, String errorMessageExpected) {
        ChessBoard board = switch (testColor){
            case ChessPiece.COLOR_BLACK -> createBaseBoardWhiteOnly(Optional.of(ChessPiece.COLOR_BLACK));
            default -> createBaseBoardBlackOnly(Optional.empty());
        };
        chessPieces.forEach(board::addChessPiece);

        boolean canCastle;
        try {
            canCastle = switch (type){
                case "castling0" -> board.castling0();
                case "castling7" -> board.castling7();
                default -> false;
            };
            Assert.assertEquals(canCastleExpected, canCastle);
        } catch (Throwable t) {
            Assert.assertEquals(errorMessageExpected, t.getMessage());
        }
    }

    @DataProvider
    public static Object[][] loadData() {
        return new Object[][]{
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 7, 7),
                                new Horse(ChessPiece.COLOR_BLACK, 7, 6)
                        ),
                        "castling0",
                        true,
                        "Рокировка невозможна: Для осуществления рокировки м/у королем и ладьей не должно быть фигур"
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 7, 0),
                                new Horse(ChessPiece.COLOR_BLACK, 7, 1)
                        ),
                        "castling7",
                        true,
                        "Рокировка невозможна: Для осуществления рокировки м/у королем и ладьей не должно быть фигур"
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 6, 7)
                        ),
                        "castling0",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 6, 0)
                        ),
                        "castling7",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 7, 7)
                        ),
                        "castling7",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 7, 0)
                        ),
                        "castling0",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 7, 7)
                        ),
                        "castling0",
                        true,
                        ""
                },
                {
                        ChessPiece.COLOR_BLACK,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_BLACK, 7, 4),
                                new Rook(ChessPiece.COLOR_BLACK, 7, 0)
                        ),
                        "castling7",
                        true,
                        ""
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 0, 7),
                                new Horse(ChessPiece.COLOR_WHITE, 0, 6)
                        ),
                        "castling0",
                        true,
                        "Рокировка невозможна: Для осуществления рокировки м/у королем и ладьей не должно быть фигур"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 0, 0),
                                new Horse(ChessPiece.COLOR_WHITE, 0, 1)
                        ),
                        "castling7",
                        true,
                        "Рокировка невозможна: Для осуществления рокировки м/у королем и ладьей не должно быть фигур"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 4, 7)
                        ),
                        "castling0",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 1, 0)
                        ),
                        "castling7",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 0, 7)
                        ),
                        "castling7",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 0, 0)
                        ),
                        "castling0",
                        true,
                        "Рокировка невозможна: Король и ладья должны стоять на своих начальных позициях"
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 0, 7)
                        ),
                        "castling0",
                        true,
                        ""
                },
                {
                        ChessPiece.COLOR_WHITE,
                        Arrays.asList(
                                new King(ChessPiece.COLOR_WHITE, 0, 4),
                                new Rook(ChessPiece.COLOR_WHITE, 0, 0)
                        ),
                        "castling7",
                        true,
                        ""
                }
        };
    }
}
