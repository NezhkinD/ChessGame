package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import java.util.Objects;

import static app.Exception.CannotMoveException.*;

public class Pawn extends ChessPiece {
    public Pawn(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException {
        if (moveEntity.limit > 2){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        if (moveEntity.limit == 2 && currentLine != 1 && currentLine != 6){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE_PAWN);
        }

        if (!moveEntity.vectorEnum.equals(VectorEnum.UP) && Objects.equals(super.color, COLOR_WHITE)){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        if (!moveEntity.vectorEnum.equals(VectorEnum.DOWN) && Objects.equals(super.color, COLOR_BLACK)){
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
    public String getSymbol() {return "P";}
}
