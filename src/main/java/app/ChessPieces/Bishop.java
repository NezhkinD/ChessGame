package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;
import static app.Exception.CannotMoveException.*;

public class Bishop extends ChessPiece {
    public Bishop(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException {
        if (!moveEntity.vectorEnum.equals(VectorEnum.SW) && !moveEntity.vectorEnum.equals(VectorEnum.SE) && !moveEntity.vectorEnum.equals(VectorEnum.NW) && !moveEntity.vectorEnum.equals(VectorEnum.NE)){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        checkMove(chessBoard, moveEntity);

        return true;
    }

    @Override
    public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotAttackException {
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
