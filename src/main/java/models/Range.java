package models;

public class Range {

    double low;
    double high;

    public Range(double low, double high) {
        this.low = low;
        this.high = high;
    }

    public Range() {
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }
}
