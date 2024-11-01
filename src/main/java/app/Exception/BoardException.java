package app.Exception;

import app.Entity.CoordinatesEntity;

public class BoardException extends Exception {
    final public static String MESSAGE_CHESS_PIECE_NOT_FOUND = "По указанным координатам фигура не найдена. Укажите координаты фигуры, которой собираетесь сделать ход";
    final public static String MESSAGE_IS_NOT_YOUR_PIECE = "Вы пытаетесь переместить чужую фигуру";

    public BoardException(CoordinatesEntity xy, String message) {
        super("Текущие координаты " + xy.userViewCurrent + ": " + message);
    }
}
