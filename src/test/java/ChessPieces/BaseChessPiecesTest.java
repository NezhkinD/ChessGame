package ChessPieces;

import app.ChessBoard;
import app.ChessPieces.*;

import java.util.Optional;

abstract public class BaseChessPiecesTest {
    protected static ChessBoard createEmptyBoard(Optional<String> nowPlayerColor){
        return new ChessBoard(nowPlayerColor.orElse(ChessPiece.COLOR_WHITE));
    }

    protected static ChessBoard createBaseBoardWhiteOnly(Optional<String> nowPlayerColor) {
        ChessBoard board = new ChessBoard(nowPlayerColor.orElse(ChessPiece.COLOR_WHITE));
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
        return board;
    }

    protected static ChessBoard createBaseBoardBlackOnly(Optional<String> nowPlayerColor) {
        ChessBoard board = new ChessBoard(nowPlayerColor.orElse(ChessPiece.COLOR_WHITE));
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
}
