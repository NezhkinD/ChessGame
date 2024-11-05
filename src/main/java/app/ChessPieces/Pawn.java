package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import java.util.Objects;

import static app.Exception.CannotAttackException.MESSAGE_CANNOT_ATTACK;
import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;
import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE_PAWN;

public class Pawn extends ChessPiece {
    public static String SYMBOL = "P";

    public Pawn(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException {
        if (moveEntity.limit > 2) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        if (moveEntity.limit == 2 && currentLine != 1 && currentLine != 6) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE_PAWN);
        }

        if (!moveEntity.vectorEnum.equals(VectorEnum.UP) && Objects.equals(super.color, COLOR_WHITE)) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        if (!moveEntity.vectorEnum.equals(VectorEnum.DOWN) && Objects.equals(super.color, COLOR_BLACK)) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        checkMove(chessBoard, moveEntity);

        return true;
    }

    @Override
    public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotAttackException, CannotMoveException {
        if (moveEntity.limit != 1) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_CANNOT_ATTACK);
        }

        if (Objects.equals(super.color, COLOR_WHITE) && !moveEntity.vectorEnum.equals(VectorEnum.NW) && !moveEntity.vectorEnum.equals(VectorEnum.NE)) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_CANNOT_ATTACK);
        }

        if (Objects.equals(super.color, COLOR_BLACK) && !moveEntity.vectorEnum.equals(VectorEnum.SW) && !moveEntity.vectorEnum.equals(VectorEnum.SE)) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_CANNOT_ATTACK);
        }

        checkAttack(chessBoard, moveEntity);
        return true;
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}
