package ChessPieces;

import app.ChessPieces.ChessPiece;
import app.ChessPieces.Queen;
import app.Enum.VectorEnum;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @deprecated
 */
@RunWith(DataProviderRunner.class)
public class QueenTest {

    @Test
    @UseDataProvider("loadDataForPossibleMoves")
    public void possibleMovesTest(
            int currentLine,
            int currentColumn,
            int limit,
            int[][] expectedHorizontalLeft,
            int[][] expectedHorizontalRight,
            int[][] expectedVerticalUp,
            int[][] expectedVerticalDown,
            int[][] expectedDiagonalSW,
            int[][] expectedDiagonalNW,
            int[][] expectedDiagonalSE,
            int[][] expectedDiagonalNE
    ) {

        Queen queen = new Queen(ChessPiece.COLOR_WHITE, currentLine, currentColumn);
        int[][] possibleHorizontalLeftMoves = queen.getAllHorizontalLeftMoves(limit);
        int[][] possibleHorizontalRightMoves = queen.getAllHorizontalRightMoves(limit);
        int[][] possibleVerticalUpMoves = queen.getAllVerticalUpMoves(limit);
        int[][] possibleVerticalDownMoves = queen.getAllVerticalDownMoves(limit);
        int[][] possibleDiagonalSWMoves = queen.getAllDiagonalSWMoves(limit);
        int[][] possibleDiagonalNWMoves = queen.getAllDiagonalNWMoves(limit);
        int[][] possibleDiagonalSEMoves = queen.getAllDiagonalSEMoves(limit);
        int[][] possibleDiagonalNEMoves = queen.getAllDiagonalNEMoves(limit);

        Assert.assertArrayEquals(expectedHorizontalLeft, possibleHorizontalLeftMoves);
        Assert.assertArrayEquals(expectedHorizontalRight, possibleHorizontalRightMoves);
        Assert.assertArrayEquals(expectedVerticalUp, possibleVerticalUpMoves);
        Assert.assertArrayEquals(expectedVerticalDown, possibleVerticalDownMoves);
        Assert.assertArrayEquals(expectedDiagonalSW, possibleDiagonalSWMoves);
        Assert.assertArrayEquals(expectedDiagonalNW, possibleDiagonalNWMoves);
        Assert.assertArrayEquals(expectedDiagonalSE, possibleDiagonalSEMoves);
        Assert.assertArrayEquals(expectedDiagonalNE, possibleDiagonalNEMoves);
    }

    @DataProvider
    public static Object[][] loadDataForPossibleMoves() {
        return new Object[][]{
                {
                        0, //currentLine
                        7, //currentColumn
                        5, //limit
                        new int[][]{{0, 6}, {0, 5}, {0, 4}, {0, 3}, {0, 2}}, // LEFT
                        new int[][]{}, // RIGHT
                        new int[][]{{1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}}, // UP
                        new int[][]{}, // DOWN
                        new int[][]{}, // SW
                        new int[][]{{1, 6}, {2, 5}, {3, 4}, {4, 3}, {5, 2}}, // NW
                        new int[][]{}, // SE
                        new int[][]{}, // NE
                },
                {
                        0, //currentLine
                        7, //currentColumn
                        0, //limit
                        new int[][]{{0, 6}, {0, 5}, {0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}}, // LEFT
                        new int[][]{}, // RIGHT
                        new int[][]{{1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {7, 7}}, // UP
                        new int[][]{}, // DOWN
                        new int[][]{}, // SW
                        new int[][]{{1, 6}, {2, 5}, {3, 4}, {4, 3}, {5, 2}, {6, 1}, {7, 0}}, // NW
                        new int[][]{}, // SE
                        new int[][]{}, // NE
                },
                {
                        0, //currentLine
                        7, //currentColumn
                        -1, //limit
                        new int[][]{{0, 6}, {0, 5}, {0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}}, // LEFT
                        new int[][]{}, // RIGHT
                        new int[][]{{1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}, {7, 7}}, // UP
                        new int[][]{}, // DOWN
                        new int[][]{}, // SW
                        new int[][]{{1, 6}, {2, 5}, {3, 4}, {4, 3}, {5, 2}, {6, 1}, {7, 0}}, // NW
                        new int[][]{}, // SE
                        new int[][]{}, // NE
                },
                {
                        6, //currentLine
                        1, //currentColumn
                        4, //limit
                        new int[][]{{6, 0}}, // LEFT
                        new int[][]{{6, 2}, {6, 3}, {6, 4}, {6, 5}}, // RIGHT
                        new int[][]{{7, 1}}, // UP
                        new int[][]{{5, 1}, {4, 1}, {3, 1}, {2, 1}}, // DOWN
                        new int[][]{{5, 0}}, // SW
                        new int[][]{{7, 0}}, // NW
                        new int[][]{{5, 2}, {4, 3}, {3, 4}, {2, 5}}, // SE
                        new int[][]{{7, 2}}, // NE
                },
                {
                        6, //currentLine
                        1, //currentColumn
                        0, //limit
                        new int[][]{{6, 0}}, // LEFT
                        new int[][]{{6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7}}, // RIGHT
                        new int[][]{{7, 1}}, // UP
                        new int[][]{{5, 1}, {4, 1}, {3, 1}, {2, 1}, {1, 1}, {0, 1}}, // DOWN
                        new int[][]{{5, 0}}, // SW
                        new int[][]{{7, 0}}, // NW
                        new int[][]{{5, 2}, {4, 3}, {3, 4}, {2, 5}, {1, 6}, {0, 7}}, // SE
                        new int[][]{{7, 2}}, // NE
                },
                {
                        6, //currentLine
                        1, //currentColumn
                        -1, //limit
                        new int[][]{{6, 0}}, // LEFT
                        new int[][]{{6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7}}, // RIGHT
                        new int[][]{{7, 1}}, // UP
                        new int[][]{{5, 1}, {4, 1}, {3, 1}, {2, 1}, {1, 1}, {0, 1}}, // DOWN
                        new int[][]{{5, 0}}, // SW
                        new int[][]{{7, 0}}, // NW
                        new int[][]{{5, 2}, {4, 3}, {3, 4}, {2, 5}, {1, 6}, {0, 7}}, // SE
                        new int[][]{{7, 2}}, // NE
                },
                {
                        1, //currentLine
                        3, //currentColumn
                        2, //limit
                        new int[][]{{1, 2}, {1, 1}}, // LEFT
                        new int[][]{{1, 4}, {1, 5}}, // RIGHT
                        new int[][]{{2, 3}, {3, 3}}, // UP
                        new int[][]{{0, 3}}, // DOWN
                        new int[][]{{0, 2}}, // SW
                        new int[][]{{2, 2}, {3, 1}}, // NW
                        new int[][]{{0, 4}}, // SE
                        new int[][]{{2, 4}, {3, 5}}, // NE
                },
                {
                        1, //currentLine
                        3, //currentColumn
                        0, //limit
                        new int[][]{{1, 2}, {1, 1}, {1, 0}}, // LEFT
                        new int[][]{{1, 4}, {1, 5}, {1, 6}, {1, 7}}, // RIGHT
                        new int[][]{{2, 3}, {3, 3}, {4, 3}, {5, 3}, {6, 3}, {7, 3}}, // UP
                        new int[][]{{0, 3}}, // DOWN
                        new int[][]{{0, 2}}, // SW
                        new int[][]{{2, 2}, {3, 1}, {4, 0}}, // NW
                        new int[][]{{0, 4}}, // SE
                        new int[][]{{2, 4}, {3, 5}, {4, 6}, {5, 7}}, // NE
                },
                {
                        1, //currentLine
                        3, //currentColumn
                        -1, //limit
                        new int[][]{{1, 2}, {1, 1}, {1, 0}}, // LEFT
                        new int[][]{{1, 4}, {1, 5}, {1, 6}, {1, 7}}, // RIGHT
                        new int[][]{{2, 3}, {3, 3}, {4, 3}, {5, 3}, {6, 3}, {7, 3}}, // UP
                        new int[][]{{0, 3}}, // DOWN
                        new int[][]{{0, 2}}, // SW
                        new int[][]{{2, 2}, {3, 1}, {4, 0}}, // NW
                        new int[][]{{0, 4}}, // SE
                        new int[][]{{2, 4}, {3, 5}, {4, 6}, {5, 7}}, // NE
                },
                {
                        1, //currentLine
                        3, //currentColumn
                        3, //limit
                        new int[][]{{1, 2}, {1, 1}, {1, 0}}, // LEFT
                        new int[][]{{1, 4}, {1, 5}, {1, 6}}, // RIGHT
                        new int[][]{{2, 3}, {3, 3}, {4, 3}}, // UP
                        new int[][]{{0, 3}}, // DOWN
                        new int[][]{{0, 2}}, // SW
                        new int[][]{{2, 2}, {3, 1}, {4, 0}}, // NW
                        new int[][]{{0, 4}}, // SE
                        new int[][]{{2, 4}, {3, 5}, {4, 6}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        1, //limit
                        new int[][]{{4, 3}}, // LEFT
                        new int[][]{{4, 5}}, // RIGHT
                        new int[][]{{5, 4}}, // UP
                        new int[][]{{3, 4}}, // DOWN
                        new int[][]{{3, 3}}, // SW
                        new int[][]{{5, 3}}, // NW
                        new int[][]{{3, 5}}, // SE
                        new int[][]{{5, 5}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        3, //limit
                        new int[][]{{4, 3}, {4, 2}, {4, 1}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}, {4, 7}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}, {7, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}, {1, 4}}, // down
                        new int[][]{{3, 3}, {2, 2}, {1, 1}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        2, //limit
                        new int[][]{{4, 3}, {4, 2}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}}, // DOWN
                        new int[][]{{3, 3}, {2, 2}}, // SW
                        new int[][]{{5, 3}, {6, 2}}, // NW
                        new int[][]{{3, 5}, {2, 6}}, // SE
                        new int[][]{{5, 5}, {6, 6}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        -1, //limit
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}, {4, 7}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}, {7, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}}, // DOWN
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        10, //limit
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}, {4, 7}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}, {7, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}}, // DOWN
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        0, //limit
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}, {4, 7}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}, {7, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}}, // DOWN
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        4, //limit
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}, {4, 7}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}, {7, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}}, // DOWN
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        5, //limit
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}}, // LEFT
                        new int[][]{{4, 5}, {4, 6}, {4, 7}}, // RIGHT
                        new int[][]{{5, 4}, {6, 4}, {7, 4}}, // UP
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}}, // DOWN
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                }
        };
    }

    @Test
    @UseDataProvider("loadDataForGetVectorMoveByToLineToColumn")
    public void getVectorMoveByToLineToColumnTest(
            String color,
            int currentLine,
            int currentColumn,
            int toLine,
            int toColumn,
            VectorEnum expected
    ){

    }

    @DataProvider
    public static Object[][] loadDataForGetVectorMoveByToLineToColumn(){
        return new Object[][]{
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        3,
                        5,
                        VectorEnum.SE,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        3,
                        3,
                        VectorEnum.SW,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        5,
                        5,
                        VectorEnum.NE,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        5,
                        3,
                        VectorEnum.NW,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        3,
                        4,
                        VectorEnum.DOWN,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        5,
                        4,
                        VectorEnum.UP,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        4,
                        5,
                        VectorEnum.RIGHT,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        4,
                        4,
                        4,
                        3,
                        VectorEnum.LEFT,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        1,
                        1,
                        1,
                        1,
                        VectorEnum.NONE,
                },
                {
                        ChessPiece.COLOR_WHITE,
                        1,
                        1,
                        2,
                        2,
                        VectorEnum.NE,
                }
        };
    }
}
