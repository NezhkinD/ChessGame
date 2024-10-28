package ChessPieces;

import app.ChessPieces.ChessPiece;
import app.ChessPieces.Queen;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class QueenTest {

    @Test
    @UseDataProvider("loadDataForPossibleMoves")
    public void possibleMovesTest(
            int currentLine,
            int currentColumn,
            int limit,
            int[][] expectedHorizontal,
            int[][] expectedVertical,
            int[][] expectedDiagonalSW,
            int[][] expectedDiagonalNW,
            int[][] expectedDiagonalSE,
            int[][] expectedDiagonalNE
    ) {

        Queen queen = new Queen(ChessPiece.COLOR_WHITE, currentLine, currentColumn);
        int[][] possibleHorizontalMoves = queen.getAllHorizontalMoves(limit);
        int[][] possibleVerticalMoves = queen.getAllVerticalMoves(limit);
        int[][] possibleDiagonalSWMoves = queen.getAllDiagonalSWMoves(limit);
        int[][] possibleDiagonalNWMoves = queen.getAllDiagonalNWMoves(limit);
        int[][] possibleDiagonalSEMoves = queen.getAllDiagonalSEMoves(limit);
        int[][] possibleDiagonalNEMoves = queen.getAllDiagonalNEMoves(limit);

        Assert.assertArrayEquals(expectedHorizontal, possibleHorizontalMoves);
        Assert.assertArrayEquals(expectedVertical, possibleVerticalMoves);
        Assert.assertArrayEquals(expectedDiagonalSW, possibleDiagonalSWMoves);
        Assert.assertArrayEquals(expectedDiagonalNW, possibleDiagonalNWMoves);
        Assert.assertArrayEquals(expectedDiagonalSE, possibleDiagonalSEMoves);
        Assert.assertArrayEquals(expectedDiagonalNE, possibleDiagonalNEMoves);
    }

    @DataProvider
    public static Object[][] loadDataForPossibleMoves() {
        return new Object[][]{
                {
                        4, //currentLine
                        4, //currentColumn
                        3, //limit
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {5, 4}, {6, 4}, {7, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 5}, {4, 6}, {4, 7}},
                        new int[][]{{3, 3}, {2, 2}, {1, 1}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        1, //limit
                        new int[][]{{3, 4}, {5, 4}},
                        new int[][]{{4, 3}, {4, 5}},
                        new int[][]{{3, 3}}, // SW
                        new int[][]{{5, 3}}, // NW
                        new int[][]{{3, 5}}, // SE
                        new int[][]{{5, 5}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        2, //limit
                        new int[][]{{3, 4}, {2, 4}, {5, 4}, {6, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 5}, {4, 6}},
                        new int[][]{{3, 3}, {2, 2}}, // SW
                        new int[][]{{5, 3}, {6, 2}}, // NW
                        new int[][]{{3, 5}, {2, 6}}, // SE
                        new int[][]{{5, 5}, {6, 6}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        -1, //limit
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}, {5, 4}, {6, 4}, {7, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}, {4, 5}, {4, 6}, {4, 7}},
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        10, //limit
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}, {5, 4}, {6, 4}, {7, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}, {4, 5}, {4, 6}, {4, 7}},
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        0, //limit
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}, {5, 4}, {6, 4}, {7, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}, {4, 5}, {4, 6}, {4, 7}},
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        4, //limit
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}, {5, 4}, {6, 4}, {7, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}, {4, 5}, {4, 6}, {4, 7}},
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                },
                {
                        4, //currentLine
                        4, //currentColumn
                        5, //limit
                        new int[][]{{3, 4}, {2, 4}, {1, 4}, {0, 4}, {5, 4}, {6, 4}, {7, 4}},
                        new int[][]{{4, 3}, {4, 2}, {4, 1}, {4, 0}, {4, 5}, {4, 6}, {4, 7}},
                        new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}}, // SW
                        new int[][]{{5, 3}, {6, 2}, {7, 1}}, // NW
                        new int[][]{{3, 5}, {2, 6}, {1, 7}}, // SE
                        new int[][]{{5, 5}, {6, 6}, {7, 7}}, // NE
                }
        };
    }
}
