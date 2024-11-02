package app.Entity;

import app.ChessBoard;

import java.util.Arrays;

public class CoordinatesEntity {
    public int currentLine;
    public int currentColumn;
    public int toLine;
    public int toColumn;
    public String userViewCurrent;
    public String userViewTo;

    public CoordinatesEntity(int currentLine, int currentColumn, int toLine, int toColumn) {
        this.currentLine = currentLine;
        this.currentColumn = currentColumn;
        this.toLine = toLine;
        this.toColumn = toColumn;

        this.userViewCurrent = ""; //todo
        this.userViewTo = ""; //todo
    }

    public CoordinatesEntity(String from, String to) {
        char[] currentLocation = from.toCharArray();
        currentColumn = Arrays.asList(ChessBoard.columnsName).indexOf(String.valueOf(currentLocation[0]));
        currentLine = Integer.parseInt(String.valueOf(currentLocation[1]));

        char[] toLocation = to.toCharArray();
        toColumn = Arrays.asList(ChessBoard.columnsName).indexOf(String.valueOf(toLocation[0]));
        toLine = Integer.parseInt(String.valueOf(toLocation[1]));

        userViewCurrent = from;
        userViewTo = to;
    }
}
