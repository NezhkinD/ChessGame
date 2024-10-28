package app.ChessPieces;

import app.ChessBoard;
import app.Exception.CannotMoveException;

import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;

public class Horse extends ChessPiece {

    protected static final int[][] POSSIBLE_MOVES = {
            {2, -1},
            {2, 1},
            {1, -2},
            {1, 2},
            {-1, -2},
            {-1, 2},
            {-2, -1},
            {-2, 1}
    };

    public Horse(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn) throws CannotMoveException {
        if (!checkPossibleMoves(chessBoard, toLine, toColumn)) {
            throw new CannotMoveException(this, toLine, toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        //todo добавить проверку на наличие другой фигуры, мешающей передвижению

        return true;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    protected int[][] getPossibleMoves() {
        return POSSIBLE_MOVES;
    }
}
