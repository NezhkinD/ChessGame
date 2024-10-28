package app.Exception;

import app.ChessPieces.ChessPiece;

public class CannotMoveException extends Exception {
    final public static String MESSAGE_CURRENT_COLUMN_NOT_EQ_TO_COLUMN = "Текущая колонка НЕ равняется колонке назначения";
    final public static String MESSAGE_BOARD_LIMIT = "Перемещение в клетку назначение, приведет к выходу за пределы доски";
    final public static String MESSAGE_PIECE_BLOCK_MOVE = "Перемещению мешает другая фигура";
    final public static String MESSAGE_WHITE_LINE = "Для перемещения белой фигуры, клетка назначения (toLine) должна быть БОЛЬШЕ текущей клетки (currentLine)";
    final public static String MESSAGE_BLACK_LINE = "Для перемещения черной фигуры, клетка назначения (toLine) должна быть МЕНЬШЕ текущей клетки (currentLine)";
    final public static String MESSAGE_NOT_POSSIBLE_MOVE = "Указана неверная траектория движения для фигуры";

    public CannotMoveException(ChessPiece chessPiece, int toLine, int toColumn, String message) {
        super("Текущая фигура " + chessPiece.getSymbolWithColor() + ": перемещение из клетки " + chessPiece.currentLine + "-" + chessPiece.currentColumn + " в клетку " + toLine + "-" + toColumn + " невозможно: " + message);
    }
}
