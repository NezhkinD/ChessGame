package app.Exception;

import app.ChessPieces.ChessPiece;
import app.Entity.CoordinatesEntity;

public class CannotMoveException extends Exception {
    final public static String MESSAGE_SOMETHING_WRONG = "Что-то пошло не так";
    final public static String MESSAGE_BOARD_LIMIT = "Перемещение в клетку назначение, приведет к выходу за пределы доски";
    final public static String MESSAGE_PIECE_BLOCK_MOVE = "Перемещению мешает другая фигура";
    final public static String MESSAGE_NOT_POSSIBLE_MOVE = "Указана неверная траектория движения для фигуры";
    final public static String MESSAGE_UNDEFINE_MOVE = "Не определена траектория движения фигуры";
    final public static String MESSAGE_NOT_POSSIBLE_MOVE_PAWN = "Только в первый ход пешка может сделать движение на 2 клетки вперед";
    final public static String MESSAGE_NOT_POSSIBLE_MOVE_KING = "Король может перемещаться только на одну клетку";

    public CannotMoveException(ChessPiece chessPiece, int toLine, int toColumn, String message) {
        super("Текущая фигура " + chessPiece.getSymbolWithColor() + ": перемещение из клетки " + chessPiece.currentLine + "-" + chessPiece.currentColumn + " в клетку " + toLine + "-" + toColumn + " невозможно: " + message);
    }

    public CannotMoveException(CoordinatesEntity xy, String message) {
        super("Перемещение из " + xy.userViewCurrent + " в " + xy.userViewTo + " невозможно: " + message);
    }

    public CannotMoveException(String message) {
        super("Перемещение не удалось: " +  message);
    }
}
