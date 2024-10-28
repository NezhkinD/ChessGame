package app.ChessPieces;

import app.ChessBoard;

public class Rook extends ChessPiece{
    public Rook(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public int[][] getPossibleMoves() {
        return this.getAllHorizontalMoves(2);
    }
}
