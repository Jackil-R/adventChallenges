package models;

public class Instruction {

    private String operation;
    private int signedNumber;

    public Instruction(String operation, int signedNumber) {
        this.operation = operation;
        this.signedNumber = signedNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getSignedNumber() {
        return signedNumber;
    }

    public void setSignedNumber(int signedNumber) {
        this.signedNumber = signedNumber;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "operation='" + operation + '\'' +
                ", signedNumber=" + signedNumber +
                '}';
    }
}
