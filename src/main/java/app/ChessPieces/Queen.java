package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Enum.VectorEnum;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import static app.Exception.CannotMoveException.MESSAGE_NOT_POSSIBLE_MOVE;
public class Queen extends ChessPiece{
    public static String SYMBOL = "Q";

    public Queen(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException {
        if (moveEntity.vectorEnum.equals(VectorEnum.HORSE) && moveEntity.vectorEnum.equals(VectorEnum.UNDIFINE) && moveEntity.vectorEnum.equals(VectorEnum.NONE) ){
            throw new CannotMoveException(moveEntity.coordinatesEntity, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        checkMove(chessBoard, moveEntity);

        return true;
    }

    @Override
    public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotAttackException, CannotMoveException {
        checkAttack(chessBoard, moveEntity);
        return true;
    }

    @Override
    public String getSymbol() {return SYMBOL;}
}
