package app.Entity;

import app.ChessPieces.ChessPiece;

public class PossibleMovesEntity {
    public ChessPiece chessPiece;
    public int toLine;
    public int toColumn;

    public PossibleMovesEntity(ChessPiece chessPiece, int toLine, int toColumn){
        this.chessPiece = chessPiece;
        this.toLine = toLine;
        this.toColumn = toColumn;
    }
}
