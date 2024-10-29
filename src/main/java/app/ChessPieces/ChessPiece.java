package app.ChessPieces;

import app.ChessBoard;
import app.Exception.CannotMoveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static app.Exception.CannotMoveException.*;
import static java.lang.Math.abs;

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

    public ChessPiece(String color, int currentLine, int currentColumn) {
        this.color = color;
        this.currentLine = currentLine;
        this.currentColumn = currentColumn;
    }

    public String getColor() {
        return this.color;
    }

    /**
     * Проверяем выходим ли за рамки доски
     */
    public boolean checkBoardLimits(int toLine, int toColumn) {
        if (toLine >= ChessBoard.LINES || toLine < 0) {
            return true;
        }

        if (toColumn >= ChessBoard.COLUMNS || toColumn < 0) {
            return true;
        }

        return false;
    }

    /**
     * Есть ли мешающая фигура на линии передвижения
     */
    public boolean isChessPieceOnMove(ChessBoard chessBoard, int toLine, int toColumn) {
        if (chessBoard.board[toLine][toColumn] != null) {
            return true;
        }

        if (currentLine != toLine) {
            int[] lines = {currentLine, toLine};
            int maxLine = Arrays.stream(lines).max().getAsInt();
            int minLine = Arrays.stream(lines).min().getAsInt();
            for (int i = minLine; i <= maxLine; i++) {
                if (i == currentLine || i == currentLine) {
                    continue;
                }

                if (chessBoard.board[i][currentColumn] != null) {
                    return true;
                }
            }
        }

        if (currentColumn != toColumn) {
            int[] columns = {currentColumn, toColumn};
            int maxColumn = Arrays.stream(columns).max().getAsInt();
            int minColumn = Arrays.stream(columns).min().getAsInt();
            for (int i = minColumn; i <= maxColumn; i++) {
                if (i == currentColumn || i == currentColumn) {
                    continue;
                }

                if (chessBoard.board[i][currentColumn] != null) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getSymbolWithColor() {
        if (Objects.equals(this.color, COLOR_BLACK)) {
            return ANSI_BLACK_BACKGROUND + ANSI_WHITE + getSymbol() + "b" + ANSI_RESET;
        }

        return ANSI_WHITE_BACKGROUND + ANSI_BLACK + getSymbol() + "w" + ANSI_RESET;
    }

    /**
     * Может ли фигура передвинуться по вертикали
     */
    protected boolean canMoveVertical(ChessBoard chessBoard, int toLine) throws CannotMoveException {
        int[] fromToLines = {currentLine, toLine};
        int min = Arrays.stream(fromToLines).min().getAsInt();
        int max = Arrays.stream(fromToLines).max().getAsInt();

        for (int i = min; i <= max; i++) {
            if (i == currentLine) {
                continue;
            }

            if (chessBoard.board[i][currentColumn] != null) {
                throw new CannotMoveException(this, toLine, currentColumn, MESSAGE_PIECE_BLOCK_MOVE + " " + chessBoard.board[i][currentColumn].getSymbolWithColor());
            }
        }

        return true;
    }

    /**
     * Может ли фигура передвинуться по горизонтали
     */
    protected boolean canMoveHorizontal(ChessBoard chessBoard, int toColumn) throws CannotMoveException {
        int[] fromToLines = {currentColumn, toColumn};
        int min = Arrays.stream(fromToLines).min().getAsInt();
        int max = Arrays.stream(fromToLines).max().getAsInt();

        for (int i = min; i <= max; i++) {
            if (i == currentColumn) {
                continue;
            }

            if (chessBoard.board[currentLine][i] != null) {
                throw new CannotMoveException(this, currentLine, toColumn, MESSAGE_PIECE_BLOCK_MOVE + " " + chessBoard.board[i][currentColumn].getSymbolWithColor());
            }
        }

        return true;
    }

    /**
     * Проверка траектории движения фигуры. Может ли фигура двигаться по указанной траектории
     */
    protected boolean checkPossibleMoves(ChessBoard board, int toLine, int toColumn) throws CannotMoveException {
        boolean canMove = false;
        for (int[] moves : getPossibleMoves()) {
            int possibleToLine = currentLine + moves[0];
            int possibleToColumn = currentColumn + moves[1];

            if (possibleToLine < 0 || possibleToColumn < 0 || possibleToLine >= ChessBoard.LINES || possibleToColumn >= ChessBoard.COLUMNS) {
                continue;
            }

            if (board.board[possibleToLine][possibleToColumn] != null) {
                continue;
            }

            if (possibleToLine == toLine && possibleToColumn == toColumn) {
                if (board.board[toLine][toColumn] != null) {
                    throw new CannotMoveException(this, toLine, toColumn, MESSAGE_PIECE_BLOCK_MOVE);
                }
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    /**
     * Может ли фигура передвинуться по диагонали
     *
     */
    protected boolean canMoveDiagonal(ChessBoard chessBoard, int fromLine, int toLine, int fromColumn, int toColumn) {
        return true;
    }

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn) throws CannotMoveException;

    abstract public String getSymbol();

    abstract protected int[][] getPossibleMoves();

    /**
     * Получить все ходы по вертикали (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) сверху и снизу с указанным лимитом
     * @deprecated
     */
    public int[][] getAllVerticalMoves(int limit) {
        int limitDown = currentColumn - limit;
        int limitUp = currentColumn + limit;
        List<int[]> moves = new ArrayList<>();

        if (limitDown < 0) {
            limitDown = 0;
        }

        if (limitUp >= ChessBoard.COLUMNS - 1) {
            limitUp = ChessBoard.COLUMNS - 1;
        }

        if (limit <= 0) {
            limitDown = 0;
            limitUp = ChessBoard.COLUMNS - 1;
        }

        // берем все ходы снизу
        for (int i = currentColumn; i >= limitDown; i--) {
            if (i == currentColumn) {
                continue;
            }
            moves.add(new int[]{currentLine, i});
        }

        // берем все ходы сверху
        for (int i = currentColumn; i <= limitUp; i++) {
            if (i == currentColumn) {
                continue;
            }
            moves.add(new int[]{currentLine, i});
        }

        return moves.toArray(new int[moves.size()][]);
    }

    /**
     * Получить все ходы по горизонтали (если limit <= 0)
     * Если limit > 0 - получим все ходы (не выходящие за пределы доски) слева и справа с указанным лимитом
     * @deprecated
     */
    public int[][] getAllHorizontalMoves(int limit) {
        int limitLeft = currentLine - limit;
        int limitRight = currentLine + limit;
        List<int[]> moves = new ArrayList<>();

        if (limitLeft < 0) {
            limitLeft = 0;
        }

        if (limitRight >= ChessBoard.LINES - 1) {
            limitRight = ChessBoard.LINES - 1;
        }

        if (limit <= 0) {
            limitLeft = 0;
            limitRight = ChessBoard.LINES - 1;
        }

        for (int i = currentLine; i >= limitLeft; i--) {
            if (i == currentLine) {
                continue;
            }
            moves.add(new int[]{i, currentColumn});
        }

        for (int i = currentLine; i <= limitRight; i++) {
            if (i == currentLine) {
                continue;
            }
            moves.add(new int[]{i, currentColumn});
        }

        return moves.toArray(new int[moves.size()][]);
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

            if (newLine >= ChessBoard.LINES || newColumn >= ChessBoard.COLUMNS){
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

            if (newLine < 0 || newColumn< 0){
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
            if (column < 0){
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

            if (i < 0 || column >= ChessBoard.COLUMNS){
                continue;
            }
            moves.add(new int[]{i, column});
        }
        return moves.toArray(new int[moves.size()][]);
    }
}
