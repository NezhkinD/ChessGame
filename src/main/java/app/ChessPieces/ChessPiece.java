package app.ChessPieces;

import app.ChessBoard;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

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

        if (currentLine != toLine){
            int[] lines = {currentLine, toLine};
            int maxLine = Arrays.stream(lines).max().getAsInt();
            int minLine = Arrays.stream(lines).min().getAsInt();
            for (int i = minLine; i <= maxLine; i++) {
                if (i == currentLine || i == currentLine){
                    continue;
                }

                if (chessBoard.board[i][currentColumn] != null) {
                    return true;
                }
            }
        }

        if (currentColumn != toColumn){
            int[] columns = {currentColumn, toColumn};
            int maxColumn = Arrays.stream(columns).max().getAsInt();
            int minColumn = Arrays.stream(columns).min().getAsInt();
            for (int i = minColumn; i <= maxColumn; i++) {
                if (i == currentColumn || i == currentColumn){
                    continue;
                }

                if (chessBoard.board[i][currentColumn] != null) {
                    return true;
                }
            }
        }

        return false;
    }

    protected String formatSymbol(String symbol)
    {
        if (Objects.equals(this.color, COLOR_BLACK)) {
            return ANSI_BLACK_BACKGROUND + ANSI_WHITE + symbol + "b" + ANSI_RESET;
        }

        return ANSI_WHITE_BACKGROUND + ANSI_BLACK + symbol + "w" + ANSI_RESET;
    }

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract public String getSymbol();
}
