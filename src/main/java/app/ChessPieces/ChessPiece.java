package app.ChessPieces;

import app.ChessBoard;
import app.Entity.CoordinatesEntity;
import app.Entity.MoveEntity;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import java.util.*;

import static app.Exception.CannotAttackException.*;
import static app.Exception.CannotMoveException.*;

abstract public class ChessPiece {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";
    public String color;
    public boolean check = true;
    public int currentLine;
    public int currentColumn;

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException;

    abstract public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotAttackException, CannotMoveException;

    abstract public String getSymbol();

    public ChessPiece(String color, int currentLine, int currentColumn) {
        this.color = color;
        this.currentLine = currentLine;
        this.currentColumn = currentColumn;
    }

    public String getColor() {
        return this.color;
    }

    public String getSymbolWithColor() {
        if (Objects.equals(this.color, COLOR_BLACK)) {
            return ANSI_BLACK_BACKGROUND + ANSI_WHITE + getSymbol() + "b" + ANSI_RESET;
        }

        return ANSI_WHITE_BACKGROUND + ANSI_BLACK + getSymbol() + "w" + ANSI_RESET;
    }

    /**
     * Проверить возможно ли перемещение к указанной клетке
     */
    public void checkMove(ChessBoard board, MoveEntity moveEntity) throws CannotMoveException {
        List<CoordinatesEntity> possibleMoves = getPossibleMoves(moveEntity);


        Optional<ChessPiece> closestChessPiece = getClosestChessPiece(board, possibleMoves);
        if (closestChessPiece.isPresent()) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_PIECE_BLOCK_MOVE + " - " + closestChessPiece.get().getSymbolWithColor());
        }
    }

    /**
     * Проверить возможно ли атаковать фигуру
     * Для атаки, м/у фигурами не должно быть других фигур
     */
    public void checkAttack(ChessBoard board, MoveEntity moveEntity) throws CannotMoveException, CannotAttackException {
        List<CoordinatesEntity> possibleMoves = getPossibleMoves(moveEntity);
        int lastIndex = moveEntity.limit - 1;

        if (lastIndex > 1) {
            if (possibleMoves.get(lastIndex).toLine != moveEntity.coordinatesEntity.toLine || possibleMoves.get(lastIndex).toColumn != moveEntity.coordinatesEntity.toColumn) {
                throw new CannotAttackException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_CANNOT_ATTACK);
            }
        }

        if (board.getChessPiece(moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn).isEmpty()) {
            throw new CannotAttackException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_PIECE_TO_ATTACK);
        }

        if (moveEntity.limit > 1){
            Optional<ChessPiece> closestChessPiece = getClosestChessPiece(board, possibleMoves);
            if (closestChessPiece.isPresent() && (closestChessPiece.get().currentLine != moveEntity.coordinatesEntity.toLine || closestChessPiece.get().currentColumn != moveEntity.coordinatesEntity.toColumn)) {
                throw new CannotAttackException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_PIECE_BLOCK_ATTACK + " " + closestChessPiece.get().getSymbolWithColor());
            }
        }
    }

    /**
     * Получить все возможные клетки для движения фигуры
     */
    public List<CoordinatesEntity> getPossibleMoves(MoveEntity moveEntity) throws CannotMoveException {

        List<CoordinatesEntity> xyList;
        switch (moveEntity.vectorEnum) {
            case UP -> xyList = getAllVerticalUpMoves(moveEntity);
            case DOWN -> xyList = getAllVerticalDownMoves(moveEntity);
            case LEFT -> xyList = getAllHorizontalLeftMoves(moveEntity);
            case RIGHT -> xyList = getAllHorizontalRightMoves(moveEntity);
            case NE -> xyList = getAllDiagonalNEMoves(moveEntity);
            case NW -> xyList = getAllDiagonalNWMoves(moveEntity);
            case SW -> xyList = getAllDiagonalSWMoves(moveEntity);
            case SE -> xyList = getAllDiagonalSEMoves(moveEntity);
            default ->
                    throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_UNDEFINE_MOVE);
        }

        if (xyList.size() == 0) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        return xyList;
    }

    /**
     * Проверить есть ли фигура на траектории движения
     */
    public Optional<ChessPiece> getClosestChessPiece(ChessBoard board, List<CoordinatesEntity> possibleMoves) {
        for (CoordinatesEntity moves : possibleMoves) {
            if (moves == null) {
                break;
            }

            if (board.getChessPiece(moves.toLine, moves.toColumn).isPresent()) {
                return board.getChessPiece(moves.toLine, moves.toColumn);
            }
        }

        return Optional.empty();
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по горизонтали слева (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) слева с указанным лимитом
     */
    public List<CoordinatesEntity> getAllHorizontalLeftMoves(MoveEntity moveEntity) {
        int limitLeft = moveEntity.coordinatesEntity.currentColumn - moveEntity.limit;
        List<CoordinatesEntity> xyList = new ArrayList<>();

        if (limitLeft < 0) {
            limitLeft = 0;
        }

        if (moveEntity.limit <= 0) {
            limitLeft = 0;
        }

        for (int i = moveEntity.coordinatesEntity.currentColumn; i >= limitLeft; i--) {
            if (i == moveEntity.coordinatesEntity.currentColumn) {
                continue;
            }
            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, moveEntity.coordinatesEntity.currentLine, i));
        }

        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по горизонтали справа(если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) справа с указанным лимитом
     */
    public List<CoordinatesEntity> getAllHorizontalRightMoves(MoveEntity moveEntity) {
        int limitRight = moveEntity.coordinatesEntity.currentColumn + moveEntity.limit;
        List<CoordinatesEntity> xyList = new ArrayList<>();

        if (limitRight >= ChessBoard.COLUMNS - 1) {
            limitRight = ChessBoard.COLUMNS - 1;
        }

        if (moveEntity.limit <= 0) {
            limitRight = ChessBoard.COLUMNS - 1;
        }

        for (int i = moveEntity.coordinatesEntity.currentColumn; i <= limitRight; i++) {
            if (i == moveEntity.coordinatesEntity.currentColumn) {
                continue;
            }
            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, moveEntity.coordinatesEntity.currentLine, i));
        }

        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по вертикали снизу (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) снизу с указанным лимитом
     */
    public List<CoordinatesEntity> getAllVerticalDownMoves(MoveEntity moveEntity) {
        int limitDown = moveEntity.coordinatesEntity.currentLine - moveEntity.limit;
        List<CoordinatesEntity> xyList = new ArrayList<>();

        if (limitDown < 0) {
            limitDown = 0;
        }

        if (moveEntity.limit <= 0) {
            limitDown = 0;
        }

        for (int i = moveEntity.coordinatesEntity.currentLine; i >= limitDown; i--) {
            if (i == moveEntity.coordinatesEntity.currentLine) {
                continue;
            }
            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, i, moveEntity.coordinatesEntity.currentColumn));
        }

        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по вертикали сверху (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) сверху с указанным лимитом
     */
    public List<CoordinatesEntity> getAllVerticalUpMoves(MoveEntity moveEntity) {
        int limitUp = moveEntity.coordinatesEntity.currentLine + moveEntity.limit;
        List<CoordinatesEntity> xyList = new ArrayList<>();

        if (limitUp >= ChessBoard.LINES - 1) {
            limitUp = ChessBoard.LINES - 1;
        }

        if (moveEntity.limit <= 0) {
            limitUp = ChessBoard.LINES - 1;
        }

        for (int i = moveEntity.coordinatesEntity.currentLine; i <= limitUp; i++) {
            if (i == moveEntity.coordinatesEntity.currentLine) {
                continue;
            }
            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, i, moveEntity.coordinatesEntity.currentColumn));
        }

        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по диагонали NE северо-восток (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public List<CoordinatesEntity> getAllDiagonalNEMoves(MoveEntity moveEntity) {
        int limit = moveEntity.limit;
        if (limit <= 0) {
            limit = ChessBoard.COLUMNS;
        }

        List<CoordinatesEntity> xyList = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            int newLine = moveEntity.coordinatesEntity.currentLine + i;
            int newColumn = moveEntity.coordinatesEntity.currentColumn + i;

            if (newLine >= ChessBoard.LINES || newColumn >= ChessBoard.COLUMNS) {
                break;
            }

            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, newLine, newColumn));
        }
        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по диагонали SW юго-запад (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public List<CoordinatesEntity> getAllDiagonalSWMoves(MoveEntity moveEntity) {
        int limit = moveEntity.limit;
        if (limit <= 0) {
            limit = ChessBoard.COLUMNS;
        }

        List<CoordinatesEntity> xyList = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            int newLine = moveEntity.coordinatesEntity.currentLine - i;
            int newColumn = moveEntity.coordinatesEntity.currentColumn - i;

            if (newLine < 0 || newColumn < 0) {
                break;
            }

            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, newLine, newColumn));
        }
        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по диагонали NW северо-запад (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public List<CoordinatesEntity> getAllDiagonalNWMoves(MoveEntity moveEntity) {
        int limitNW = moveEntity.coordinatesEntity.currentLine + moveEntity.limit;
        if (limitNW >= ChessBoard.COLUMNS - 1 || moveEntity.limit <= 0) {
            limitNW = ChessBoard.COLUMNS - 1;
        }

        List<CoordinatesEntity> xyList = new ArrayList<>();

        for (int i = moveEntity.coordinatesEntity.currentLine; i <= limitNW; i++) {
            if (i == moveEntity.coordinatesEntity.currentLine) {
                continue;
            }

            int column = (moveEntity.coordinatesEntity.currentColumn - i) + moveEntity.coordinatesEntity.currentLine;
            if (column < 0) {
                continue;
            }

            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, i, column));
        }
        return xyList;
    }

    /**
     * Получить все ходы (от текущей клетки до клетки назначения) по диагонали SE юго-восток (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public List<CoordinatesEntity> getAllDiagonalSEMoves(MoveEntity moveEntity) {
        int limitSE = moveEntity.coordinatesEntity.currentLine - moveEntity.limit;
        if (moveEntity.limit <= 0) {
            limitSE = -ChessBoard.LINES;
        }

        List<CoordinatesEntity> xyList = new ArrayList<>();

        for (int i = moveEntity.coordinatesEntity.currentLine; i >= limitSE; i--) {
            if (i == moveEntity.coordinatesEntity.currentLine) {
                continue;
            }

            int column = (moveEntity.coordinatesEntity.currentColumn - i) + moveEntity.coordinatesEntity.currentLine;

            if (i < 0 || column >= ChessBoard.COLUMNS) {
                continue;
            }
            xyList.add(new CoordinatesEntity(moveEntity.coordinatesEntity.currentLine, moveEntity.coordinatesEntity.currentColumn, i, column));
        }
        return xyList;
    }
}
