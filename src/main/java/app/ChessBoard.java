package app;

import app.ChessPieces.*;
import app.Entity.CoordinatesEntity;
import app.Entity.MoveEntity;
import app.Entity.UserInputEntity;
import app.Exception.BoardException;

import java.util.Arrays;
import java.util.Optional;

import static app.Exception.BoardException.*;

public class ChessBoard {
    public static final int LINES = 8;
    public static final int COLUMNS = 8;
    public static final String[] columnsName = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessPiece[][] board = new ChessPiece[LINES][COLUMNS]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(UserInputEntity userInputEntity) throws Exception {
        CoordinatesEntity xy = userInputEntity.moveEntity.coordinatesEntity;
        Optional<ChessPiece> chessPiece = getChessPiece(xy.currentLine, xy.currentColumn);
        if (chessPiece.isEmpty()){
            throw new BoardException(xy, MESSAGE_CHESS_PIECE_NOT_FOUND);
        }

        if (!nowPlayer.equals(chessPiece.get().getColor())) {
           throw new BoardException(xy, MESSAGE_IS_NOT_YOUR_PIECE);
        }

        if (chessPiece.get().canMoveToPosition(this, userInputEntity.moveEntity)) {
            chessPiece.get().currentLine = xy.toLine;
            chessPiece.get().currentColumn = xy.toColumn;
            setChessPiece(chessPiece, xy.toLine ,xy.toColumn); // if piece can move, we moved a piece
            setChessPiece(Optional.empty(), xy.currentLine, xy.currentColumn);  // set null to previous cell
            return true;
        }

        return false;
    }

    /**
     * Отобразить доску
     */
    public void printBoard() {
        System.out.println("Player 2(" + ChessPiece.COLOR_BLACK + ")");
        System.out.println();
        showColumnsName();

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbolWithColor() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(" + ChessPiece.COLOR_WHITE + ")");
    }

    public boolean checkPosition(int pos) {
        return pos >= 0 && pos < LINES;
    }

    public boolean castling0() {
        return true;
    }

    public boolean castling7() {
        return true;
    }

    public void changePlayer() {
        this.nowPlayer = this.nowPlayerColor().equals(ChessPiece.COLOR_WHITE) ? ChessPiece.COLOR_BLACK : ChessPiece.COLOR_WHITE;
        System.out.print("Ход игрока " + nowPlayer + ":: ");
    }

    public Optional<ChessPiece> getChessPiece(int line, int column) {
        if (board[line][column] == null) {
            return Optional.empty();
        }

        return Optional.of(board[line][column]);
    }

    public void setChessPiece(Optional<ChessPiece> chessPiece, int line, int column) {
        if (chessPiece.isEmpty()) {
            board[line][column] = null;
            return;
        }
        board[line][column] = chessPiece.get();
    }

    public CoordinatesEntity mapCoordinatesFromString(String from, String to){
        char[] currentLocation = from.toCharArray();
        int currentColumn = Arrays.asList(ChessBoard.columnsName).indexOf(String.valueOf(currentLocation[0]));
        int currentLine = Integer.parseInt(String.valueOf(currentLocation[1]));

        char[] toLocation = to.toCharArray();
        int toColumn = Arrays.asList(ChessBoard.columnsName).indexOf(String.valueOf(toLocation[0]));
        int toLine = Integer.parseInt(String.valueOf(toLocation[1]));

        return new CoordinatesEntity(currentLine, currentColumn, toLine, toColumn);
    }

    protected void showColumnsName() {
        System.out.println("\t" + String.join("\t", columnsName));
    }
}
