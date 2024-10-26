package app.ChessPieces;

import app.ChessBoard;

public class Horse extends ChessPiece {

    private static final int[][] POSSIBLE_MOVES = {
            {2, -1},
            {2, 1},
            {1, -2},
            {1, 2},
            {-1, -2},
            {-1, 2},
            {-2, -1},
            {-2, -1},
            {-2, -1}
    };

    public Horse(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean canMove = false;
        for (int[] moves : POSSIBLE_MOVES) {
            int possibleToLine = currentLine + moves[0];
            int possibleToColumn = currentColumn + moves[1];

            if (possibleToLine < ChessBoard.LINES || possibleToColumn < ChessBoard.COLUMNS || possibleToLine >= ChessBoard.LINES || possibleToColumn >= ChessBoard.COLUMNS) {
                continue;
            }

            if (chessBoard.board[possibleToLine][possibleToColumn] != null) {
                continue;
            }

            if (possibleToLine == toLine && possibleToColumn == toColumn) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    @Override
    public String getSymbol() {
        return formatSymbol("H");
    }
}
