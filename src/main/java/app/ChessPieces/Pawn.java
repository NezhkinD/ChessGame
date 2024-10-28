package app.ChessPieces;

import app.ChessBoard;
import app.Exception.CannotMoveException;

import java.util.Objects;

import static app.Exception.CannotMoveException.*;

public class Pawn extends ChessPiece {
    private static final int[][] POSSIBLE_MOVES = {
            {1, 0},
            {-1, 0}
    };

    private static final int[][] POSSIBLE_MOVES_FIRST = {
            {1, 0},
            {-1, 0},
            {2, 0},
            {-2, 0}
    };

    public Pawn(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn) throws CannotMoveException {
        if (currentColumn != toColumn) {
            throw new CannotMoveException(this, toLine, toColumn, MESSAGE_CURRENT_COLUMN_NOT_EQ_TO_COLUMN);
        }

        if (super.checkBoardLimits(toLine, toColumn)) {
            throw new CannotMoveException(this, toLine, toColumn, MESSAGE_BOARD_LIMIT);
        }

        if (!canMoveVertical(chessBoard, toLine)) {
            return false;
        }

        if (Objects.equals(super.color, COLOR_WHITE) && toLine <= currentLine) {
            throw new CannotMoveException(this, toLine, toColumn, MESSAGE_WHITE_LINE);
        }

        if (Objects.equals(super.color, COLOR_BLACK) && toLine >= currentLine) {
            throw new CannotMoveException(this, toLine, toColumn, MESSAGE_BLACK_LINE);
        }

        if (!super.checkPossibleMoves(chessBoard, toLine, toColumn)) {
            throw new CannotMoveException(this, toLine, toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        return true;
    }


    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    protected int[][] getPossibleMoves() {
        int[][] possibleMoves = POSSIBLE_MOVES;
        if (currentLine == 1 || currentLine == 6) {
            possibleMoves = POSSIBLE_MOVES_FIRST;
        }

        return possibleMoves;
    }
}
