package models;

public class Password {

    private int start;
    private int end;
    private char character;
    private String password;

    public Password(int start, int end, char character, String password) {
        this.start = start;
        this.end = end;
        this.character = character;
        this.password = password;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public char getCharacter() {
        return character;
    }

    public String getPassword() {
        return password;
    }
}
