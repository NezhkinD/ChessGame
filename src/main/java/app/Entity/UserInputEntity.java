package app.Entity;

import app.Exception.CannotMoveException;

public class UserInputEntity {
    public String command;
    public MoveEntity moveEntity;

    public UserInputEntity(String input) throws CannotMoveException {
        String[] s = input.toLowerCase().split(" ");
        command = s[0];
        moveEntity = new MoveEntity(new CoordinatesEntity(s[1], s[2]));
    }
}
