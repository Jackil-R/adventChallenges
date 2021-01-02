package models;

public class Action {

    private char move;
    private int value;

    public Action(char move, int value) {
        this.move = move;
        this.value = value;
    }

    public Action() {

    }

    public char getMove() {
        return move;
    }

    public void setMove(char move) {
        this.move = move;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
