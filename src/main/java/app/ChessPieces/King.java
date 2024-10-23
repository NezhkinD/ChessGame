package app.ChessPieces;

import app.ChessBoard;

public class King extends ChessPiece{
    public King(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
