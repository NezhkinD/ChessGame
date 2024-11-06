package app.Exception;

import app.ChessPieces.ChessPiece;
import app.Entity.CoordinatesEntity;

public class CannotAttackException extends Exception {
    final public static String MESSAGE_SOMETHING_WRONG = "Что-то пошло не так";
    final public static String MESSAGE_NOT_PIECE_TO_ATTACK = "Нет фигуры для атаки";
    final public static String MESSAGE_CANNOT_ATTACK = "Указана неверная траектория атаки для фигуры";
    final public static String MESSAGE_PIECE_NOT_FOUND = "Не найдена фигура для атаки";
    final public static String MESSAGE_PIECE_BLOCK_ATTACK = "Атаке мешает другая фигура";

    public CannotAttackException(ChessPiece chessPiece, int toLine, int toColumn, String message) {
        super("Текущая фигура " + chessPiece.getSymbolWithColor() + ": атака из клетки " + chessPiece.currentLine + "-" + chessPiece.currentColumn + " в клетку " + toLine + "-" + toColumn + " невозможна: " + message);
    }

    public CannotAttackException(String message) {
        super("Атака не удалась: " + message);
    }
}
