package app.ChessPieces;

import app.ChessBoard;

public class Queen extends ChessPiece{
    public Queen(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    protected int[][] getPossibleMoves() {
        return new int[0][];
    }
}
