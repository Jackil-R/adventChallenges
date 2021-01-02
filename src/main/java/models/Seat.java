package models;

import java.util.Comparator;

public class Seat {


    private double row;
    private double column;
    private double seatId;

    public Seat(double row, double column, double seatId) {
        this.row = row;
        this.column = column;
        this.seatId = seatId;
    }

    public Seat() {
    }

    public double getRow() {
        return row;
    }

    public void setRow(double row) {
        this.row = row;
    }

    public double getColumn() {
        return column;
    }

    public void setColumn(double column) {
        this.column = column;
    }

    public double getSeatId() {
        return seatId;
    }

    public void setSeatId(double seatId) {
        this.seatId = seatId;
    }

}
