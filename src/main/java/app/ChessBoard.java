package app;

import app.ChessPieces.ChessPiece;
import app.ChessPieces.King;
import app.ChessPieces.Rook;
import app.Entity.CoordinatesEntity;
import app.Entity.UserInputEntity;
import app.Exception.BoardException;
import app.Exception.CannotAttackException;
import app.Exception.CannotCastingException;
import app.Exception.CannotMoveException;

import java.util.Objects;
import java.util.Optional;

import static app.Exception.BoardException.MESSAGE_CHESS_PIECE_NOT_FOUND;
import static app.Exception.BoardException.MESSAGE_IS_NOT_YOUR_PIECE;
import static app.Exception.CannotCastingException.MESSAGE_CANNOT_CASTING0_PIECE;
import static app.Exception.CannotCastingException.MESSAGE_KING_AND_ROCK_MUST_BE_ON_START_POSITION;

public class ChessBoard {
    public static final int LINES = 8;
    public static final int COLUMNS = 8;
    public static final String[] columnsName = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessPiece[][] board = new ChessPiece[LINES][COLUMNS];
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

        if (chessPiece.isEmpty()) {
            throw new BoardException(xy, MESSAGE_CHESS_PIECE_NOT_FOUND);
        }

        if (!nowPlayer.equals(chessPiece.get().getColor())) {
            throw new BoardException(xy, MESSAGE_IS_NOT_YOUR_PIECE);
        }

        if (chessPiece.get().canMoveToPosition(this, userInputEntity.moveEntity)) {
            movePiece(chessPiece.get(), xy);
            return true;
        }

        return false;
    }

    public boolean attackPiece(UserInputEntity userInputEntity) throws BoardException, CannotMoveException, CannotAttackException {
        // todo практически дублирует moveToPosition
        CoordinatesEntity xy = userInputEntity.moveEntity.coordinatesEntity;
        Optional<ChessPiece> chessPiece = getChessPiece(xy.currentLine, xy.currentColumn);

        if (chessPiece.isEmpty()) {
            throw new BoardException(xy, MESSAGE_CHESS_PIECE_NOT_FOUND);
        }

        if (!nowPlayer.equals(chessPiece.get().getColor())) {
            throw new BoardException(xy, MESSAGE_IS_NOT_YOUR_PIECE);
        }

        if (chessPiece.get().canAttack(this, userInputEntity.moveEntity)) {
            if (getChessPiece(xy.toLine, xy.toColumn).isPresent()) {
                System.out.println(chessPiece.get().getSymbolWithColor() + " уничтожил " + getChessPiece(xy.toLine, xy.toColumn).get().getSymbolWithColor() + "\n");
            }

            movePiece(chessPiece.get(), xy);
            return true;
        }

        return false;
    }

    /**
     * Проверить существует ли вражеский король
     */
    public boolean checkOppositeKingExist() {
        boolean kingExist = false;
        for (ChessPiece[] pieces : board) {
            for (ChessPiece piece : pieces) {
                if (piece == null) {
                    continue;
                }
                if (Objects.equals(piece.color, getOppositePlayerColor()) && Objects.equals(piece.getSymbol(), King.SYMBOL)) {
                    return true;
                }
            }
        }
        return kingExist;
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

    public boolean castling0() throws CannotCastingException {
        int currentColumnKing = 4;
        int currentColumnRock = 7;
        int[] checkColumns = {5, 6};

        return castling(currentColumnKing, currentColumnRock, checkColumns, checkColumns[1], checkColumns[0]);
    }

    public boolean castling7() throws CannotCastingException {
        int currentColumnKing = 4;
        int currentColumnRock = 0;
        int[] checkColumns = {1, 2, 3};

        return castling(currentColumnKing, currentColumnRock, checkColumns, checkColumns[1], checkColumns[2]);
    }

    public void changePlayer() {
        this.nowPlayer = this.nowPlayerColor().equals(ChessPiece.COLOR_WHITE) ? ChessPiece.COLOR_BLACK : ChessPiece.COLOR_WHITE;
    }

    public String getOppositePlayerColor() {
        return this.nowPlayerColor().equals(ChessPiece.COLOR_WHITE) ? ChessPiece.COLOR_BLACK : ChessPiece.COLOR_WHITE;
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

    public void addChessPiece(ChessPiece piece) {
        board[piece.currentLine][piece.currentColumn] = piece;
    }

    protected void showColumnsName() {
        System.out.println("\t" + String.join("\t", columnsName));
    }

    protected void movePiece(ChessPiece chessPiece, CoordinatesEntity xy) {
        chessPiece.currentLine = xy.toLine;
        chessPiece.currentColumn = xy.toColumn;
        setChessPiece(Optional.of(chessPiece), xy.toLine, xy.toColumn);
        setChessPiece(Optional.empty(), xy.currentLine, xy.currentColumn);
    }

    protected boolean castling(int currentColumnKing, int currentColumnRock, int[] checkColumns, int toColumnKing, int toColumnRock) throws CannotCastingException {
        int currentLine = this.nowPlayerColor().equals(ChessPiece.COLOR_WHITE) ? 0 : 7;

        Optional<ChessPiece> kingPiece = getChessPiece(currentLine, currentColumnKing);
        Optional<ChessPiece> rookPiece = getChessPiece(currentLine, currentColumnRock);

        if (kingPiece.isEmpty() || rookPiece.isEmpty() || !Objects.equals(kingPiece.get().getSymbol(), King.SYMBOL) || !Objects.equals(rookPiece.get().getSymbol(), Rook.SYMBOL)) {
            throw new CannotCastingException(MESSAGE_KING_AND_ROCK_MUST_BE_ON_START_POSITION);
        }

        for (int column : checkColumns) {
            if (getChessPiece(currentLine, column).isPresent()){
                throw new CannotCastingException(MESSAGE_CANNOT_CASTING0_PIECE);
            }
        }

        movePiece(kingPiece.get(), new CoordinatesEntity(currentLine, currentColumnKing, currentLine,toColumnKing));
        movePiece(rookPiece.get(), new CoordinatesEntity(currentLine, currentColumnRock, currentLine, toColumnRock));

        return true;
    }
}
