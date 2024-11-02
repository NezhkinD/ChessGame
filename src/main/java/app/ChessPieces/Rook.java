package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;

public class Rook extends ChessPiece{
    public static String SYMBOL = "R";

    public Rook(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException {
        if (!moveEntity.vectorEnum.equals(VectorEnum.UP) && !moveEntity.vectorEnum.equals(VectorEnum.LEFT) && !moveEntity.vectorEnum.equals(VectorEnum.RIGHT) && !moveEntity.vectorEnum.equals(VectorEnum.DOWN)){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        checkMove(chessBoard, moveEntity);

        return true;
    }

    @Override
    public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException, CannotAttackException {
        checkAttack(chessBoard, moveEntity);
        return true;
    }

    @Override
    public String getSymbol() {return SYMBOL;}
}
