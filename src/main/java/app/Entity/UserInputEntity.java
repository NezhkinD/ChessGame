package app.Entity;

import app.ChessBoard;

import java.util.Arrays;

public class UserInputEntity {
    public String command;
    public int currentLine;
    public int currentColumn;
    public int toLine;
    public int toColumn;

    public UserInputEntity(String input){
        String[] s = input.toLowerCase().split(" ");
        command = s[0];

        char[] currentLocation = s[1].toCharArray();
        currentColumn = Arrays.asList(ChessBoard.columnsName).indexOf(String.valueOf(currentLocation[0]));
        currentLine = Integer.parseInt(String.valueOf(currentLocation[1]));

        char[] toLocation = s[2].toCharArray();
        toColumn = Arrays.asList(ChessBoard.columnsName).indexOf(String.valueOf(toLocation[0]));
        toLine = Integer.parseInt(String.valueOf(toLocation[1]));
    }
}
