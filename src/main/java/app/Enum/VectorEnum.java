package app.Enum;

import app.Entity.CoordinatesEntity;

public enum VectorEnum {
    UNDIFINE, NONE, LEFT, RIGHT, UP, DOWN, NW, NE, SW, SE, HORSE;

    private static final int[][] POSSIBLE_HORSE_MOVES = {
            {2, -1},
            {2, 1},
            {1, -2},
            {1, 2},
            {-1, -2},
            {-1, 2},
            {-2, -1},
            {-2, 1}
    };

    public static VectorEnum defineVector(CoordinatesEntity coordinatesEntity) {
        if (coordinatesEntity.toLine == coordinatesEntity.currentLine && coordinatesEntity.toColumn == coordinatesEntity.currentColumn) {
            return VectorEnum.NONE;
        }

        for (int[] moves : POSSIBLE_HORSE_MOVES) {
            if ((coordinatesEntity.currentLine + moves[0]) == coordinatesEntity.toLine && (coordinatesEntity.currentColumn + moves[1] == coordinatesEntity.toColumn)){
                return VectorEnum.HORSE;
            }
        }

        if (coordinatesEntity.toLine > coordinatesEntity.currentLine && coordinatesEntity.currentColumn == coordinatesEntity.toColumn) {
            return VectorEnum.UP;
        }

        if (coordinatesEntity.toLine < coordinatesEntity.currentLine && coordinatesEntity.currentColumn == coordinatesEntity.toColumn) {
            return VectorEnum.DOWN;
        }

        if (coordinatesEntity.toLine == coordinatesEntity.currentLine && coordinatesEntity.toColumn < coordinatesEntity.currentColumn) {
            return VectorEnum.LEFT;
        }

        if (coordinatesEntity.toLine == coordinatesEntity.currentLine && coordinatesEntity.toColumn > coordinatesEntity.currentColumn) {
            return VectorEnum.RIGHT;
        }

        if (coordinatesEntity.toLine > coordinatesEntity.currentLine && coordinatesEntity.toColumn > coordinatesEntity.currentColumn && (coordinatesEntity.toLine - coordinatesEntity.currentLine) == (coordinatesEntity.toColumn - coordinatesEntity.currentColumn)) {
            return VectorEnum.NE;
        }

        if (coordinatesEntity.toLine > coordinatesEntity.currentLine && coordinatesEntity.toColumn < coordinatesEntity.currentColumn && (coordinatesEntity.toLine - coordinatesEntity.currentLine) == (coordinatesEntity.currentColumn - coordinatesEntity.toColumn)) {
            return VectorEnum.NW;
        }

        if (coordinatesEntity.toLine < coordinatesEntity.currentLine && coordinatesEntity.toColumn < coordinatesEntity.currentColumn && (coordinatesEntity.currentLine - coordinatesEntity.toLine) == (coordinatesEntity.currentColumn - coordinatesEntity.toColumn)) {
            return VectorEnum.SW;
        }

        if (coordinatesEntity.toLine < coordinatesEntity.currentLine && coordinatesEntity.toColumn > coordinatesEntity.currentColumn && (coordinatesEntity.currentLine - coordinatesEntity.toLine) == (coordinatesEntity.toColumn - coordinatesEntity.currentColumn)) {
            return VectorEnum.SE;
        }

        return VectorEnum.UNDIFINE;
    }
}
