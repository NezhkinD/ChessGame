package app;

import app.ChessPieces.*;
import app.Entity.UserInputEntity;
import app.Exception.BoardException;
import app.Exception.CannotAttackException;
import app.Exception.CannotCastingException;
import app.Exception.CannotMoveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    protected static final Logger logger = LogManager.getLogger();
    public static final String COMMAND_MOVE = "move";
    public static final String COMMAND_ATTACK = "attack";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_REPLAY = "replay";
    public static final String COMMAND_CASTLING0 = "castling0";
    public static final String COMMAND_CASTLING7 = "castling7";
    public static final String[] COMMANDS = {
            COMMAND_MOVE, COMMAND_ATTACK, COMMAND_EXIT, COMMAND_REPLAY, COMMAND_CASTLING0, COMMAND_CASTLING7
    };
    private static final String MESSAGE_OUTPUT_EXIT = "Завершаем игру.\n-----------------";
    final public static String MESSAGE_KING_IS_NOT_EXIST = "Вражеский король убит, победа игрока";

    public static void main(String[] args) {
        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        showGameRules();

        while (true) {
            board.printBoard();
            stepUser(board);
            String scannerInput = scanner.nextLine();

            if (Arrays.stream(COMMANDS).noneMatch(scannerInput.split(" ")[0]::contains)){
                System.out.println("Команда не найдена");
                continue;
            }

            try {
                UserInputEntity userInputEntity = new UserInputEntity(scannerInput);
                switch (userInputEntity.command) {
                    case COMMAND_EXIT -> {
                        exit("\n");
                    }
                    case COMMAND_REPLAY -> {
                        System.out.println("Заново");
                        board = buildBoard();
                    }
                    case COMMAND_CASTLING0 -> {
                        if (board.castling0()) {
                            System.out.println("Рокировка удалась");
                        }
                    }
                    case COMMAND_CASTLING7 -> {
                        if (board.castling7()) {
                            System.out.println("Рокировка удалась");
                        }
                    }
                    case COMMAND_MOVE -> {
                        if (board.moveToPosition(userInputEntity)) {
                            System.out.println("Игрок " + board.nowPlayer + " сделал ход");
                        } else {
                            throw new CannotMoveException(CannotMoveException.MESSAGE_SOMETHING_WRONG);
                        }
                    }
                    case COMMAND_ATTACK -> {
                        if (board.attackPiece(userInputEntity)) {
                            System.out.println("Игрок " + board.nowPlayer + " атаковал");
                        } else {
                            throw new CannotAttackException(CannotAttackException.MESSAGE_SOMETHING_WRONG);
                        }
                        if (!board.checkOppositeKingExist()) {
                            exit(MESSAGE_KING_IS_NOT_EXIST + " " + board.nowPlayer);
                        }
                    }
                }

                board.changePlayer();
            } catch (CannotMoveException | CannotAttackException | BoardException | CannotCastingException e) {
                logger.warn(e.getMessage());
                logger.warn("Игроку " + board.nowPlayer + " не удалось сделать ход. Игрок " + board.nowPlayer + " повторите ход.");
            } catch (Exception e) {
                logger.error("Вы что-то ввели не так, попробуйте ещё раз");
            }
        }
    }

    public static void showGameRules() {
        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'replay' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
                Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?
                """);
        System.out.println();
    }

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard(ChessPiece.COLOR_WHITE);
        board.addChessPiece(new Rook(ChessPiece.COLOR_WHITE, 0, 0));
        board.addChessPiece(new Horse(ChessPiece.COLOR_WHITE, 0, 1));
        board.addChessPiece(new Bishop(ChessPiece.COLOR_WHITE, 0, 2));
        board.addChessPiece(new Queen(ChessPiece.COLOR_WHITE, 0, 3));
        board.addChessPiece(new King(ChessPiece.COLOR_WHITE, 0, 4));
        board.addChessPiece(new Bishop(ChessPiece.COLOR_WHITE, 0, 5));
        board.addChessPiece(new Horse(ChessPiece.COLOR_WHITE, 0, 6));
        board.addChessPiece(new Rook(ChessPiece.COLOR_WHITE, 0, 7));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 0));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 1));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 2));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 3));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 4));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 5));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 6));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_WHITE, 1, 7));

        board.addChessPiece(new Rook(ChessPiece.COLOR_BLACK, 7, 0));
        board.addChessPiece(new Horse(ChessPiece.COLOR_BLACK, 7, 1));
        board.addChessPiece(new Bishop(ChessPiece.COLOR_BLACK, 7, 2));
        board.addChessPiece(new Queen(ChessPiece.COLOR_BLACK, 7, 3));
        board.addChessPiece(new King(ChessPiece.COLOR_BLACK, 7, 4));
        board.addChessPiece(new Bishop(ChessPiece.COLOR_BLACK, 7, 5));
        board.addChessPiece(new Horse(ChessPiece.COLOR_BLACK, 7, 6));
        board.addChessPiece(new Rook(ChessPiece.COLOR_BLACK, 7, 7));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 0));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 1));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 2));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 3));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 4));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 5));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 6));
        board.addChessPiece(new Pawn(ChessPiece.COLOR_BLACK, 6, 7));
        return board;
    }

    protected static void stepUser(ChessBoard board) {
        System.out.print("Ход игрока " + board.nowPlayer + ":: ");
    }

    protected static void exit(String message) {
        System.out.println(message);
        System.out.println(MESSAGE_OUTPUT_EXIT);
        System.exit(0);
    }
}