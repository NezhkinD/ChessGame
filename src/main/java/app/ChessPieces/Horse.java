package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import static app.Exception.CannotAttackException.MESSAGE_CANNOT_ATTACK;
import static app.Exception.CannotAttackException.MESSAGE_PIECE_NOT_FOUND;
import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;
import static app.Exception.CannotMoveException.MESSAGE_PIECE_BLOCK_MOVE;

public class Horse extends ChessPiece {
    public static String SYMBOL = "H";

    public Horse(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException {
        if (!moveEntity.vectorEnum.equals(VectorEnum.HORSE)){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        if (chessBoard.getChessPiece(moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn).isPresent()){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_PIECE_BLOCK_MOVE);
        }

        return true;
    }

    @Override
    public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotAttackException, CannotMoveException {
        if (!moveEntity.vectorEnum.equals(VectorEnum.HORSE)){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_CANNOT_ATTACK);
        }

        if (chessBoard.getChessPiece(moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn).isEmpty()){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_PIECE_NOT_FOUND);
        }

        return true;
    }

    @Override
    public String getSymbol() {return SYMBOL;}
}
