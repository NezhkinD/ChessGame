package app.Entity;

import app.ChessBoard;
import app.Enum.VectorEnum;
import app.Exception.CannotMoveException;

import static app.Exception.CannotMoveException.MESSAGE_BOARD_LIMIT;

public class MoveEntity {
    public CoordinatesEntity coordinatesEntity;
    public int limit;
    public VectorEnum vectorEnum;

    public MoveEntity(CoordinatesEntity xy) throws CannotMoveException {
        this.coordinatesEntity = xy;
        vectorEnum = VectorEnum.defineVector(xy);

        if (xy.currentLine < 0
                || xy.currentLine >= ChessBoard.LINES
                || xy.currentColumn < 0
                || xy.currentColumn >= ChessBoard.COLUMNS
                || xy.toColumn < 0
                || xy.toColumn >= ChessBoard.COLUMNS
                || xy.toLine < 0
                || xy.toLine >= ChessBoard.LINES
        ) {
            throw new CannotMoveException(xy, MESSAGE_BOARD_LIMIT);
        }

        switch (vectorEnum) {
            case UP, NE, NW -> limit = xy.toLine - xy.currentLine;
            case DOWN -> limit = xy.currentLine - xy.toLine;
            case LEFT, SE -> limit = xy.toColumn - xy.currentColumn;
            case RIGHT, SW -> limit = xy.currentColumn - xy.toColumn;
            case HORSE -> limit = 1;
            default -> limit = 0;
        }
    }
}
