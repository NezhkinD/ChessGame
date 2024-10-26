package app;

import app.ChessPieces.*;
import app.Entity.UserInputEntity;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String COMMAND_MOVE = "move";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_REPLAY = "replay";
    public static final String COMMAND_CASTLING0 = "castling0";
    public static final String COMMAND_CASTLING7 = "castling7";
    private static final String MESSAGE_OUTPUT_EXIT = "Завершаем работу.\n-----------------";

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        showGameRules();
        board.printBoard();
        System.out.print("Ход игрока " + board.nowPlayer + ":: ");

        while (true) {
            String scannerInput = scanner.nextLine();

            if (scannerInput.equals(COMMAND_EXIT)) {
                System.out.println(MESSAGE_OUTPUT_EXIT);
                System.exit(0);
            }

            if (scannerInput.equals(COMMAND_REPLAY)) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
                continue;
            }


            if (scannerInput.equals(COMMAND_CASTLING0)) {
                if (board.castling0()) {
                    board.printBoard();
                    System.out.println("Рокировка удалась");
                } else {
                    System.out.println("Рокировка не удалась");
                }
                continue;
            }

            if (scannerInput.equals(COMMAND_CASTLING7)) {
                if (board.castling7()) {
                    board.printBoard();
                    System.out.println("Рокировка удалась");
                } else {
                    System.out.println("Рокировка не удалась");
                }
                continue;
            }

            if (!scannerInput.contains(COMMAND_MOVE)) {
                System.out.println("Команда не найдена");
                continue;
            }

            try {
                UserInputEntity userInputEntity = new UserInputEntity(scannerInput);
                if (board.moveToPosition(userInputEntity.currentLine, userInputEntity.currentColumn, userInputEntity.toLine, userInputEntity.toColumn)) {
                    board.printBoard();
                    System.out.println("Игрок " + board.nowPlayer + " сделал ход");
                    board.changePlayer();
                    continue;
                }

                System.out.println("Игроку " + board.nowPlayer + " не удалось сделать ход. Игрок " + board.nowPlayer + " повторите ход.");
            } catch (Exception e) {
                System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
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

        //String randPlayerColor = getRandPlayerColor();
        ChessBoard board = new ChessBoard(ChessPiece.COLOR_WHITE);

        board.board[0][0] = new Rook(ChessPiece.COLOR_WHITE, 0, 0);
        board.board[0][1] = new Horse(ChessPiece.COLOR_WHITE, 0, 1);
        board.board[0][2] = new Bishop(ChessPiece.COLOR_WHITE, 0, 2);
        board.board[0][3] = new Queen(ChessPiece.COLOR_WHITE, 0, 3);
        board.board[0][4] = new King(ChessPiece.COLOR_WHITE, 0, 4);
        board.board[0][5] = new Bishop(ChessPiece.COLOR_WHITE, 0, 5);
        board.board[0][6] = new Horse(ChessPiece.COLOR_WHITE, 0, 6);
        board.board[0][7] = new Rook(ChessPiece.COLOR_WHITE, 0, 7);
        board.board[1][0] = new Pawn(ChessPiece.COLOR_WHITE, 1, 0);
        board.board[1][1] = new Pawn(ChessPiece.COLOR_WHITE, 1, 1);
        board.board[1][2] = new Pawn(ChessPiece.COLOR_WHITE, 1, 2);
        board.board[1][3] = new Pawn(ChessPiece.COLOR_WHITE, 1, 3);
        board.board[1][4] = new Pawn(ChessPiece.COLOR_WHITE, 1, 4);
        board.board[1][5] = new Pawn(ChessPiece.COLOR_WHITE, 1, 5);
        board.board[1][6] = new Pawn(ChessPiece.COLOR_WHITE, 1, 6);
        board.board[1][7] = new Pawn(ChessPiece.COLOR_WHITE, 1, 7);

        board.board[7][0] = new Rook(ChessPiece.COLOR_BLACK, 7, 0);
        board.board[7][1] = new Horse(ChessPiece.COLOR_BLACK, 7, 1);
        board.board[7][2] = new Bishop(ChessPiece.COLOR_BLACK, 7, 2);
        board.board[7][3] = new Queen(ChessPiece.COLOR_BLACK, 7, 3);
        board.board[7][4] = new King(ChessPiece.COLOR_BLACK, 7, 4);
        board.board[7][5] = new Bishop(ChessPiece.COLOR_BLACK, 7, 5);
        board.board[7][6] = new Horse(ChessPiece.COLOR_BLACK, 7, 6);
        board.board[7][7] = new Rook(ChessPiece.COLOR_BLACK, 7, 7);
        board.board[6][0] = new Pawn(ChessPiece.COLOR_BLACK, 6, 0);
        board.board[6][1] = new Pawn(ChessPiece.COLOR_BLACK, 6, 1);
        board.board[6][2] = new Pawn(ChessPiece.COLOR_BLACK, 6, 2);
        board.board[6][3] = new Pawn(ChessPiece.COLOR_BLACK, 6, 3);
        board.board[6][4] = new Pawn(ChessPiece.COLOR_BLACK, 6, 4);
        board.board[6][5] = new Pawn(ChessPiece.COLOR_BLACK, 6, 5);
        board.board[6][6] = new Pawn(ChessPiece.COLOR_BLACK, 6, 6);
        board.board[6][7] = new Pawn(ChessPiece.COLOR_BLACK, 6, 7);
        return board;
    }

    protected static String getRandPlayerColor() {
        String color = ChessPiece.COLOR_WHITE;
        if ((new Random()).nextInt(2) == 1) {
            color = ChessPiece.COLOR_BLACK;
        }
        return color;
    }
}