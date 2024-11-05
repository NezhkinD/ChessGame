package app.Exception;

public class CannotCastingException extends Exception {
    final public static String MESSAGE_KING_AND_ROCK_MUST_BE_ON_START_POSITION = "Король и ладья должны стоять на своих начальных позициях";
    final public static String MESSAGE_CANNOT_CASTING0_PIECE = "Для осуществления рокировки м/у королем и ладьей не должно быть фигур";

    public CannotCastingException(String message) {
        super("Рокировка невозможна: " + message);
    }
}
