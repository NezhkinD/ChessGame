package app.ChessPieces;

import app.ChessBoard;
import app.Entity.MoveEntity;
import app.Exception.CannotAttackException;
import app.Exception.CannotMoveException;

import java.util.*;

import static app.Exception.CannotAttackException.MESSAGE_NOT_PIECE_TO_ATTACK;
import static app.Exception.CannotMoveException.*;

abstract public class ChessPiece {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";
    public String color;
    public boolean check = true;
    public int currentLine;
    public int currentColumn;

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotMoveException;

    abstract public boolean canAttack(ChessBoard chessBoard, MoveEntity moveEntity) throws CannotAttackException;

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
        int[][] possibleMoves = getPossibleMoves(moveEntity);
        Optional<ChessPiece> closestChessPiece = getClosestChessPiece(board, possibleMoves);
        if (closestChessPiece.isPresent()){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_PIECE_BLOCK_MOVE + " - " + closestChessPiece.get().getSymbolWithColor());
        }
    }

    /**
     * Проверить возможно ли атаковать фигуру
     */
    public void checkAttack(ChessBoard board, MoveEntity moveEntity) throws CannotMoveException {
        int[][] possibleMoves = getPossibleMoves(moveEntity);
        Optional<ChessPiece> closestChessPiece = getClosestChessPiece(board, possibleMoves);
        if (closestChessPiece.isEmpty()){
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_PIECE_TO_ATTACK);
        }
    }

    /**
     * Получить все возможные клетки для движения фигуры
     */
    public int[][] getPossibleMoves(MoveEntity moveEntity) throws CannotMoveException {

        int[][] possibleMoves;
        switch (moveEntity.vectorEnum) {
            case UP -> possibleMoves = getAllVerticalUpMoves(moveEntity.coordinatesEntity.toLine - currentLine);
            case DOWN -> possibleMoves = getAllVerticalDownMoves(currentLine - moveEntity.coordinatesEntity.toLine);
            case LEFT -> possibleMoves = getAllHorizontalLeftMoves(moveEntity.coordinatesEntity.toColumn - currentColumn);
            case RIGHT -> possibleMoves = getAllHorizontalRightMoves(currentColumn - moveEntity.coordinatesEntity.toColumn);
            case NE -> possibleMoves = getAllDiagonalNEMoves(moveEntity.coordinatesEntity.toColumn - currentColumn);
            case NW -> possibleMoves = getAllDiagonalNWMoves(currentColumn - moveEntity.coordinatesEntity.toColumn);
            case SW -> possibleMoves = getAllDiagonalSWMoves(moveEntity.coordinatesEntity.toColumn - currentColumn);
            case SE -> possibleMoves = getAllDiagonalSEMoves(currentColumn - moveEntity.coordinatesEntity.toColumn);
            default -> throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_UNDEFINE_MOVE);
        }

        if (possibleMoves.length == 0) {
            throw new CannotMoveException(this, moveEntity.coordinatesEntity.toLine, moveEntity.coordinatesEntity.toColumn, MESSAGE_NOT_POSSIBLE_MOVE);
        }

        return possibleMoves;
    }

    /**
     * Проверить есть ли фигура на траектории движения
     */
    public Optional<ChessPiece> getClosestChessPiece(ChessBoard board, int[][] possibleMoves)
    {
        for (int[] moves : possibleMoves) {
            int line = moves[0];
            int column = moves[1];

            if (board.getChessPiece(line, column).isPresent()) {
                return board.getChessPiece(line, column);
            }
        }

        return Optional.empty();
    }

    /**
     * Получить все ходы по горизонтали слева (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) слева с указанным лимитом
     */
    public int[][] getAllHorizontalLeftMoves(int limit) {
        int limitLeft = currentColumn - limit;
        List<int[]> moves = new ArrayList<>();

        if (limitLeft < 0) {
            limitLeft = 0;
        }

        if (limit <= 0) {
            limitLeft = 0;
        }

        for (int i = currentColumn; i >= limitLeft; i--) {
            if (i == currentColumn) {
                continue;
            }
            moves.add(new int[]{currentLine, i});
        }

        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по горизонтали справа(если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) справа с указанным лимитом
     */
    public int[][] getAllHorizontalRightMoves(int limit) {
        int limitRight = currentColumn + limit;
        List<int[]> moves = new ArrayList<>();

        if (limitRight >= ChessBoard.COLUMNS - 1) {
            limitRight = ChessBoard.COLUMNS - 1;
        }

        if (limit <= 0) {
            limitRight = ChessBoard.COLUMNS - 1;
        }

        for (int i = currentColumn; i <= limitRight; i++) {
            if (i == currentColumn) {
                continue;
            }
            moves.add(new int[]{currentLine, i});
        }

        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по вертикали снизу (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) снизу с указанным лимитом
     */
    public int[][] getAllVerticalDownMoves(int limit) {
        int limitDown = currentLine - limit;
        List<int[]> moves = new ArrayList<>();

        if (limitDown < 0) {
            limitDown = 0;
        }

        if (limit <= 0) {
            limitDown = 0;
        }

        for (int i = currentLine; i >= limitDown; i--) {
            if (i == currentLine) {
                continue;
            }
            moves.add(new int[]{i, currentColumn});
        }

        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по вертикали сверху (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) сверху с указанным лимитом
     */
    public int[][] getAllVerticalUpMoves(int limit) {
        int limitUp = currentLine + limit;
        List<int[]> moves = new ArrayList<>();

        if (limitUp >= ChessBoard.LINES - 1) {
            limitUp = ChessBoard.LINES - 1;
        }

        if (limit <= 0) {
            limitUp = ChessBoard.LINES - 1;
        }

        for (int i = currentLine; i <= limitUp; i++) {
            if (i == currentLine) {
                continue;
            }
            moves.add(new int[]{i, currentColumn});
        }

        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по диагонали NE северо-восток (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public int[][] getAllDiagonalNEMoves(int limit) {
        if (limit <= 0) {
            limit = ChessBoard.COLUMNS;
        }

        List<int[]> moves = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            int newLine = currentLine + i;
            int newColumn = currentColumn + i;

            if (newLine >= ChessBoard.LINES || newColumn >= ChessBoard.COLUMNS) {
                break;
            }

            moves.add(new int[]{newLine, newColumn});
        }
        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по диагонали SW юго-запад (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public int[][] getAllDiagonalSWMoves(int limit) {
        if (limit <= 0) {
            limit = ChessBoard.COLUMNS;
        }

        List<int[]> moves = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            int newLine = currentLine - i;
            int newColumn = currentColumn - i;

            if (newLine < 0 || newColumn < 0) {
                break;
            }

            moves.add(new int[]{newLine, newColumn});
        }
        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по диагонали NW северо-запад (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public int[][] getAllDiagonalNWMoves(int limit) {
        int limitNW = currentLine + limit;
        if (limitNW >= ChessBoard.COLUMNS - 1 || limit <= 0) {
            limitNW = ChessBoard.COLUMNS - 1;
        }

        List<int[]> moves = new ArrayList<>();

        for (int i = currentLine; i <= limitNW; i++) {
            if (i == currentLine) {
                continue;
            }

            int column = (currentColumn - i) + currentLine;
            if (column < 0) {
                continue;
            }

            moves.add(new int[]{i, column});
        }
        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по диагонали SE юго-восток (если limit <= 0)
     * Если limit > 0 - получим все ходы, не выходящие за пределы доски, с указанным лимитом
     */
    public int[][] getAllDiagonalSEMoves(int limit) {
        int limitSE = currentLine - limit;
        if (limit <= 0) {
            limitSE = -ChessBoard.LINES;
        }

        List<int[]> moves = new ArrayList<>();

        for (int i = currentLine; i >= limitSE; i--) {
            if (i == currentLine) {
                continue;
            }

            int column = (currentColumn - i) + currentLine;

            if (i < 0 || column >= ChessBoard.COLUMNS) {
                continue;
            }
            moves.add(new int[]{i, column});
        }
        return moves.toArray(new int[moves.size()][]);
    }
}
