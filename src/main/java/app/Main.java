package app;

import app.ChessPieces.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    protected static final String COMMAND_MOVE = "move";
    protected static final String COMMAND_EXIT = "exit";
    protected static final String COMMAND_REPLAY = "replay";

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        showGameRules();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals(COMMAND_EXIT)) break;
            else if (s.equals(COMMAND_REPLAY)) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains(COMMAND_MOVE)) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Игрок " + board.nowPlayer + " сделал ход");
                            board.printBoard();
                        } else
                            System.out.println("Игроку " + board.nowPlayer + " не удалось сделать ход. Игрок " + board.nowPlayer + " повторите ход.");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                }
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

        // getRandPlayerColor()
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