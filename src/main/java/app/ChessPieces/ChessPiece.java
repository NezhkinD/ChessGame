package app.ChessPieces;

import app.ChessBoard;

abstract public class ChessPiece {
    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";

    public String color;
    public boolean check = true;
    public int currentLine;
    public int currentColumn;

    public ChessPiece(String color, int currentLine, int currentColumn) {
        this.color = color;
        this.currentLine = currentLine;
        this.currentColumn = currentColumn;
    }

    public String getColor() {
        return this.color;
    }

    /**
     * Проверяем выходим ли за рамки доски
     */
    public boolean checkBoardLimits(int toLine, int toColumn) {
        if (toLine >= ChessBoard.LINES || toLine < 0) {
            return true;
        }

        if (toColumn >= ChessBoard.COLUMNS || toColumn < 0) {
            return true;
        }

        return false;
    }

    /**
     * Есть ли мешающая фигура на линии передвижения
     */
    public boolean isChessPieceOnMove(ChessBoard chessBoard, int toLine, int toColumn) {
        ChessPiece chessPiece = chessBoard.board[toLine][toColumn];

        if (chessPiece != null) {
            return false;
        }

        if (currentLine != toLine && currentColumn == toColumn){
            for (int i = currentLine; i <= toLine; i++) {
                if (chessBoard.board[i][currentColumn] != null) {
                    return true;
                }
            }
        }

        if (currentColumn != toColumn && currentLine == toLine){
            for (int i = currentColumn; i <= toColumn; i++) {
                if (chessBoard.board[currentLine][i] != null) {
                    return true;
                }
            }
        }

        return false;
    }

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract public String getSymbol();
}
