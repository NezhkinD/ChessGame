package app.ChessPieces;

import app.ChessBoard;

import java.util.Objects;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece {
    public Pawn(String color, int currentLine, int currentColumn) {
        super(color, currentLine, currentColumn);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (super.checkBoardLimits(toLine, toColumn)) {
            return false;
        }

        // todo проверять есть ли фигура на линии движения
//        if (super.isChessPieceOnMove(chessBoard, toLine, toColumn)){
//            return false;
//        }

        if (super.currentColumn != toColumn) {
            return false;
        }

        if (Objects.equals(super.color, COLOR_WHITE)) {
            if (toLine > super.currentLine && super.currentLine == 1 && (toLine - super.currentLine) <= 2) {
                return true;
            }

            if (toLine > super.currentLine && super.currentLine >= 2 && (toLine - super.currentLine) == 1) {
                return true;
            }
        }

        if (Objects.equals(super.color, COLOR_BLACK)) {
            if (super.currentLine > toLine && super.currentLine == 6 && (super.currentLine - toLine) <= 2) {
                return true;
            }

            if (super.currentLine > toLine && super.currentLine >= 6 && (super.currentLine - toLine) == 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return formatSymbol("P");
    }
}
