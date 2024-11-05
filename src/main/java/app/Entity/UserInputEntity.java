package app.Entity;

import app.Exception.CannotMoveException;

import java.util.Arrays;

public class UserInputEntity {
    public String command;
    public MoveEntity moveEntity;

    public UserInputEntity(String input) throws CannotMoveException {
        String[] s = input.toLowerCase().split(" ");
        command = s[0];

        if (Arrays.stream(s).count() == 3){
            moveEntity = new MoveEntity(new CoordinatesEntity(s[1], s[2]));
        } else {
            moveEntity = new MoveEntity(new CoordinatesEntity("a0", "a0"));
        }
    }
}
