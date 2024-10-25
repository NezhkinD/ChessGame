package app.ChessPieces;

import app.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        currentColumn = toLine;
        currentLine = toColumn;
        return false;
    }

    @Override
    public String getSymbol() {
        return formatSymbol("B");
    }
}
